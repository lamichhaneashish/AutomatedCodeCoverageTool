package ashishyugeshjavier;

// All the imports
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

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
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
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
                                @Override
                                public byte[] transform(ClassLoader classLoader, String str, Class<?> aClass,
                                                        ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
                                    // All 10 projects that were tested using the tool for their coverage details.
                                    if (str.startsWith("org/apache/commons/dbutils") == true || str.startsWith("com/jakewharton/byteunits") == true ||
               str.startsWith("com/googlecode/charts4j") == true || str.startsWith("au/com/ds/ef") == true || str.startsWith("com/warrenstrange/googleauth") == true ||
               str.startsWith("com/offbytwo/iclojure") == true || str.startsWith("de/komoot/photon") == true || str.startsWith("com/cloudhopper/smpp") == true || 
               str.startsWith("org/atteo/evo/inflector") == true || str.startsWith("org/webbit") == true){
                                        System.out.println( "Java Agent is running !!!!" ); // message that the java agent has started running.
                                        ClassReader reader = new ClassReader(bytes); // its an event producer that parses a compiled class given as a byte array.
                                        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //its an event consumer that produces as output a byte array containing the compiled class.
                                        ClassTransformVisitor cVisitor = new ClassTransformVisitor(writer); // its an event filter.
                                        reader.accept(cVisitor, 0);
                                        return writer.toByteArray();
                                    }
                                    return null;
                                }
                            }
        );
    }
}
