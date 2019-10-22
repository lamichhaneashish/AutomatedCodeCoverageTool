package utdallas;


import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class JUnitListener extends RunListener {

    public void Started(Description description) throws Exception{
        System.out.println("Test Run Started\n"); // for checking only. will be deleted from the final build.
        CoverageTool.testSuite = new HashMap<String, HashMap<String,LinkedHashSet<Integer>>>();
    }

    // When test suite finished running.
    public  void finished(Result result) throws Exception{
        System.out.println("Test Run Finished\n");
        try{
            FileWriter fileWriter = new FileWriter("stmt-cov.txt",true);
            StringBuffer string_buffer = new StringBuffer();
            for (String testName : CoverageTool.testSuite.keySet()){
                string_buffer.append(testName + "\n");
            }
        } catch(IOException exception){
            System.out.println("Logging error : Couldn't Log this.");
        }
    }
}
