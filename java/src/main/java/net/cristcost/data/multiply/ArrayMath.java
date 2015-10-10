package net.cristcost.data.multiply;

import java.util.Arrays;

public class ArrayMath {

  public static int[] multiply(int[] a, int[] b) {
    int[] ret = new int[] { 0 };
    // multiply a foreach item in b
    for (int i : b) {
      for (int j = 0; j < i; j++) {
        ret = sum(ret, a);
      }
      a = shift(a);
    }
    return stripZeros(ret);
  }

  public static int[] shift(int[] array) {
    int[] ret = new int[array.length + 1];
    for (int i = 0; i < array.length; i++) {
      ret[i + 1] = array[i];
    }
    return ret;
  }

  public static int[] sum(int[] a, int[] b) {
    boolean carry = false;
    int[] ret = new int[Math.max(a.length, b.length) + 1];
    for (int i = 0; i < ret.length; i++) {
      int partialSum = valueOrZero(a, i) + valueOrZero(b, i) + (carry ? 1 : 0);
      if (partialSum >= 10) {
        carry = true;
        partialSum -= 10;
      } else {
        carry = false;
      }
      ret[i] = partialSum;
    }
    return stripZeros(ret);
  }

  private static int[] stripZeros(int[] array) {
    for (int i = array.length - 1; i > 0; i--) {
      if (array[i] != 0) {
        return Arrays.copyOfRange(array, 0, i + 1);
      }
    }
    return new int[] { 0 }; // if zero, return array with one zero
  }

  private static int valueOrZero(int[] a, int i) {
    if (i < a.length) {
      return a[i];
    } else
      return 0;
  }
}
