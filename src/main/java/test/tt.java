package test;

import test.use_default.IamAbstractNotDef;

import java.lang.reflect.Method;

public class tt {
    public static void main(String[] args) {
        Method[] declaredMethods = IamAbstractNotDef.class.getDeclaredMethods();
        System.out.println(declaredMethods);

    }
}
