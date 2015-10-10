/*
 * Copyright 2015, Cristiano Costantini
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.cristcost.data.multiply;

public class MultiplyApp {

  public static void main(String[] args) {
    if (args.length != 2) {
      dumpUsage(null);
    }
    if (!Util.validateString(args[0])) {
      dumpUsage("Invalid operand 1");
    }
    if (!Util.validateString(args[1])) {
      dumpUsage("Invalid operand 2");
    }

    int[] a = Util.stringToArray(args[0]);
    int[] b = Util.stringToArray(args[1]);

    int[] result = ArrayMath.multiply(a, b);

    System.out.println(Util.arrayToString(result));
  }

  private static void dumpUsage(String error) {
    if (error != null) {
      System.out.println("Error: " + error);
      System.out.println();
    }
    System.out.println("Usage:   multiply operand1 operand2");
    System.out.println("Example: multiply 123 321");
    System.exit(0);
  }
}
