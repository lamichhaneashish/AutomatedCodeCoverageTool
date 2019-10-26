package utdallas;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTransformVisitor extends ClassVisitor implements Opcodes {
    private String class_name;

    public ClassTransformVisitor(final ClassVisitor class_visitor){
        super(ASM6,class_visitor);
    }

    @Override
    public void visit(int version, int access, String class_name,String signature,
                      String superName, String[] interfaces){
        this.class_name = class_name;
        super.visit(version, access, class_name, signature, superName, interfaces);


    }

    @Override
    public MethodVisitor visitMethod(final int access, final String m_name, final String desc, final String signature, final String[] exceptions){
        MethodVisitor methodVisitor = cv.visitMethod(access, m_name, desc, signature, exceptions);
        return methodVisitor == null ? null : new MethodTransformVisitor(methodVisitor,class_name);
    }


}
