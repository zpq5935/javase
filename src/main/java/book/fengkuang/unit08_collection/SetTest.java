package book.fengkuang.unit08_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.junit.Test;

class A {
    @Override
    public boolean equals(Object o) {
        return true;
    }
}

class B {
    @Override
    public int hashCode() {
        return 1;
    }
}

class C {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }
}

public class SetTest {
    @Test
    public void test_hashset() {
        HashSet<Object> hashSet = new HashSet<Object>();
        System.out.println(hashSet.add(new A()));
        System.out.println(hashSet.add(new A()));
        System.out.println("添加b类");
        B b1 = new B();
        B b2 = new B();
        System.out.println("equals:" + b1.equals(b2));
        System.out.println("==:" + (b1 == b2));
        System.out.println(hashSet.add(b1));
        System.out.println(hashSet.add(b2));
        System.out.println(hashSet.add(new C()));
        System.out.println(hashSet.add(new C()));
        System.out.println(hashSet);
    }

    /*
     * 测试LinkedHashSet的有序性
     */
    @Test
    public void test_linkedhashet() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(23);
        hashSet.add(593);
        hashSet.add(-823);
        hashSet.add(0);
        hashSet.add(null);
        System.out.println(hashSet);
        // LinkedHashSet可以保证插入顺序与存储顺序的一致
        LinkedHashSet<Integer> linkedhashSet = new LinkedHashSet<>();
        linkedhashSet.add(23);
        linkedhashSet.add(593);
        linkedhashSet.add(-823);
        linkedhashSet.add(0);
        linkedhashSet.add(null);
        System.out.println(linkedhashSet);
    }

    /**
     * TreeSet保持元素的比较顺序
     */
    @Test
    public void testTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(-90);
        treeSet.add(688);
        treeSet.add(6886);
        treeSet.add(8);
        System.out.println(treeSet);
        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
        System.out.println(treeSet.subSet(-100, 100));
    }

    /**
     * 测试EnumSet，也是有序的
     */
    @Test
    public void testEnumSet() {
        Collection collection = new ArrayList<BookEnum>();
        collection.add(BookEnum.CHINESE);
        collection.add(BookEnum.MATH);
        System.out.println(collection);
        //
        EnumSet<BookEnum> bookEnums = EnumSet.copyOf(collection);
        System.out.println(bookEnums);
    }

}
