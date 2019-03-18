package yu.jing.dynamic.jdkTest;

public class HelloTestImpl implements HelloTest {
    @Override
    public void say(String name) {
        System.out.println("jdk dynamic hello " +name);
    }
}
