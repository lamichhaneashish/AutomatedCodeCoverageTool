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

    String mName; // to store the class name.
    int line; // line number

    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName = name;
    }

    // statement coverage collection
    @Override
    public void visitLineNumber(int line, Label start) {
        if(line != 0) {
            this.line = line;
            mv.visitLdcInsn(mName);
            mv.visitLdcInsn(line);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "ashishyugeshjavier/CoverageCollection", "lineinfo", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitLabel(Label label) {
        if(line != 0) {
            mv.visitLdcInsn(mName);
            mv.visitLdcInsn(line);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "ashishyugeshjavier/CoverageCollection", "lineinfo", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        super.visitLabel(label);
    }
}