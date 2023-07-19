package com.gate;

/**
 * @author: Fred
 * @date: 2023/7/18 16:39
 * @description: (添加、修改和删除字段)
 * 下面是一个示例，用于在类中添加一个名为 "newField" 的字段，并删除名为 "toBeRemovedField" 的字段：
 */
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class MyFieldClassVisitor extends ClassVisitor {

    public MyFieldClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        // 删除名为 "toBeRemovedField" 的字段
        if ("toBeRemovedField".equals(name)) {
            return null;
        }

        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        // 添加名为 "newField" 的字段
        FieldVisitor newFieldVisitor = super.visitField(Opcodes.ACC_PRIVATE, "newField", "Ljava/lang/String;", null, null);
        if (newFieldVisitor != null) {
            newFieldVisitor.visitEnd();
        }
        super.visitEnd();
    }
}