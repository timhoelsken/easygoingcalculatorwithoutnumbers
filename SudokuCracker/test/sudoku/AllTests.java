/**
 * @author Tobe
 */
package sudoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Class to run all tests of the project
 */
@RunWith(Suite.class)
@SuiteClasses({BasicSudokuTest.class, FieldTest.class})
public class AllTests {
}
