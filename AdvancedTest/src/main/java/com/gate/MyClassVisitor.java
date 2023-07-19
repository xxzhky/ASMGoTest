package com.gate;

/**
 * @author: Fred
 * @date: 2023/7/18 16:29
 * @description: (使用 ClassVisitor 解析字节码)
 */
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    // 使用 ASM5 作为 Opcodes 版本，并调用父类构造函数
    public MyClassVisitor() {
        super(Opcodes.ASM5);
    }

    // 重写 visit 方法，用于在访问类时输出类名
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // 打印类名
        System.out.println("Class: " + name);
        // 调用父类 visit 方法，以便继续处理类信息
        super.visit(version, access, name, signature, superName, interfaces);
    }

    // 重写 visitMethod 方法，用于在访问类中的方法时输出方法名
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // 打印方法名
        System.out.println("Method: " + name);
        // 调用父类 visitMethod 方法，以便继续处理方法信息
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    // 然后我们可以将自定义的 ClassVisitor 传递给 ClassReader，以开始解析字节码：
    //MyClassVisitor classVisitor = new MyClassVisitor();
    // 使用 ClassReader 的 accept 方法，将 MyClassVisitor 传递给 ClassReader 进行字节码分析
    //classReader.accept(classVisitor, 0);
}