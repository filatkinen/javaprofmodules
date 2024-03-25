package ru.otus.filatkinen.task02.testframework.launcher;

import ru.otus.filatkinen.task02.testframework.classes.A;
import ru.otus.filatkinen.task02.testframework.classes.B;
import ru.otus.filatkinen.task02.testframework.classes.C;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("---Testing Class A----");
        new Launcher(A.class).launchTest();
        System.out.println("\n---Testing Class B----");
        new Launcher(B.class).launchTest();
        System.out.println("\n---Testing Class C----");
        new Launcher(C.class).launchTest();
    }
}
