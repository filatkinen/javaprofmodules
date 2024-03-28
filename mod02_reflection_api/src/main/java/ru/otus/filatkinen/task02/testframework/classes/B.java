package ru.otus.filatkinen.task02.testframework.classes;

import ru.otus.filatkinen.task02.testframework.launcher.*;

public class B {

    @Disabled
//    @ThrowsException(exception = "ArithmeticException")
    public void a01(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @BeforeSuite
    private void a02(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @AfterSuite
    public void a03(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @AfterSuite
    private void a04(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Before
    @Test(priority = 10)
    public void a05(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 7)
    private void a06(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 4)
    public void a07(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 8)
    private void a08(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 1)
    private void a09(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 9)
    private void a10() throws Exception {
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());

        throw new ErrorSimple();
    }


    @Test(priority = 8)
    private void a11(){
        System.out.printf(">>Execute class %s, method %s\n",
                this.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
    }
}
