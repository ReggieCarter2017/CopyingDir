package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        copyDir(new File("test2"), new File("./backup"));

    }

    private static void copyDir(File sourceDir, File copiedDir) throws IOException {
        if (!copiedDir.exists()) {
            copiedDir.mkdir();
        }
        for (String f : sourceDir.list()) {
            copyDirectoryCompatibityMode(new File(sourceDir, f), new File(copiedDir, f));
        }
    }

    public static void copyDirectoryCompatibityMode(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            copyDir(source, dest);
        } else {
            copyFile(source, dest);
        }
    }


    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            assert is != null;
            is.close();
            assert os != null;
            os.close();
        }
    }
}