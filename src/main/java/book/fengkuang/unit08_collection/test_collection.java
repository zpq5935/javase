package book.fengkuang.unit08_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.Test;

public class test_collection {

    @Test
    public void test01() {
        List<Object> l1 = new ArrayList<>();
        l1.add("Java虚拟机");
        System.out.println(l1.size());
        System.out.println(l1.contains("Java虚拟机"));
        System.out.println(l1.contains(6));
        Set<Object> set = new HashSet<>();
        set.add(56);
        set.add("C++");
        System.out.println(l1);
        l1.addAll(set);
        System.out.println(l1);
    }

    /**
     * 使用Lambda 表达式遍历集合
     */
    @Test
    public void test02() {
        Collection<Object> collection = new HashSet<>();
        collection.add(123);
        collection.add("klj");
        collection.add("8sd45");
        collection.forEach(obj -> System.out.println("元素：" + obj));
    }

    /**
     * 使用Iterator 表达式遍历集合
     */
    @Test
    public void test03() {
        Collection<Object> collection = new HashSet<>();
        collection.add(123);
        collection.add("klj");
        collection.add("8sd45");
        collection.add("人生");
        Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            /*
             * if(((String)o).equals("人生")) iterator.remove();
             */
            System.out.println(o);
        }
    }

    /**
     * 使用Stream 流式遍历集合
     */
    @Test
    public void test04() {
        IntStream is = IntStream.builder().add(1).add(85).add(-96).build();
        // 注意：下面某些属于末端方法（使用过后会清空数据，后面对于流的再次操作是不允许的）
//        System.out.println(is.max().getAsInt());
         is.forEach(ele -> System.out.printf("%-5s",ele));
//         is.sorted().forEach(ele -> System.out.printf("%-5s",ele));
        //
        List list = new ArrayList<>();
        list.add("章删");
        list.add("章zp");
        list.add("zpq");
        list.add("大声道");
        list.add("章婆婆");
        System.out.println("\n包含\"章\":"+list.stream().filter(
                ele -> ((String) ele).contains("章")
                ).count());

    }
}
