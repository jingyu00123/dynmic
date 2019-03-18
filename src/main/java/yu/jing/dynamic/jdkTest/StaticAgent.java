package yu.jing.dynamic.jdkTest;

public class StaticAgent  implements HelloTest{
    private  HelloTestImpl helloTest;


    @Override
    public void say(String name) {
        getSubject().say("jingyu");
    }

    public  HelloTestImpl getSubject(){
        if(helloTest == null){
            helloTest = new HelloTestImpl();
        }
        return helloTest;
    }
}
