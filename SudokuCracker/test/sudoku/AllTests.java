package sudoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Class to run all tests of the project
 * 
 * @author Tobias
 */
@RunWith(Suite.class)
@SuiteClasses( { Basics.class, SolveEasySudokus.class })
public class AllTests {
}
