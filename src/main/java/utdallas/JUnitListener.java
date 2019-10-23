package utdallas;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import java.util.LinkedHashSet;
import org.junit.runner.Description;
import org.junit.runner.Result;



public class JUnitListener extends RunListener {

    public void Started(Description description) throws Exception{
        System.out.println("start tests \n"); // for checking only. will be deleted from the final build.
        CoverageTool.testSuite = new HashMap<String, HashMap<String,LinkedHashSet<Integer>>>();
    }

    // When test suite finished running.
    public  void finished(Result result) throws Exception{
        System.out.println("finished test run\n");
        try{
            FileWriter fileWriter = new FileWriter("stmt-cov.txt",true);
            StringBuffer String_buffer = new StringBuffer();
            for (String testName : CoverageCollection.testSuite.keySet()){
                String_buffer.append(testName + "\n");
                //Added the Hashmap we discussed in todays meeting 
                HashMap<String, LinkedHashSet<Integer>> test_view= CoverageCollection.testSuite.get(testName); 
                
                //Loop Goes here string buffer will need to append the the key of test_view
            }
        } catch(IOException exception){
            System.out.println("Logging error : Couldn't Log this.");
        }
    }
}
