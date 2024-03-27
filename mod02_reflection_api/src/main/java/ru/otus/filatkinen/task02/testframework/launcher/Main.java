package ru.otus.filatkinen.task02.testframework.launcher;

import ru.otus.filatkinen.task02.testframework.classes.A;
import ru.otus.filatkinen.task02.testframework.classes.B;
import ru.otus.filatkinen.task02.testframework.classes.C;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        LogManager slg = LogManager.getLogManager();
        Logger log = slg.getLogger(Logger.GLOBAL_LOGGER_NAME);

        log.info("---Testing Class A----");
        new Launcher(A.class).launchTest();
        log.info("---Testing Class B----");
        new Launcher(B.class).launchTest();
        log.info("---Testing Class C----");
        new Launcher(C.class).launchTest();
    }
}
