package ashishyugeshjavier;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Javier Gomez
 * @author Yugesh Taksari
 * @author Ashish Lamichhane
 */

/**
 * JUnitListener captures the start and end events for each JUnit test method,
 * so that we can start recording when the test starts and stop recording when the test ends.
 * JUnit RunListener provides a direct way to do that that is integrated in the pom.xml file instead of creating
 * a separate RunListener.
 */

public class JUnitListener extends RunListener {

    /**
     * This is run whenever a test suite starts to run.
     * A HashMap for a single test suite is created if it is not currently present.
     * @param description_info
     * @throws Exception
     */
    public void testRunStarted(Description description_info) throws Exception
    {
        if (CoverageCollection.testSuite == null) {
            CoverageCollection.testSuite = new HashMap<String, HashMap<String, IntLinkedOpenHashSet>>();
        }
    }

    /**
     * This is run whenever a test case starts to run.
     * Here The full test class name prefixed by "[TEST] is created as well as testCases, a HashMap to store information about
     * lines covered is also created.
     * @param description_info
     */
    public void testStarted(Description description_info)
    {
        CoverageCollection.testClassName = "[TEST]" + description_info.getClassName() + ":" + description_info.getMethodName();
        CoverageCollection.testCases = new HashMap<String, IntLinkedOpenHashSet>();
    }

    /**
     * This is run whenever a test case has finished running.
     * In this case, teh coverage information is passed into a testsuite HashMap.
     * @param description_info
     */
    public void testFinished(Description description_info)
    {
        CoverageCollection.testSuite.put( CoverageCollection.testClassName, CoverageCollection.testCases);
    }

    /**
     * This is run whenever a test suite has finished running. In this case, a new file is created called "stmt-cov.txt" and all information
     * collected so far is dumped into that file. The test suite HashMap's key is same to the testCase HashMap key to first dump information
     * about test class full name and the test method name followed by test class full name and line numbers covered by that test case.
     * @param result
     * @throws IOException
     */
    public void testRunFinished(Result result) throws IOException {

        File output_file = new File("stmt-cov.txt");
        StringBuilder result_string = new StringBuilder();
        FileOutputStream output_stream = new FileOutputStream(output_file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output_stream));

        for (String testCaseName : CoverageCollection.testSuite.keySet())
        {
            result_string.append(testCaseName + "\n");
            HashMap<String, IntLinkedOpenHashSet> caseCoverage = CoverageCollection.testSuite.get(testCaseName);
            for (String cName : caseCoverage.keySet())
            {
                int[] lines = caseCoverage.get(cName).toIntArray();
                Arrays.sort(lines); // lines are sorted before dumping into the file.
                for (int line : lines)
                {
                    result_string.append(cName + ":" + line + "\n");
                }
            }
        }

        bw.write(result_string.toString());
        bw.close();
    }

}