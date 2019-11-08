package ashishyugeshjavier;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
/**
 * @author Javier Gomez
 * @author Yugesh Taksari
 * @author Ashish Lamichhane
 */
public class Agent {
	   /**
     * The premain is a mechanism associated with the java.lang.instrument package, used for loading
     * our agent that heps in the byte-code manipulation for finding the code coverage of the JUnit Tests.
     * We are only concerned with the instrumentation and coverage for the classes of the project under test
     * so the prefix of those classes is used to filter out other classes such as classes belonging to third-party libraries or
     * Java Internal library.
     * @param agentArgs : String of arguments.
     * @param inst : Instrumentation
     */
	public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer( new ClassFileTransformer() {
        	 /**
             * It transforms the classes into the bytecode format.
             * @param classLoader
             * @param str
             * @param aClass
             * @param protectionDomain
             * @param bytes
             * @return
             * @throws IllegalClassFormatException
             */
           public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        	   if (className.startsWith( "org/apache/commons/dbutils" ) || className.startsWith("org/joda/time") || className.startsWith("project")){
//        	        System.out.println("Java Agent has started !!!");
                	ClassReader classReader = new ClassReader(classfileBuffer);
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    ClassTransformVisitor classVisitor = new ClassTransformVisitor(classWriter);
                    classReader.accept( classVisitor,0 );
                    return classWriter.toByteArray();
                }
                return new byte[0];
            }
        } );
    }

}
