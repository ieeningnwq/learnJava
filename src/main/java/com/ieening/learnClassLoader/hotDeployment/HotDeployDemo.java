package com.ieening.learnClassLoader.hotDeployment;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;

public class HotDeployDemo {
    private static final String CLASS_NAME = "com.ieening.learnClassLoader.hotDeployment.HelloImpl";
    private static final String FILE_NAME = "src\\main\\resources\\helloClass\\"
            + CLASS_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class";
    private static volatile IHelloService helloService;

    /**
     * @return the helloService
     */
    public static IHelloService getHelloService() {
        if (helloService != null) {
            return helloService;
        }
        synchronized (HotDeployDemo.class) {
            helloService = createHelloService();
            return helloService;
        }
    }

    private static IHelloService createHelloService() {
        try {
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> cls = myClassLoader.loadClass(CLASS_NAME);
            if (cls != null) {
                return (IHelloService) cls.getConstructor().newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void client() {
        Thread t = new Thread() {
            public void run() {
                try {
                    while (true) {
                        IHelloService helloService = getHelloService();
                        helloService.sayHello();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            };
        };
        t.start();
    }

    public static void monitor() {
        Thread t = new Thread() {
            private long lastModified = new File(FILE_NAME).lastModified();

            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        long now = new File(FILE_NAME).lastModified();
                        if (now != lastModified) {
                            lastModified = now;
                            reloadHelloService();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        };
        t.start();
    }

    protected static void reloadHelloService() {
        helloService = createHelloService();
    }

    public static void main(String[] args) {
        client();
        monitor();
    }
}
