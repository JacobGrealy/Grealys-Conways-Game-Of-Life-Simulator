import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AllTestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(AllTestsTestSuite.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println("TEST FAILURE: " + failure.toString());
      }
      
      if(result.wasSuccessful())
      {
    	  System.out.println("All " + result.getRunCount() + " test(s) have completed Successfully.");
      }
      else
      {
    	  System.out.println(result.getFailureCount() + "/" + result.getRunCount() + " test(s) have failed, see above for more information.");
      }
   }
}