package sudoku;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.SetException;
import sudoku.exceptions.SolveException;

/**
 * Advanced solving algorithms tests
 * 
 * @author Tim
 */
public class SudokuAdvancedSolving {

  private Sudoku sudoku;

  /**
   * Produces an empty Sudoku instance for all tests.
   */
  @Before
  public void initialiseSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumnOne() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 3, 9);
    sudoku.set(5, 2, 4);
    sudoku.set(6, 2, 6);

    sudoku.addMissingNumberWithColumnCombination(2, 5);
    assertEquals(7, sudoku.get(2, 5));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumnTwo() throws SetException, SolveException {
    sudoku.set(7, 2, 1);
    sudoku.set(7, 3, 9);
    sudoku.set(5, 1, 4);
    sudoku.set(6, 1, 6);

    sudoku.addMissingNumberWithColumnCombination(1, 5);
    assertEquals(7, sudoku.get(1, 5));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumnThree() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(5, 3, 4);
    sudoku.set(6, 3, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 5);
    assertEquals(7, sudoku.get(3, 5));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumnFour() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(5, 3, 5);
    sudoku.set(6, 3, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 4);
    assertEquals(7, sudoku.get(3, 4));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumnFife() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(5, 3, 4);
    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);
    assertEquals(7, sudoku.get(3, 6));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * This one is not
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfColumnSix() throws SetException, SolveException {
    sudoku.set(7, 2, 9);
    sudoku.set(5, 3, 4);
    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);

  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * This one is not
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfColumnSeven() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(5, 3, 4);
    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);

  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * This one is not
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfColumnEight() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);

    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);
    assertEquals(0, sudoku.get(3, 6));
  }

  /**
   * If there is one free field in the Column-Combination, it is maybe solvable
   * This one is not
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfColumnNine() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(5, 3, 4);

    sudoku.addMissingNumberWithColumnCombination(3, 6);
    assertEquals(0, sudoku.get(3, 6));
  }

  /**
   * If there are two free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfColumnOne() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 6);
    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 4);
    assertEquals(7, sudoku.get(3, 4));
  }

  /**
   * If there are two free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfColumnTwo() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 4);
    sudoku.set(6, 3, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);
    assertEquals(7, sudoku.get(3, 6));
  }

  /**
   * If there are two free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfColumnThree() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 4);
    sudoku.set(6, 3, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 5);
    assertEquals(7, sudoku.get(3, 5));
  }

  /**
   * If there are two free fields in the Column-Combination, it is maybe
   * solvable THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveDoubleFieldOfColumnFour() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 4);

    sudoku.addMissingNumberWithColumnCombination(3, 5);
  }

  /**
   * If there are two free fields in the Column-Combination, it is maybe
   * solvable THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveDoubleFieldOfColumnFife() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 5, 4);
    sudoku.set(6, 3, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 5);
  }

  /**
   * If there are three free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfColumnOne() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 4);
    sudoku.set(7, 7, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 6);
    assertEquals(7, sudoku.get(3, 6));
  }

  /**
   * If there are three free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfColumnTwo() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 4);
    sudoku.set(7, 7, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 5);
    assertEquals(7, sudoku.get(3, 5));
  }

  /**
   * If there are three free fields in the Column-Combination, it is maybe
   * solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfColumnThree() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 5);
    sudoku.set(7, 7, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 4);
    assertEquals(7, sudoku.get(3, 4));
  }

  /**
   * If there are three free fields in the Column-Combination, it is maybe
   * solvable THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveTripleFieldOfColumnFour() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 5);

    sudoku.addMissingNumberWithColumnCombination(3, 4);
    assertEquals(0, sudoku.get(3, 4));
  }

  /**
   * If there are three free fields in the Column-Combination, it is maybe
   * solvable THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveTripleFieldOfColumnFife() throws SetException, SolveException {

    sudoku.set(7, 2, 9);
    sudoku.set(7, 5, 5);
    sudoku.set(7, 7, 6);

    sudoku.addMissingNumberWithColumnCombination(3, 4);
    assertEquals(0, sudoku.get(3, 4));
  }

  /**
   * Test column-combination with help of the "very Easy" sudoku
   * 
   * @throws SetException
   * @throws SolveException
   * 
   * @throws InternalException
   */
  @Test(expected = SolveException.class)
  public void solveVeryEasySudokuColumnTest() throws SetException, SolveException {
    sudoku.set(3, 1, 1);
    sudoku.set(1, 2, 1);
    sudoku.set(6, 7, 1);
    sudoku.set(5, 9, 1);
    sudoku.set(6, 2, 2);
    sudoku.set(5, 3, 2);
    sudoku.set(2, 4, 2);
    sudoku.set(9, 8, 2);
    sudoku.set(1, 9, 2);
    sudoku.set(9, 1, 3);
    sudoku.set(1, 4, 3);
    sudoku.set(5, 5, 3);
    sudoku.set(2, 8, 3);
    sudoku.set(8, 4, 4);
    sudoku.set(5, 6, 4);
    sudoku.set(7, 7, 4);
    sudoku.set(4, 8, 4);
    sudoku.set(1, 3, 5);
    sudoku.set(9, 5, 5);
    sudoku.set(5, 7, 5);
    sudoku.set(4, 2, 6);
    sudoku.set(8, 3, 6);
    sudoku.set(7, 4, 6);
    sudoku.set(2, 6, 6);
    sudoku.set(3, 2, 7);
    sudoku.set(7, 5, 7);
    sudoku.set(1, 6, 7);
    sudoku.set(6, 9, 7);
    sudoku.set(1, 1, 8);
    sudoku.set(2, 2, 8);
    sudoku.set(3, 6, 8);
    sudoku.set(9, 7, 8);
    sudoku.set(8, 8, 8);
    sudoku.set(7, 1, 9);
    sudoku.set(9, 3, 9);
    sudoku.set(3, 8, 9);
    sudoku.set(4, 9, 9);

    sudoku.addMissingNumberWithColumnCombination(3, 1);

  }

  /**
   * Test column-combinatioen with help of the "just easy" sudoku
   * 
   * @throws SetException
   * @throws SolveException
   * 
   * @throws InternalException
   */
  @Test
  public void solveJustEasySudokuColumnTest() throws SetException, SolveException {
    sudoku.set(9, 5, 1);
    sudoku.set(8, 7, 1);
    sudoku.set(6, 8, 1);
    sudoku.set(4, 1, 2);
    sudoku.set(2, 3, 2);
    sudoku.set(5, 4, 2);
    sudoku.set(8, 6, 2);
    sudoku.set(1, 1, 3);
    sudoku.set(7, 6, 3);
    sudoku.set(5, 8, 3);
    sudoku.set(7, 3, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 8, 4);
    sudoku.set(2, 1, 5);
    sudoku.set(6, 4, 5);
    sudoku.set(3, 6, 5);
    sudoku.set(1, 9, 5);
    sudoku.set(4, 2, 6);
    sudoku.set(8, 5, 6);
    sudoku.set(6, 7, 6);
    sudoku.set(9, 2, 7);
    sudoku.set(8, 4, 7);
    sudoku.set(2, 9, 7);
    sudoku.set(4, 6, 8);
    sudoku.set(5, 7, 8);
    sudoku.set(9, 9, 8);
    sudoku.set(3, 2, 9);
    sudoku.set(4, 3, 9);
    sudoku.set(7, 5, 9);
    sudoku.set(6, 9, 9);

    sudoku.addMissingNumberWithColumnCombination(4, 6);
    assertEquals(7, sudoku.get(4, 6));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfRowOne() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 9, 3);
    sudoku.set(5, 4, 2);
    sudoku.set(6, 5, 2);

    sudoku.addMissingNumberWithRowCombination(6, 2);
    assertEquals(7, sudoku.get(6, 2));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfRowTwo() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 9, 3);
    sudoku.set(5, 4, 2);
    sudoku.set(6, 6, 2);

    sudoku.addMissingNumberWithRowCombination(5, 2);
    assertEquals(7, sudoku.get(5, 2));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfRowThree() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 9, 3);
    sudoku.set(5, 5, 2);
    sudoku.set(6, 6, 2);

    sudoku.addMissingNumberWithRowCombination(4, 2);
    assertEquals(7, sudoku.get(4, 2));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfRowFour() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 5, 3);
    sudoku.set(6, 6, 3);

    sudoku.addMissingNumberWithRowCombination(4, 3);
    assertEquals(7, sudoku.get(4, 3));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfRowFife() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);

    sudoku.addMissingNumberWithRowCombination(4, 1);
    assertEquals(7, sudoku.get(4, 1));
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfRowSix() throws SetException, SolveException {

    sudoku.set(7, 9, 2);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);

    sudoku.addMissingNumberWithRowCombination(4, 1);
  }

  /**
   * If there is one free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveSingleFieldOfRowSeven() throws SetException, SolveException {

    sudoku.set(7, 1, 3);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);

    sudoku.addMissingNumberWithRowCombination(4, 1);
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfRowOne() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 4, 1);
    sudoku.set(7, 6, 5);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfRowTwo() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 6, 1);
    sudoku.set(7, 4, 5);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfRowThree() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 6, 1);
    sudoku.set(7, 5, 5);

    sudoku.addMissingNumberWithRowCombination(4, 1);
    assertEquals(7, sudoku.get(4, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveDoubleFieldOfRowFour() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 4, 1);
    sudoku.set(7, 5, 5);

    sudoku.addMissingNumberWithRowCombination(6, 1);
    assertEquals(7, sudoku.get(6, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveDoubleFieldOfRowFife() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 4, 1);

    sudoku.addMissingNumberWithRowCombination(6, 1);
    assertEquals(7, sudoku.get(6, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveDoubleFieldOfRowSix() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(5, 4, 1);
    sudoku.set(7, 6, 5);

    sudoku.addMissingNumberWithRowCombination(6, 1);
    assertEquals(7, sudoku.get(6, 1));
  }

  /**
   * If there are two free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveDoubleFieldOfRowSeven() throws SetException, SolveException {

    sudoku.set(7, 9, 2);
    sudoku.set(5, 4, 1);
    sudoku.set(7, 5, 5);

    sudoku.addMissingNumberWithRowCombination(6, 1);
    assertEquals(7, sudoku.get(6, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfRowOne() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);

    sudoku.set(7, 4, 4);
    sudoku.set(7, 5, 7);

    sudoku.addMissingNumberWithRowCombination(6, 1);
    assertEquals(7, sudoku.get(6, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfRowTwo() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);

    sudoku.set(7, 5, 4);
    sudoku.set(7, 6, 7);

    sudoku.addMissingNumberWithRowCombination(4, 1);
    assertEquals(7, sudoku.get(4, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveTripleFieldOfRowThree() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);

    sudoku.set(7, 4, 4);
    sudoku.set(7, 6, 7);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveTripleFieldOfRowFour() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);

    sudoku.set(7, 4, 4);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveTripleFieldOfRowFife() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(7, 5, 1);
    sudoku.set(7, 4, 4);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If there are three free field in the Row-Combination, it is maybe solvable
   * THIS IS NOT
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveTripleFieldOfRowSix() throws SetException, SolveException {
    sudoku.set(7, 1, 3);
    sudoku.set(7, 9, 2);
    sudoku.set(7, 6, 7);

    sudoku.addMissingNumberWithRowCombination(5, 1);
    assertEquals(7, sudoku.get(5, 1));
  }

  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareOne() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 4, 5);
    sudoku.set(3, 4, 6);
    sudoku.set(7, 6, 4);
    
    sudoku.set(9, 5, 2);
    sudoku.set(9, 8, 5);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 6);
    assertEquals(9, sudoku.get(6, 6));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * THIS IS NOT SOLVEABLE
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test (expected = SolveException.class)
  public void solveRowColumnCrossInSquareTwo() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 4, 5);
    sudoku.set(3, 4, 6);
    sudoku.set(7, 6, 4);
    
    sudoku.set(9, 5, 2);
    sudoku.set(9, 8, 5);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 5);
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareThree() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 4, 5);
    sudoku.set(3, 4, 6);
    sudoku.set(7, 6, 6);
    
    sudoku.set(9, 5, 2);
    sudoku.set(9, 8, 5);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 4);
    assertEquals(9, sudoku.get(6, 4));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareFour() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 4, 5);
    sudoku.set(3, 4, 6);
    sudoku.set(7, 6, 6);
    
    sudoku.set(9, 5, 2);
    sudoku.set(9, 8, 4);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 5);
    assertEquals(9, sudoku.get(6, 5));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareFife() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 4, 5);
    sudoku.set(3, 4, 6);
    sudoku.set(7, 6, 5);
    
    sudoku.set(9, 5, 2);
    sudoku.set(9, 8, 4);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 6);
    assertEquals(9, sudoku.get(6, 6));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareSix() throws SetException, SolveException {
    sudoku.set(1, 5, 4);
    sudoku.set(2, 5, 5);
    sudoku.set(3, 5, 6);
    sudoku.set(7, 6, 5);
    
    sudoku.set(9, 4, 2);
    sudoku.set(9, 8, 4);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 6);
    assertEquals(9, sudoku.get(6, 6));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareSeven() throws SetException, SolveException {
    sudoku.set(1, 5, 4);
    sudoku.set(2, 5, 5);
    sudoku.set(3, 5, 6);
    sudoku.set(7, 6, 4);
    
    sudoku.set(9, 4, 2);
    sudoku.set(9, 8, 6);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 5);
    assertEquals(9, sudoku.get(6, 5));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareEight() throws SetException, SolveException {
    sudoku.set(1, 6, 4);
    sudoku.set(2, 6, 5);
    sudoku.set(3, 6, 6);
    sudoku.set(7, 5, 4);
    
    sudoku.set(9, 4, 2);
    sudoku.set(9, 8, 6);

    sudoku.addMissingNumberInSquareWithRowColumnCross(5, 5);
    assertEquals(9, sudoku.get(5, 5));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareNine() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 6, 4);
    sudoku.set(7, 5, 6);
    
    sudoku.set(9, 4, 2);
    sudoku.set(9, 8, 5);

    sudoku.addMissingNumberInSquareWithRowColumnCross(6, 6);
    assertEquals(9, sudoku.get(6, 6));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareTen() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 6, 4);
    sudoku.set(7, 5, 5);
    
    sudoku.set(9, 6, 2);
    sudoku.set(9, 8, 6);

    sudoku.addMissingNumberInSquareWithRowColumnCross(4, 5);
    assertEquals(9, sudoku.get(4, 5));
  }
  
  /**
   * If a row and a column (containing the same number) cross a square so that
   * there is only one field left, the number has to be set there
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveRowColumnCrossInSquareEleven() throws SetException, SolveException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 6, 4);
    sudoku.set(7, 4, 5);
   
    sudoku.set(9, 6, 2);
    sudoku.set(9, 8, 6);

    sudoku.addMissingNumberInSquareWithRowColumnCross(5, 5);
    assertEquals(9, sudoku.get(5, 5));
  }
  
  /**
   * Get all missing numbers of a field, regarding row, column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllPossibleMissingNumbersOne() throws SetException, SolveException {
    sudoku.set(1, 2, 2);
    sudoku.set(2, 3, 3);
    sudoku.set(3, 1, 4);
    sudoku.set(4, 4, 1);
   
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);

    assertEquals(tmpExpectedSet, sudoku.getAllPossibleMissingNumbers(1, 1));
  }
  
  /**
   * Get all missing numbers of a field, regarding row, column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllPossibleMissingNumbersTwo() throws SetException, SolveException {
    sudoku.set(1, 1, 3);    
    
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
   
    
    sudoku.set(8, 3, 8);
    
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);

    assertEquals(tmpExpectedSet, sudoku.getAllPossibleMissingNumbers(3, 3));
  }
  
  /**
   * Get all existing numbers of a field, regarding row, column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllExistingNumbersOne() throws SetException, SolveException {
    sudoku.set(1, 2, 2);
    sudoku.set(2, 3, 3);
    sudoku.set(3, 1, 4);
    sudoku.set(4, 4, 1);
   
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);

    assertEquals(tmpExpectedSet, sudoku.getAllExistingNumbers(1, 1));
  }
  
  /**
   * Get all existing numbers of a field, regarding row, column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllExistingNumbersTwo() throws SetException, SolveException {
    sudoku.set(1, 1, 3);    
    
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
   
    
    sudoku.set(8, 3, 8);
    
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getAllExistingNumbers(3, 3));
  }
  
  /**
   * Get all missing numbers of two fields in a row, regarding also column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllMissingNumbersInARowWithTwoEmptyFields() throws SetException, SolveException {
    sudoku.set(1, 1, 3);
    sudoku.set(2, 2, 3);
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    sudoku.set(6, 6, 3);
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
   
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(8);

    assertEquals(tmpExpectedSet, sudoku.getAllMissingNumbersInCombinationOfARow(3));
  }
  
  /**
   * Get all missing numbers of three fields in a row, regarding also column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllMissingNumbersInARowWithThreeEmptyFields() throws SetException, SolveException {
    sudoku.set(1, 1, 3);
    sudoku.set(2, 2, 3);
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
      
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(8);

    assertEquals(tmpExpectedSet, sudoku.getAllMissingNumbersInCombinationOfARow(3));
  }
  
  /**
   * Get all missing numbers of four fields in a row, regarding also column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllMissingNumbersInARowWithFourEmptyFields() throws SetException, SolveException {
    sudoku.set(1, 1, 3);
    
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
      
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(8);

    assertEquals(tmpExpectedSet, sudoku.getAllMissingNumbersInCombinationOfARow(3));
  }
  
  /**
   * Get all missing numbers of four fields in a row, regarding also column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllMissingNumbersInARowWithFourEmptyFieldsWithAnotherNumberOne() throws SetException, SolveException {
    sudoku.set(1, 1, 3);
    
    
    sudoku.set(4, 4, 3);
    sudoku.set(5, 5, 3);
    
    sudoku.set(7, 7, 3);
    
    sudoku.set(9, 9, 3);
   
    sudoku.set(8, 3, 8);
    
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);

    assertEquals(tmpExpectedSet, sudoku.getAllMissingNumbersInCombinationOfARow(3));
  }
  
  /**
   * Get all missing numbers of four fields in a row, regarding also column and square!
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void getAllMissingNumbersInARowWithFourEmptyFieldsWithAnotherNumberTwo() throws SetException, SolveException {
    sudoku.set(9, 5, 1);
    sudoku.set(4, 1, 2);
    sudoku.set(2, 3, 2);
    sudoku.set(5, 4, 2);
    sudoku.set(8, 6, 2);
    sudoku.set(1, 1, 3);
    sudoku.set(8, 2, 3);
    sudoku.set(9, 3, 3);
    sudoku.set(7, 6, 3);
    sudoku.set(2, 7, 3);
    sudoku.set(5, 8, 3);
    sudoku.set(7, 3, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 8, 4);
    sudoku.set(2, 1, 5);
    sudoku.set(6, 4, 5);
    sudoku.set(3, 6, 5);
    sudoku.set(1, 9, 5);
    
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);

    assertEquals(tmpExpectedSet, sudoku.getAllMissingNumbersInCombinationOfARow(3));
  }
  
  /**
   * Get all missing numbers of multiple fields in a row, regarding also column and square!
   * With help of "just easy" sudoku
   * 
   * @throws SetException
   * @throws SolveException
   */
  @Test
  
  public void solveRowWithMultipleMissingFieldsOne() throws SetException, SolveException {
    sudoku.set(9, 5, 1);
    sudoku.set(8, 7, 1);
    sudoku.set(6, 8, 1);
    sudoku.set(4, 1, 2);
    sudoku.set(2, 3, 2);
    sudoku.set(5, 4, 2);
    sudoku.set(8, 6, 2);
    sudoku.set(1, 1, 3);
    sudoku.set(8, 2, 3);
    sudoku.set(9, 3, 3);
    sudoku.set(7, 6, 3);
    sudoku.set(2, 7, 3);
    sudoku.set(5, 8, 3);
    sudoku.set(7, 3, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 8, 4);
    sudoku.set(2, 1, 5);
    sudoku.set(6, 4, 5);
    sudoku.set(3, 6, 5);
    sudoku.set(1, 9, 5);
    sudoku.set(4, 2, 6);
    sudoku.set(7, 4, 6);
    sudoku.set(8, 5, 6);
    sudoku.set(6, 7, 6);
    sudoku.set(2, 8, 6);
    sudoku.set(9, 2, 7);
    sudoku.set(8, 4, 7);
    sudoku.set(3, 7, 7);
    sudoku.set(4, 8, 7);
    sudoku.set(2, 9, 7);
    sudoku.set(2, 2, 8);
    sudoku.set(4, 6, 8);
    sudoku.set(5, 7, 8);
    sudoku.set(9, 9, 8);
    sudoku.set(3, 2, 9);
    sudoku.set(4, 3, 9);
    sudoku.set(7, 5, 9);

    sudoku.addMissingRowNumberInMultipleMissingFields(5, 3);
    assertEquals(6, sudoku.get(5, 3));
  }
}
