package ashishyugeshjavier;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Javier Gomez
 * @author Yugesh Taksari
 * @author Ashish Lamichhane
 */

/**
 * This class collects all the coverage of a testsuite and all the test cases inside that test suite.
 */
public class CoverageCollection {

public static HashMap<String, HashMap<String,HashSet<Integer>>> testSuiteInfo;
public static HashMap<String,HashSet<Integer>> testCase;
public static String testName;

/**
 * This information is passed to this method from MethodTransformVisitor.
 * If the test cases is null then it simply returns. Else, a IntLinkedOpenHashSet created that stores information about that class and the
 * line numbers covered by that class.
 * @param className : String , name of the test class.
 * @param line : Integer, the line number covered.
 */
public static void visitLine(String className, Integer line){
	if (testCase == null) return ;
    HashSet<Integer> lines = testCase.get(className);
    /**
     * if the lines covered is null then it will created a new integer array of the lines that has been covered and
     * pass it into testcases HashMap else, they are entered into the linesCovered IntLinkedOpenHashSet that is already present
     */
    if(lines == null) {
        lines = new HashSet<Integer>();
        testCase.put(className, lines);   
    }else {
    	lines.add(line);
    }
}

}
