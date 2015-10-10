package net.cristcost.data.multiply;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayMathTest {

  @Test
  public void testArraySum() {
    assertEquals("160", sumWithStrings("130", "030"));
    assertEquals("100", sumWithStrings("", "100"));
    assertEquals("0", sumWithStrings("", ""));
    assertEquals("198", sumWithStrings("99", "99"));
  }

  @Test
  public void testMultiply() {
    assertEquals("10", multiplyWithStrings("10", "1"));
    assertEquals("20", multiplyWithStrings("10", "2"));
    assertEquals("100", multiplyWithStrings("10", "10"));
    assertEquals("0", multiplyWithStrings("10", "0"));
    assertEquals("0", multiplyWithStrings("10", ""));

    // inverted operands
    assertEquals("10", multiplyWithStrings("1", "10"));
    assertEquals("20", multiplyWithStrings("2", "10"));
    assertEquals("100", multiplyWithStrings("10", "10"));
    assertEquals("0", multiplyWithStrings("0", "10"));
    assertEquals("0", multiplyWithStrings("", "10"));

    // bigger operands
    assertEquals("65536", multiplyWithStrings("256", "256"));

    // biggest operands
    assertEquals("4611686014132420609",
        multiplyWithStrings(Integer.toString(Integer.MAX_VALUE),
            Integer.toString(Integer.MAX_VALUE)));

    // bigger biggest operands
    assertEquals("85070591730234615847396907784232501249",
        multiplyWithStrings(Long.toString(Long.MAX_VALUE),
            Long.toString(Long.MAX_VALUE)));
  }

  @Test
  public void testShift() {
    assertEquals("120", shiftWithStrings("12"));
    assertEquals("0", shiftWithStrings(""));
  }

  private String multiplyWithStrings(String a, String b) {
    int[] firstOperand = Util.stringToArray(a);
    int[] secondOperand = Util.stringToArray(b);
    int[] multiplication = ArrayMath.multiply(firstOperand, secondOperand);
    return Util.arrayToString(multiplication);
  }

  private String shiftWithStrings(String string) {
    int[] operand = Util.stringToArray(string);
    int[] ret = ArrayMath.shift(operand);
    return Util.arrayToString(ret);
  }

  private String sumWithStrings(String a, String b) {
    int[] firstOperand = Util.stringToArray(a);
    int[] secondOperand = Util.stringToArray(b);
    int[] sum = ArrayMath.sum(firstOperand, secondOperand);
    return Util.arrayToString(sum);
  }
}
