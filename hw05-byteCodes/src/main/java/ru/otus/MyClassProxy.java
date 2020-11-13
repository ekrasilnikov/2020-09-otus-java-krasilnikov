package ru.otus;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
            System.out.println("executed method:" + method.getName() + ", param: " + StringUtils.join(args, ", "));
            return method.invoke(myClass, args);
        }
    }
}
