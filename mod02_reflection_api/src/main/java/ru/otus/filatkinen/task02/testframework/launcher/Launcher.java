package ru.otus.filatkinen.task02.testframework.launcher;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Launcher {

    private class MethodInstance {
        Method method;
        int priority;

        public MethodInstance(Method method, int priority) {
            this.method = method;
            this.priority = priority;
        }
    }

    private Method beforeSuite;
    private Method afterSuite;
    private List<MethodInstance> before;
    private List<MethodInstance> after;
    private List<MethodInstance> methods;
    private boolean skippedClass;
    protected boolean failConfiguration;

    private Class classLaunch;

    private int testCount;
    private int testSkipped;
    private int testSuccessful;
    private int testFall;


    public Launcher(Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.classLaunch = cl;
        before = new ArrayList<>();
        after = new ArrayList<>();
        methods = new ArrayList<>();
        initLouncher();
    }

    private void initLouncher() {

        // Check Class Disabled Annotation
        if (classLaunch.isAnnotationPresent(Disabled.class)) {
            skippedClass = true;
            return;
        }

        for (Method method : classLaunch.getDeclaredMethods()) {
            testCount++;

            // Check Method Disabled Annotation
            if (method.isAnnotationPresent(Disabled.class)) {
                testSkipped++;
                continue;
            }

            // Check BeforeSuite and AfterSuite annotation
            if (method.isAnnotationPresent(BeforeSuite.class) && !method.isAnnotationPresent(Test.class)) {
                if (beforeSuite != null) {
                    failConfiguration = true;
                    System.out.println("BeforeSuite встречается более 1-го раза");
                    return;
                }
                beforeSuite = method;
                continue;
            }
            if (method.isAnnotationPresent(AfterSuite.class) && !method.isAnnotationPresent(Test.class)) {
                if (afterSuite != null) {
                    failConfiguration = true;
                    System.out.println("AfterSuit встречается более 1-го раза");
                    return;
                }
                afterSuite = method;
                continue;
            }

            // Check Before and After annotation
            if ((method.isAnnotationPresent(Before.class) || method.isAnnotationPresent(After.class))
                    && method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                //Check 1<=priority<=10. Otherwise, skip test
                if (!(test.priority() >= 1 && test.priority() <= 10)) {
                    continue;
                }

                MethodInstance methodInstance = new MethodInstance(method, test.priority());
                if (method.isAnnotationPresent(Before.class)) {
                    before.add(methodInstance);
                } else {
                    after.add(methodInstance);
                }
                continue;
            }

            // Check Test Annotation
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                MethodInstance methodInstance = new MethodInstance(method, test.priority());
                methods.add(methodInstance);
            }

            method.setAccessible(true);
        }

        Comparator<MethodInstance> cmp = (x, y) -> {
            if (x.priority == y.priority) {
                return 0;
            }
            return x.priority > y.priority ? 1 : -1;
        };

        // Sorting by priority
        methods.sort(cmp);

        // Lets sorting by priority before and after too :)
        before.sort(cmp);
        after.sort(cmp);

    }

    private void endLouncher() {
        System.out.println();
        System.out.println("Статистика тестирования:");
        System.out.printf("Всего методов: %d\n", testCount);
        System.out.printf("Пропущено(Disabled): %d\n", testSkipped);
        System.out.printf("Выполнено успешно: %d\n", testSuccessful);
        System.out.printf("Завершились неудачей: %d\n", testFall);
    }


    public void launchTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (skippedClass) {
            System.out.println("Аннотация Disable  была установлена на класс целиком - тестирования не было");
            return;
        }

        if (failConfiguration) {
            System.out.println("Ошибка в применении аннтотаций - тестирования не было");
            return;
        }

        Object o = classLaunch.getConstructor().newInstance();

        //BeforeSuite
        System.out.println(">>>>>>@BeforeSuite<<<<<<");
        launchMethod(beforeSuite, o);

        int counter;
        for (MethodInstance methodLaunch : methods) {
            System.out.println();
            counter = 0;
            for (MethodInstance methodBefore : before) {
                if (counter == 0) {
                    System.out.println(">>>>>>>>>@Before<<<<<<<<<");
                    counter++;
                }
                launchMethod(methodBefore.method, o);
            }

            System.out.println(">>>>>>>>>>>>>>@Test<<<<<<<<<<<<<<" + " priority=" + methodLaunch.priority);
            launchMethod(methodLaunch.method, o);

            counter = 0;
            for (MethodInstance methodAfter : after) {
                if (counter == 0) {
                    System.out.println(">>>>>>>>>@After<<<<<<<<<");
                    counter++;
                }
                launchMethod(methodAfter.method, o);
            }
        }

        //AfterSuite
        System.out.println("\n>>>>>>@AfterSuite<<<<<<");
        launchMethod(afterSuite, o);

        //End
        endLouncher();

    }


    private void launchMethod(Method m, Object o) {
        if (m == null) {
            return;
        }
        try {
            m.invoke(o);
        } catch (Exception e) {
            System.out.println("!!!Error -"+e);
            testFall++;
        }
        testSuccessful++;
    }
}


