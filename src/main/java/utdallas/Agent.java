package utdallas;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static void premain(String agentArguments, Instrumentation instrumentation){
        System.out.println("Agent is running !!!"); // for checking purpose only.will be deleted in a final build.
        instrumentation.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("transforming " + className); // checking purpose only
                if (className.startsWith("org/apache/commons/dbutils") ||
                        className.startsWith("org/joda/time") ||
                        className.startsWith("com/fasterxml/aalto") ||
                        className.startsWith("org/neo4j/batchimport") ||
                        className.startsWith("com/github/vbauer/caesar") ||
                        className.startsWith("com/vaadin/demo/dashboard") ||
                        className.startsWith("au/com/ds/ef") ||
                        className.startsWith("de/apaxo/bedcon") ||
                        className.startsWith("com/tagtraum/perf/gcviewer") ||
                        className.startsWith("org/hashids")
                ){
                    System.out.println(className);
                    ClassReader class_reader = new ClassReader(classfileBuffer);
                    ClassWriter class_writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    ClassTransformVisitor ctransform_visitor = new ClassTransformVisitor(class_writer);
                    class_reader.accept(ctransform_visitor,0);
                    return class_writer.toByteArray();
                }
                return classfileBuffer;
            }
        });
    }
}
