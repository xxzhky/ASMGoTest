package com.leadboot.gate.proxy;

/**
 * @author: Fred
 * @date: 2023/7/18 17:17
 * @description: 动态代理是一种常用的设计模式，可以在运行时动态地为对象生成代理。 (类的描述)
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;

import com.leadboot.gate.loader.MyClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * 需要注意的是，直接操作字节码可能会导致难以调试的问题，因此在实际项目中应谨慎使用。
 * 在使用 Java ASM 时，确保充分了解其潜在风险，并确保在修改字节码时保持对 Java 虚拟机规范的遵从性。
 */

public class MyDynamicProxy {

    public static <T> T createProxy(Class<T> interfaceClass, InvocationHandler handler) {
        // 创建 ClassWriter
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        // 定义代理类，实现指定的接口
        String proxyClassName = interfaceClass.getName() + "$Proxy";
        String proxyClassInternalName = proxyClassName.replace('.', '/');
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, proxyClassInternalName, null, "java/lang/Object", new String[]{Type.getInternalName(interfaceClass)});

        // 实现代理类的构造方法
        MethodVisitor constructorVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructorVisitor.visitCode();
        constructorVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        constructorVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructorVisitor.visitInsn(Opcodes.RETURN);
        constructorVisitor.visitMaxs(1, 1);
        constructorVisitor.visitEnd();

        // 实现接口的所有方法
        for (Method method : interfaceClass.getDeclaredMethods()) {
            String methodName = method.getName();
            String methodDescriptor = Type.getMethodDescriptor(method);
            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);

            // 在代理方法中调用 InvocationHandler 的 invoke 方法
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitFieldInsn(Opcodes.GETFIELD, proxyClassInternalName, "handler", "Ljava/lang/reflect/InvocationHandler;");
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitLdcInsn(Type.getType(interfaceClass));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/reflect/Method", "valueOf", "(Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/lang/reflect/InvocationHandler", "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", true);
            methodVisitor.visitInsn(Opcodes.ARETURN);
            methodVisitor.visitMaxs(4, 2);
            methodVisitor.visitEnd();
        }

        classWriter.visitEnd();

        // 使用自定义类加载器加载代理类
        byte[] proxyClassBytecode = classWriter.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> proxyClass = myClassLoader.defineClass(proxyClassName, ByteBuffer.wrap(proxyClassBytecode));

        // 创建代理类实例，并设置 InvocationHandler
        try {
            T proxyInstance = (T) proxyClass.getConstructor().newInstance();
            proxyClass.getField("handler").set(proxyInstance, handler);
            return proxyInstance;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to create proxy instance", e);
        }
    }


}