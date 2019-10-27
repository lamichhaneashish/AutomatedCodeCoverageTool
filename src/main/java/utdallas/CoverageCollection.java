package utdallas;
import java.util.HashMap;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;

/**
 * @author Javier Gomez
 * @author Yugesh Taksari
 * @author Ashish Lamichhane
 */

/**
 * This class collects all the coverage of a testsuite and all the test cases inside that test suite.
 */
public class CoverageCollection {

    public static HashMap<String, IntLinkedOpenHashSet> testCases; // HashMap to store information about each test case.
    public static String testClassName; // name of the test class.
    public static HashMap<String, HashMap<String, IntLinkedOpenHashSet>> testSuite; // HashMap to store information about entire test suite.

    /**
     * This information is passed to this method from MethodTransformVisitor.
     * If the test cases is null then it simply returns. Else, a IntLinkedOpenHashSet created that stores information about that class and the
     * line numbers covered by that class.
     * @param testClassName : String , name of the test class.
     * @param line_covered : Integer, the line number covered.
     */
    public static void lineinfo(String testClassName, Integer line_covered) {
        // if there is no testcase HashMap then simply return.
        if (testCases == null) {
            return;
        }
        // A new IntLinkedOpenHashSet is created to store the lines covered by that test.
        IntLinkedOpenHashSet linesCovered = testCases.get(testClassName);
        /**
         * if the lines covered is null then it will created a new integer array of the lines that has been covered and
         * pass it into testcases HashMap else, they are entered into the linesCovered IntLinkedOpenHashSet that is already present
         */
        if ( linesCovered== null) {
            int[] tempSet = {line_covered};
            testCases.put(testClassName, new IntLinkedOpenHashSet(tempSet));
        } else { linesCovered.add(line_covered); }
    }

}