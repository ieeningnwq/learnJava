package com.ieening.learnClassLoader.hotDeployment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;

public class MyClassLoader extends ClassLoader {
    private static final String BASE_DIR = "src\\main\\resources\\helloClass\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        fileName = BASE_DIR + fileName + ".class";
        try (InputStream input = new FileInputStream(fileName);
                ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            input.transferTo(output);
            byte[] bytes = output.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("failed to load class " + name, e);
        }
    }
}
