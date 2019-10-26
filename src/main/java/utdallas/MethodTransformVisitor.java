package utdallas;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTransformVisitor extends MethodVisitor implements Opcodes{

    private String className;
    private int line;

     /*    * @param mv
     * @param className
     */
    public MethodTransformVisitor(final MethodVisitor mv, String className) {
        super(ASM6, mv);
        this.className = className;
    }

    // method coverage collection
//    @Override
//    public void visitCode(){
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn(mName+" executed");
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//        super.visitCode();
//    }


    @Override
    public void visitLineNumber(int line, Label start) {
            this.line = line;
            /**
             * Generated by ASMfier
            */

            mv.visitLdcInsn(className);
            mv.visitLdcInsn(line);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "utdallas/CoverageTool", "addCoveredLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
            super.visitLineNumber(line, start);

    }

    @Override
    public void visitLabel(Label arg0) {
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(this.line);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKESTATIC, "utdallas/CoverageTool", "addCoveredLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        super.visitLabel(arg0);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }


}
