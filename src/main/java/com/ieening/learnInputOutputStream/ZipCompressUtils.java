package com.ieening.learnInputOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompressUtils {
    public static boolean addFileToZipOutputStream(File file, ZipOutputStream outputStream, String rootPath)
            throws IOException {
        String relativePath = file.getCanonicalPath().substring(rootPath.length());
        if (file.isFile()) {
            outputStream.putNextEntry(new ZipEntry(relativePath));
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                inputStream.transferTo(outputStream);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            outputStream.putNextEntry(new ZipEntry(relativePath + File.separator));
            for (File f : file.listFiles()) {
                if (!addFileToZipOutputStream(f, outputStream, rootPath)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean extractZipEntry(ZipEntry entry, ZipInputStream zipInputStream, String destDir) {
        if (!entry.getName().endsWith(File.separator)) {
            File parent = new File(destDir + entry.getName()).getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            try (OutputStream entryOut = new BufferedOutputStream(new FileOutputStream(destDir + entry.getName()))) {
                zipInputStream.transferTo(entryOut);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return new File(destDir + entry.getName()).mkdirs();
        }
        return true;
    }
}
