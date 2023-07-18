package com.leadboot.gate;

/**
 * @author: Fred
 * @date: 2023/7/18 16:42
 * @description: 修改方法内的指令
 * 下面是一个示例，用于在类中添加一个名为 "newMethod" 的方法，并删除名为 "toBeRemovedMethod" 的方法：
 */
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyMethodAdapter extends MethodVisitor {

    public MyMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        // 在方法调用前添加 System.out.println
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Entering method: " + name);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        // 原始方法调用
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

}

