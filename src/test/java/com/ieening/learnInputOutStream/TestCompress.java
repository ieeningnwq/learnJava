package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.ieening.learnInputOutputStream.ZipCompressUtils;

public class TestCompress {
    String fileNameToCompress = "src\\main\\resources\\compress\\AutoSave_1002b5_7e61dc00_3a96a21.sav";
    String fileNameUnCompressed = "src\\main\\resources\\compress\\AutoSave_1002b5_7e61dc00_3a96a21_uncompressed.sav";
    String gzFileName = fileNameToCompress + ".gz";

    @Test
    public void testGzipCompress() {
        File f = new File(gzFileName);
        if (f.exists()) {
            f.delete();
        }

        long startTime = new Date().getTime();
        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileNameToCompress));
                GZIPOutputStream outputStream = new GZIPOutputStream(
                        new BufferedOutputStream(new FileOutputStream(gzFileName)))) {
            inputStream.transferTo(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(gzFileName);
        assertTrue(file.lastModified() > startTime);
    }

    @Test
    public void testGzipUnCompress() throws IOException {
        try (
                GZIPInputStream gzipInputStream = new GZIPInputStream(
                        new BufferedInputStream(new FileInputStream(gzFileName)));
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(fileNameUnCompressed))) {
            gzipInputStream.transferTo(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(FileUtils.contentEquals(new File(fileNameToCompress), new File(fileNameUnCompressed)));
    }

    String dirNameToCompress = "src\\main\\resources\\compress\\gamesaves";
    String fileNameUnzip = "src\\main\\resources\\compress\\gamesaves_unzip";
    String fileZiped = "src\\main\\resources\\compress\\gamesaves.zip";

    @Test
    public void testZipCompress() {
        File f = new File(fileZiped);
        if (f.exists()) {
            f.delete();
        }
        long startTime = new Date().getTime();
        File toZipFile = new File(dirNameToCompress);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileZiped)))) {
            if (!toZipFile.exists()) {
                throw new FileNotFoundException(toZipFile.getAbsolutePath());
            } else {
                toZipFile = toZipFile.getCanonicalFile();
                String rootPath = toZipFile.getParent();
                if (!rootPath.endsWith(File.separator)) {
                    rootPath += File.separator;
                }
                if (!ZipCompressUtils.addFileToZipOutputStream(toZipFile, zipOutputStream, rootPath)) {
                    throw new ZipException("zip " + toZipFile.getAbsolutePath() + "failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(fileZiped);
        assertTrue(file.lastModified() > startTime);
    }

    @Test
    public void testUnZipCompress() throws IOException {
        try (ZipInputStream zInputStream = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(fileZiped)))) {
            if (!fileNameUnzip.endsWith(File.separator)) {
                fileNameUnzip += File.separator;
                ZipEntry entry;
                while ((entry = zInputStream.getNextEntry()) != null) {
                    if (!ZipCompressUtils.extractZipEntry(entry, zInputStream, fileNameUnzip)) {
                        throw new ZipException("unzip file " + fileZiped);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(sizeOfDirectory(new File(dirNameToCompress)), sizeOfDirectory(new File(fileNameUnzip)));
    }

    public static long sizeOfDirectory(final File directory) {
        long size = 0;
        if (directory.isFile()) {
            return directory.length();
        } else {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += sizeOfDirectory(file);
                }
            }
        }
        return size;
    }
}
