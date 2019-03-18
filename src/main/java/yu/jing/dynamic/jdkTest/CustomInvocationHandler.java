package yu.jing.dynamic.jdkTest;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    //这是动态代理的好处，被封装的对象是Object类型，接受任意类型的对象
    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * JDK提供了Java.lang.reflect.Proxy类来实现动态代理的，可通过它的newProxyInstance来获得代理实现类。
         * 同时对于代理的接口的实际处理，
         * 是一个java.lang.reflect.InvocationHandler，它提供了一个invoke方法供实现者提供相应的代理逻辑的实现。
         */

        System.out.println("开始jdk");
        Object reVal = method.invoke(target, args);
        System.out.println("结束jdk");
        return reVal;
    }
}
