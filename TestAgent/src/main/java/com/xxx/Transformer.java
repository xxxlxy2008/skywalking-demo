package com.xxx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

class Transformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader l, String className,
                            Class<?> c, ProtectionDomain pd, byte[] b)  {
        if (!c.getSimpleName().equals("TestClass")) {
            return null; // 只修改TestClass的定义
        }
        // 读取 TestClass.class这个 class文件，作为 TestClass类的新定义
        return getBytesFromFile("/Users/xxx/Documents/log/TestClass.class");
    }

    public static byte[] getBytesFromFile(String fileName) {
        try {
            // precondition
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!"
                    + e.getClass().getName());
            return null;
        }
    }
}