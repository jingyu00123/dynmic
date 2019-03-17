package yu.jing.dynamic.jdkTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        //设置为true,会在工程根目录生成$Proxy0.class代理类（com.sun.proxy.$Proxy0.class）
        System.getProperties().put(
                "sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        String saveGeneratedFiles = System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles");
        System.out.println(saveGeneratedFiles);

        HelloTest helloTest = new HelloTestImpl();
        CustomInvocationHandler customInvocationHandler = new CustomInvocationHandler(helloTest);
        HelloTest proxy = (HelloTest) Proxy.newProxyInstance(helloTest.getClass().getClassLoader(),
                helloTest.getClass().getInterfaces(), customInvocationHandler);
        proxy.say("jingyu");

    }
}
