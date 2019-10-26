package utdallas;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedHashSet;
//import java.util.Set;

public class CoverageTool {
    public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testSuite;
    public static HashMap<String, LinkedHashSet<Integer>> testCase;
    public static String testName;

    public static void visitLine(String className, Integer line){
        if(testCase == null || className == null) return;
        LinkedHashSet<Integer> set = testCase.get(className);
        if(set == null) {
            set = new LinkedHashSet<Integer>();
        }
        set.add(line);
        testCase.put(className, set);
    }
}
