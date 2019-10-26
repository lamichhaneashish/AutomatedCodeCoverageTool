package utdallas;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class CoverageTool {

    public static HashMap<String, HashSet> linesCovered;
    public static HashMap<String, HashMap<String, HashSet>> testCases;
    public static String testName;

    public static void addCoveredLine(String name, Integer line) {
        // if the the lines covered is empty
        if (linesCovered == null) {
            System.out.println("This is line " + name + "| " + line);
            return;
        }

        HashSet covered_lines = linesCovered.get(name);
        // If linesCovered has no values for the class name
        if (covered_lines == null) {
            int[] new_set = { line };
            System.out.println("This is line " + name + "| " + line);
            linesCovered.put(name, new HashSet<>(Arrays.asList(new_set)));
        }

        else {
            System.out.println("This is the line " + name + "| " + line);
            covered_lines.add(line);
        }
    }
}
