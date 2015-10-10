package net.cristcost.data.multiply;

public class Util {

  private Util() {
  }

  public static boolean validateArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] < 0 || array[i] > 9) {
        return false;
      }
    }
    return true;
  }

  public static boolean validateString(String string) {
    return string.matches("^[0-9]*$");
  }

  public static String arrayToString(int[] array) {
    StringBuilder sb = new StringBuilder();
    for (int i = array.length - 1; i >= 0; i--) {
      sb.append(array[i]);
    }
    return sb.toString();
  }

  public static int[] stringToArray(String number) {
    int length = number.length();
    int[] ret = new int[length];
    for (int i = 0; i < length; i++) {
      ret[length - i - 1] = Integer.parseInt(Character.toString(number.charAt(i)));
    }
    return ret;
  }
}
