package ru.otus;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class MyClassProxy {

    static MyClassInterface createMyClass() throws NoSuchMethodException {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(MyClassProxy.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        Map<String, Method> annotatedMethodsMap = new HashMap<>();

        DemoInvocationHandler(MyClassInterface myClass) throws NoSuchMethodException {
            this.myClass = myClass;
            for (Method method : myClass.getClass().getMethods()) {
                if (myClass.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Log.class)) {
                    annotatedMethodsMap.put(method.getName(), method);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Map.Entry<String, Method> annotatedMethod : annotatedMethodsMap.entrySet()) {
                if (annotatedMethod.getKey().equals(method.getName())) {
                    printLog(myClass.getClass().getMethod(method.getName(), method.getParameterTypes()), args);
                }
            }
            return method.invoke(myClass, args);
        }
    }

    static void printLog(Method method, Object[] args) {
        System.out.println("executed method:" + method.getName() + ", param: " + StringUtils.join(args, ", "));
    }
}
