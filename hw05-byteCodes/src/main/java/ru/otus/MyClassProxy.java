package ru.otus;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class MyClassProxy {

    private MyClassProxy() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(MyClassProxy.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method implMethod = myClass.getClass().getMethod(method.getName(), method.getParameterTypes());
            Object invoke = method.invoke(myClass, args);
            if (implMethod.isAnnotationPresent(Log.class)) {
                printLog(proxy, method, args);
            }
            return invoke;
        }
    }

    static void printLog(Object proxy, Method method, Object[] args) {
        System.out.println("executed method:" + method.getName() + ", param: " + StringUtils.join(args, ", "));
    }
}
