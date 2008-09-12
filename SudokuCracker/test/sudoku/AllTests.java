package sudoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { Basics.class, SquareTest.class, SolveEasySudokus.class })
/**
 * Class to run all tests of the project
 * @author Tobias
 */
public class AllTests {
}
