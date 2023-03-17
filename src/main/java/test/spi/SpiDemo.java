package test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<People> peoples = ServiceLoader.load(People.class);

        Iterator<People> iterator = peoples.iterator();
        while (iterator.hasNext()) {
            People people = iterator.next();

            System.out.println(people.speak());

        }
    }
}
