package com.leadboot.gate;

/**
 * @author: Fred
 * @date: 2023/7/18 16:41
 * @description: (添加、修改和删除方法)
 */
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyMethodClassVisitor extends ClassVisitor {

    public MyMethodClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // 删除名为 "toBeRemovedMethod" 的方法
        if ("toBeRemovedMethod".equals(name)) {
            return null;
        }

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        // 添加名为 "newMethod" 的方法
        MethodVisitor newMethodVisitor = super.visitMethod(Opcodes.ACC_PUBLIC, "newMethod", "()V", null, null);
        if (newMethodVisitor != null) {
            // 开始访问方法的字节码
            newMethodVisitor.visitCode();

            // 向方法字节码中添加 RETURN 指令，表示方法返回
            newMethodVisitor.visitInsn(Opcodes.RETURN);

            // 设置方法的最大操作数栈深度和最大局部变量表大小
            // 由于这个方法是一个空方法，所以这里设置为 0, 0
            newMethodVisitor.visitMaxs(0, 0);

            // 结束访问方法的字节码
            newMethodVisitor.visitEnd();
        }
        super.visitEnd();
    }
}