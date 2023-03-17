package book.fengkuang.unit15_io.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import org.junit.Test;

/**
 * 序列化测试
 *
 * @author 22517
 */
public class SerializeTest implements Serializable {

    @Test
    public void testSeralize() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            Person person = new Person("9龙", 23);
            Teacher teacher1 = new Teacher(person);
            Teacher teacher2 = new Teacher(person);
//            objectOutputStream.writeObject(teacher1);
//            objectOutputStream.writeObject(teacher2);
            objectOutputStream.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeseralize() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"))) {
            Teacher teacher1 = (Teacher) objectInputStream.readObject();
            Teacher teacher2 = (Teacher) objectInputStream.readObject();
            Person person = (Person) objectInputStream.readObject();
            System.out.println(teacher1.person.equals(teacher2.person));
            System.out.println(teacher1.person.equals(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试static变量会不会参与序列化
     */
    @Test
    public void testSerializeStatic() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"))) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"));
//            Person person = new Person("李四", 22);
//            Teacher2 teacher2 = new Teacher2(person);
//            objectOutputStream.writeObject(teacher2);
            Teacher2 readTeacher = (Teacher2) objectInputStream.readObject();
            System.out.println(readTeacher);
//            System.out.println(Teacher2.person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试关键字transient
     */
    @Test
    public void testTransient() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"));
            Richer richer = new Richer();
            richer.setMoney(100L);
            richer.setName("张三");
            System.out.println(richer);
            objectOutputStream.writeObject(richer);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"));
            Richer richerFrom = (Richer) objectInputStream.readObject();
            System.out.println(richerFrom);
        } catch (Exception e) {
        }
    }

    /**
     * 自定义序列化，把富人的钱暴露出来
     */
    @Test
    public void testDefineSeria() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"));
            Richer2 richer = new Richer2();
            richer.setMoney(100L);
            richer.setName("张三");
            System.out.println(richer);
            objectOutputStream.writeObject(richer);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"));
            Richer2 richerFrom = (Richer2) objectInputStream.readObject();
            System.out.println(richerFrom);
        } catch (Exception e) {
        }
    }

    /**
     * 测试子类对方法readResolve的访问操作符的反应
     */
    @Test
    public void testReadResolve() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"));
            Richer2Son richer = new Richer2Son();
            richer.setMoney(100L);
            richer.setName("张三");
            System.out.println(richer);
            objectOutputStream.writeObject(richer);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"));
            Richer2Son richerFrom = (Richer2Son) objectInputStream.readObject();
            System.out.println(richerFrom);
        } catch (Exception e) {
        }
    }


    class Person  implements Serializable{
        private String name;
        private int age;

        // 我不提供无参构造器
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    class Teacher implements Serializable {
        Person person;

        public Teacher(Person person) {
            super();
            this.person = person;
        }

    }

    /**
     * 测试static会不会序列化
     *
     * @author 22517
     */
    static class Teacher2 implements Serializable {
        static Person person;

        public Teacher2(Person person) {
            super();
            this.person = person;
        }

        public static Person getPerson() {
            return person;
        }

        public static void setPerson(Person person) {
            Teacher2.person = person;
        }

        @Override
        public String toString() {
            return "Teacher2{" + "person='" + person + '}';
        }
    }

    class Richer implements Serializable {
        String name;
        transient Long money;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getMoney() {
            return money;
        }

        public void setMoney(Long money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "Richer [name=" + name + ", money=" + money + "]";
        }

    }

    /**
     * 这个富人，我要把你的财产暴露出来
     *
     * @author 22517
     */
    class Richer2 implements Serializable {
        String name;
        transient Long money;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getMoney() {
            return money;
        }

        public void setMoney(Long money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "Richer [name=" + name + ", money=" + money + "]";
        }

        // private void writeObject(ObjectOutputStream objectOutputStream) throws
        // IOException {
        // objectOutputStream.writeObject(name);
        // objectOutputStream.writeLong(money);
        // }
        //
        // private void readObject(ObjectInputStream inputStream) throws
        // IOException, ClassNotFoundException {
        // this.name = (String) inputStream.readObject();
        // this.money = inputStream.readLong();
        // }
        private Object readResolve() throws ObjectStreamException {
            return null;
        }
    }

    class Richer2Son extends Richer2 {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

    }
}