package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        final Object instance = initInstance(configClass);
        List<Method> methodList = getMethods(configClass);
        initComponent(instance, methodList);
    }

    private void initComponent(Object instance, List<Method> methodList) {
        for (Method method : methodList) {
            final AppComponent componentInfo = method.getAnnotation(AppComponent.class);
            checkContext(componentInfo.name());
            try {
                method.setAccessible(true);
                Object component = method.invoke(instance, getMethodArguments(method));
                appComponents.add(component);
                appComponentsByName.put(componentInfo.name(), component);
            } catch (Exception e) {
                throw new AppComponentsContainerException(String.format("Cannot create component '%s'", componentInfo.name()), e);
            }
        }
    }

    private List<Method> getMethods(Class<?> configClass) {
        final List<Method> methodList = Arrays.asList(configClass.getDeclaredMethods());
        return methodList.stream()
                .filter(f -> f.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(c -> c.getAnnotation(AppComponent.class).order()))
                .collect(Collectors.toList());
    }

    private Object[] getMethodArguments(Method method) {
        if (method.getParameterCount() == 0) {
            return new Object[0];
        }
        Object[] args = new Object[method.getParameterCount()];
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            args[i] = getAppComponent(parameters[i].getType());
        }
        return args;
    }

    private Object initInstance(Class<?> configClass) {
        try {
            Constructor<?> constructor = configClass.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new AppComponentsContainerException(
                    String.format("Failed to instantiate class %s", configClass.getCanonicalName())
                    , e);
        }
    }

    private void checkContext(String name) {
        if (appComponentsByName.containsKey(name)) {
            throw new AppComponentsContainerException(String.format("A component named '%s' is already in context.", name)
            );
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Сlass is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if (Objects.isNull(componentClass)) {
            throw new IllegalArgumentException("componentClass can not be null");
        }
        List<Object> componentsList = appComponents.stream()
                .filter(componentClass::isInstance)
                .collect(Collectors.toList());
        if (componentsList.isEmpty()) {
            throw new AppComponentsContainerException(String.format("Component '%s' not found in context", componentClass.getCanonicalName()));
        }
        if (componentsList.size() > 1) {
            throw new AppComponentsContainerException(String.format("Found more 1 component '%s' in context", componentClass.getCanonicalName()));
        }
        return (C) componentsList.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (Objects.isNull(componentName)) {
            throw new IllegalArgumentException("Component name can not be null");
        }
        C component = (C) appComponentsByName.get(componentName);
        if (Objects.isNull(component)) {
            throw new AppComponentsContainerException(String.format("Component '%s' not found in context", componentName));
        }
        return component;
    }
}
