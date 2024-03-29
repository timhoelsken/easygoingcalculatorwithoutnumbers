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
@SuiteClasses( { SudokuBasics.class, SquareTest.class, SudokuBasicSolving.class, SudokuAdvancedSolving.class, SolveEasySudokus.class, SolveHardSudokus.class })
public class AllTests {
}
