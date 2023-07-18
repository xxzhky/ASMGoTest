package com.leadboot.gate;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author: Fred
 * @date: 2023/7/18 16:47
 * @description: 要应用这个方法适配器，我们需要在自定义的 ClassVisitor 实现中重写 visitMethod 方法
 *
 */
public
class MyMethodLoggerClassVisitor extends ClassVisitor {

    public MyMethodLoggerClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MyMethodAdapter(methodVisitor);
    }
}
