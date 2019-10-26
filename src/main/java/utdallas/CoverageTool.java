package utdallas;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedHashSet;
//import java.util.Arrays

public class CoverageTool {
    public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testSuite;
    public static HashMap<String, LinkedHashSet<Integer>> testCase;
    public static String testName;

    public static void addCoveredLine(String name, Integer line) {

        // if the lines covered is empty
        if (testCase == null) {

            System.out.println("This is line " + name + "| " + line);
            return;

        }

        LinkedHashSet covered_lines = testCase.get(name);
        // If linesCovered has no values for the specified class name
        if (covered_lines == null) {
            int[] new_set = { line };
            System.out.println("This is line " + name + "| " + line);
            testCase.put(name, new LinkedHashSet(Arrays.asList(new_set)));
            // System.out.println("Putting line number" + line + "in" + name);
        }

        else {
            // If adding lines to existing linesCovered
            System.out.println("This is line " + name + "| " + line);
            covered_lines.add(line);
        }
    }
}
