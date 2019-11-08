package ashishyugeshjavier;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Javier Gomez
 * @author Yugesh Taksari
 * @author Ashish Lamichhane
 */

/**
 * This is similar to the MethodTransformVisitor provided to us in the second HW.
 * Some changes are :
 * a. visitLineNumber has been modified to pass the information to the CollectCoverageTool's lineinfo method.
 * b. similar changes in the visitLabel method as well.
 */
class MethodTransformVisitor extends MethodVisitor implements Opcodes {


    private String cName;
    private int line;

    /**
     * Since we only care the className not the methodName in this project.
     * so we input the className
     * @param mv
     * @param className
     */
    public MethodTransformVisitor(final MethodVisitor mv, String className) {
        super(ASM7, mv);
        this.cName = className;
    }


    @Override
    public void visitLabel(Label arg0) {
        mv.visitLdcInsn(this.cName);
        mv.visitLdcInsn(this.line);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKESTATIC, "group2/CoverageCollection", "visitLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        super.visitLabel(arg0);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
    	this.line = line;
        if (0 != line) {
            mv.visitLdcInsn(cName);
            mv.visitLdcInsn(line);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "group2/CoverageCollection", "visitLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
            super.visitLineNumber(line, start);
        }
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
