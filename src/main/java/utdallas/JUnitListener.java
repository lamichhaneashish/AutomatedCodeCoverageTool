package utdallas;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.junit.runner.notification.RunListener;

import java.util.HashSet;
import java.util.LinkedHashSet;
import org.junit.runner.Description;
import org.junit.runner.Result;
import sun.tools.asm.Cover;


public class JUnitListener extends RunListener {

    public void testRunStarted(Description description) throws Exception{
        System.out.println("start tests \n"); // for checking only. will be deleted from the final build.
        if (CoverageTool.testSuite == null){
            CoverageTool.testSuite = new HashMap<String,HashMap<String,LinkedHashSet<Integer>>>();

        }
    }

    public void testStarted(Description description){
        System.out.println("\n-----\nStarting - " + description.getMethodName());
        CoverageTool.testName = "[TEST]" + description.getClassName() + ":" + description.getMethodName();
        CoverageTool.testCase = new HashMap<String, LinkedHashSet<Integer>>();
    }


    public void testFinished(Description description){
        System.out.println("Finished - " + description.getMethodName());
        CoverageTool.testSuite.put(CoverageTool.testName, CoverageTool.testCase);
    }
    // When test suite finished running.
    public  void testRunFinished(Result result) throws Exception{
        System.out.println("finished test run\n");
        try{
            FileWriter fileWriter = new FileWriter("stmt-cov.txt",true);
            StringBuffer String_buffer = new StringBuffer();
            for (String testName : CoverageTool.testSuite.keySet()){
                String_buffer.append(testName + "\n");
                //Added the Hashmap we discussed in todays meeting
                HashMap<String, LinkedHashSet<Integer>> test_view= CoverageTool.testSuite.get(testName);

                //Loop Goes here string buffer will need to append the the key of test_view
                for (String className : test_view.keySet()){
                    for (Integer i : test_view.get(className)){
                        String_buffer.append(className+":"+i+"\n");
                    }
                }
            }
            fileWriter.write(String_buffer.toString());
            fileWriter.close();
        } catch(IOException exception){
            System.out.println("Logging error : Couldn't Log this.");
        }
    }
}
