package com.gate;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: Fred
 * @date: 2023/7/18 16:45
 * @description: 生成新的字节码
 */
public class MyBytecodeGen {

    public static void main(String[] args) throws IOException {


//要读取字节码，我们需要首先创建一个 ClassReader 实例。ClassReader 可以接受一个字节数组，表示一个已编译的 Java 类文件。
// 例如，我们可以从文件系统或者类加载器中加载字节码：

// 从文件系统中加载字节码
        byte[] bytecode = Files.readAllBytes(Paths.get("path/to/MyClass.class"));

// 或者从类加载器中加载字节码
        InputStream is = MyBytecodeGen.class.getClassLoader().getResourceAsStream("com/example/MyClass.class");
        //在修改字节码后，我们需要使用 Class Writer 生成新的字节码。以下是一个示例，展示了如何使用自定义的 ClassVisitor 修改字节码，
        // 并使用 ClassWriter 生成新的字节码：
        // 创建 lassReader
        ClassReader classReader = new ClassReader(is);

        // 创建 ClassWriter
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        // 创建自定义的 ClassVisitor，接受 ClassWriter 作为参数
        MyMethodLoggerClassVisitor myMethodLoggerClassVisitor = new MyMethodLoggerClassVisitor(classWriter);

// 使用 ClassReader 遍历字节码，应用自定义的 ClassVisitor
        classReader.accept(myMethodLoggerClassVisitor, ClassReader.EXPAND_FRAMES);

        // 从 ClassWriter 中获取修改后的字节码
        byte[] modifiedBytecode = classWriter.toByteArray();

// 可以将 modifiedBytecode 写入到 .class 文件或直接加载到 JVM 中执行
    }

}
