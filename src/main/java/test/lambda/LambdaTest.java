package test.lambda;

public class LambdaTest {
    public static void main(String[] args) {
        LambdaTest l = new LambdaTest();
        l.add((i,j)->i+j);
        l.eat(()-> System.out.println("我在吃。。。"));
        l.fly(weather -> System.out.println("天气："+weather));
    }

    public void eat(Eatable eatable){
        System.out.println(eatable);
        eatable.taste();
    }
    public  void fly(Flyable flyable){
        System.out.println("我正在驾驶："+flyable);
        flyable.fly("raining");
    }
    public  void add(Addable addable){
        System.out.println("3与5的和为："+addable.add(3,5));
    }
}

interface Eatable {
    void taste();
}

interface Flyable {
    void fly(String weather);
}

interface Addable{
    int add(int a, int b);
}
