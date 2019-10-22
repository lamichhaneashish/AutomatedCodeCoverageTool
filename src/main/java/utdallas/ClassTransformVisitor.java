package utdallas;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTransformVisitor extends ClassVisitor implements Opcodes {
    private String class_name;

    public ClassTransformVisitor(final ClassVisitor class_visitor){
        super(ASM6,class_visitor);
    }


}
