package ashishyugeshjavier;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


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
    public void testRunStarted(Description description) throws Exception {
        CoverageCollection.testSuiteInfo = new HashMap<String,HashMap<String,HashSet<Integer>>>();
    }
    
    /**
     * This is run whenever a test suite has finished running. In this case, a new file is created called "stmt-cov.txt" and all information
     * collected so far is dumped into that file. The test suite HashMap's key is same to the testCase HashMap key to first dump information
     * about test class full name and the test method name followed by test class full name and line numbers covered by that test case.
     * @param result
     * @throws IOException
     */
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Test Run Finished\n");
        try {
            FileWriter fw = new FileWriter("stmt-cov.txt",true);
            StringBuffer sb = new StringBuffer();
            for (String testCaseName : CoverageCollection.testSuiteInfo.keySet())
            {
                sb.append(testCaseName);
                HashMap<String, HashSet<Integer>> caseCoverage = CoverageCollection.testSuiteInfo.get(testCaseName);
                for (String cName : caseCoverage.keySet())
                {
                	HashSet<Integer> values = caseCoverage.get(cName);
                	for (Integer i : values) {
                		sb.append(cName + ":" + i + "\n");
                	}
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ex) {
            System.err.println("Couldn't log this");
        }
    }

    /**
     * This is run whenever a test case starts to run.
     * Here The full test class name prefixed by "[TEST] is created as well as testCases, a HashMap to store information about
     * lines covered is also created.
     * @param description_info
     */
    public void testStarted(Description description) throws Exception {
        CoverageCollection.testName = "[TEST] " + description.getClassName() + ":" + description.getMethodName() + "\n" ;
        CoverageCollection.testCase = new HashMap<String,HashSet<Integer>>();
    }

    /**
     * This is run whenever a test case has finished running.
     * In this case, the coverage information is passed into a testsuite HashMap.
     * @param description_info
     */
    public void testFinished(Description description) throws Exception {
        CoverageCollection.testSuiteInfo.put(CoverageCollection.testName, CoverageCollection.testCase);
    }
    
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }
}
