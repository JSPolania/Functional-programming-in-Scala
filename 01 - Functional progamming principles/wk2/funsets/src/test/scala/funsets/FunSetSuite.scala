package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1: Set = singletonSet(1)
    val s2: Set = singletonSet(2)
    val s3: Set = singletonSet(3)
    val less7: Set = lessThanSet(7)
    val more1: Set = moreThanSet(1)
    val s1to10: Set = intervalSet(1, 10)
    val s5to15: Set = intervalSet(5, 15)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("validation of different sets") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton 1")
      assert(contains(s1to10, 7), "intervalSet s1to10 contains 7")
      assert(!contains(s1to10, 17), "intervalSet s1to10 not contains 17")
      assert(contains(less7, 5), "less7 contains 7")
      assert(!contains(less7, 9), "less7 not contains 9")
      assert(contains(more1, 5), "more1 contains 5")
      assert(!contains(more1, 0), "more1 not contains 0")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s:Set = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  test("intersection contains only elements that exits in both sets") {
    new TestSets {
      val s: Set = intersect(more1, less7)
      assert(contains(s, 5), "5 in s")
      assert(!contains(s, 11), "11 not in s")
      assert(!contains(s, -7), "-7 not in s")
    }
  }
  test("difference method tests") {
    new TestSets {
      val s: Set = intersect(more1, less7)
      val even:Set = _ % 2 == 0
      val sNotOdd: Set = diff(s,even)
      assert(contains(sNotOdd, 3), "3 is in sNotOdd")
      assert(!contains(sNotOdd, 2), "2 is not in sNotOdd")
      assert(!contains(sNotOdd, 10), "10 is not in sNotOdd")
    }
  }
  test("filter tests") {
    new TestSets {
      val s: Set = filter(s1to10, x => x % 2 != 0)
      assert(contains(s,3), "3 is in s")
      assert(!contains(s,2), "2 not in s")
    }
  }
  test("forall tests") {
    new TestSets {
      assert(forall(s3, s1to10), "true, 3 in s1to10")
      assert(forall(s5to15,s5to15), "s is true for itself")
      assert(!forall(s1to10, s5to15), "s1to10 not  satisfy s5to10 ")
      val s20to30:Set = intervalSet(20,30)
      assert(!forall(s1to10, s20to30), "no element in s1to10 belongs to s20to30")
    }
  }
  test("exits tests") {
    new TestSets {
      assert(exists(s3, s1to10), "true, 3 in s1to10")
      assert(exists(s5to15,s5to15), "s is true for itself")
      assert(exists(s1to10, s5to15), "s1to10 not  satisfy s5to10 ")
      val s20to30:Set = intervalSet(20,30)
      assert(!exists(s1to10, s20to30), "no element in s1to10 belongs to s20to30")
    }
  }
}
