package yu.jing.dynamic.jdkTest;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy  implements MethodInterceptor {

    private Object target;//需要代理的目标对象


    //重写拦截方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, objects);//方法执行，参数：target 目标对象 objects参数数组
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }


    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        Object result = enhancer.create();//创建并返回代理对象
        System.out.println("cglib生成类"+result.getClass().getName());
        return result;
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/yu.jing/Desktop/workSpace/dynmic/dynmic/com/sun");
        CglibProxy cglib = new CglibProxy();//实例化CglibProxy对象
        HelloTest user =  (HelloTest) cglib.getCglibProxy(new HelloTestImpl());//获取代理对象
        user.say("jingyu");
    }
}
