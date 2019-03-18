package yu.jing.dynamic.jdkTest;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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

        //生成proxy的方法
        HelloTest proxy = (HelloTest) Proxy.newProxyInstance(helloTest.getClass().getClassLoader(),
                helloTest.getClass().getInterfaces(), customInvocationHandler);


        //这里可以通过运行结果证明proxy是Proxy的一个实例，这个实例实现了hellotest接口
        System.out.println("判断生成代理类是否属于proxy"+(proxy instanceof Proxy) );
        System.out.println("生成类型"+proxy.getClass().toString());
        //field
        Field[] fields = proxy.getClass().getDeclaredFields();
        for(Field field : fields){
            System.out.println("字段有"+field.getName());
        }
        Method[] methods = proxy.getClass().getDeclaredMethods();
        for (Method method : methods){
            System.out.println("方法有"+method.getName());
        }
        System.out.println("父类"+proxy.getClass().getSuperclass().getName());
        proxy.say("jingyu");

    }
}
