package com.gate.loader;

/**
 * @author: Fred
 * @date: 2023/7/18 17:08
 * @description: 要实现自定义类加载器，我们需要扩展 Java 标准库中的 ClassLoader 类，并重写 findClass 方法。
 */
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.gate.MyMethodLoggerClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class MyClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 加载原始字节码
            String resourceName = name.replace('.', '/').concat(".class");
            InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
            byte[] originalBytecode = is.readAllBytes();

            // 使用 Java ASM 修改字节码
            byte[] modifiedBytecode = modifyBytecode(originalBytecode);

            // 使用修改后的字节码定义类
            ByteBuffer byteBuffer = ByteBuffer.wrap(modifiedBytecode);
            return defineClass(name, byteBuffer, null);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class " + name, e);
        }
    }

    private byte[] modifyBytecode(byte[] originalBytecode) {
        // 创建 ClassReader 和 ClassWriter
        ClassReader classReader = new ClassReader(originalBytecode);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        // 创建自定义的 ClassVisitor
        MyMethodLoggerClassVisitor myMethodLoggerClassVisitor = new MyMethodLoggerClassVisitor(classWriter);

        // 使用 ClassReader 遍历字节码，应用自定义的 ClassVisitor
        classReader.accept(myMethodLoggerClassVisitor, ClassReader.EXPAND_FRAMES);

        // 返回修改后的字节码
        return classWriter.toByteArray();
    }

    public Class defineClass(String name, byte[] b) {

        return defineClass(name, b, 0, b.length);
    }

    public Class defineClass(String name, java.nio.ByteBuffer bf) {

        return defineClass(name, bf, null);
    }
}