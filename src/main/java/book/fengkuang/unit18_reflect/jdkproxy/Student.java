package book.fengkuang.unit18_reflect.jdkproxy;

public class Student implements Person {
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public void eat() {
        System.out.println("学生被保护为祖国的花朵，一般来说应该吃好些；事实上喊口号的事实在太正常了");
    }

    @Override
    public void sleep() {
        System.out.println("学生的话每日起的都是很早的，5 6 点吧");

    }

    @Override
    public void drink() {
        System.out.println(name + "喝水貌似没啥，我记得我高中就是隔三差五需要下去搬水而已");

    }

}
