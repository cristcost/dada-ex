package net.cristcost.dada.multiply;

import net.cristcost.dada.multiply.Util;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;

public class UtilTest {

  @Test
  public void testArrayToString() {
    assertEquals("123", Util.arrayToString(new int[] { 3, 2, 1 }));
    assertEquals("", Util.arrayToString(new int[] {}));
    assertEquals("000", Util.arrayToString(new int[] { 0, 0, 0 }));

    // validation is not a responsibility of arrayToString
    assertEquals("-123", Util.arrayToString(new int[] { 3, 2, -1 }));
    assertEquals("123", Util.arrayToString(new int[] { 123 }));
  }

  @Test
  public void testStringToArray() {
    assertTrue(Arrays.equals(new int[] { 3, 2, 1 }, Util.stringToArray("123")));
    assertTrue(Arrays.equals(new int[] {}, Util.stringToArray("")));
    assertTrue(Arrays.equals(new int[] { 0, 0, 0 }, Util.stringToArray("000")));

    try {
      Util.stringToArray("-123");
      fail("Must throw NumberFormatException");
    } catch (NumberFormatException e) {
    }
    try {
      Util.stringToArray(" 123");
      fail("Must throw NumberFormatException");
    } catch (NumberFormatException e) {
    }
    try {
      Util.stringToArray("centoventitre");
      fail("Must throw NumberFormatException");
    } catch (NumberFormatException e) {
    }

  }

  @Test
  public void testValidateArray() {
    assertTrue(Util.validateArray(new int[] { 3, 2, 1 }));
    assertTrue(Util.validateArray(new int[] {}));
    assertTrue(Util.validateArray(new int[] { 0, 0, 0 }));

    assertFalse(Util.validateArray(new int[] { 3, 2, -1 }));
    assertFalse(Util.validateArray(new int[] { 123 }));
  }

  @Test
  public void testValidateString() {
    assertTrue(Util.validateString("123"));
    assertTrue(Util.validateString(""));
    assertTrue(Util.validateString("000"));

    assertFalse(Util.validateString("-123")); // signed integers unsupported

    assertFalse(Util.validateString(" 123"));
    assertFalse(Util.validateString("centoventitre"));
  }
}
