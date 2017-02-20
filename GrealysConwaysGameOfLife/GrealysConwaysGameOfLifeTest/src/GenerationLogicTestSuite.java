import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Run with JUnit Suite Test
@RunWith(Suite.class)

//Run all @test methods in the following classes
@Suite.SuiteClasses({ 
   LiveCellTest.class, DeadCellTest.class
})

//Class for testing both live and dead cells, ensuring that our gameplay logic works for both!
public class GenerationLogicTestSuite {
}