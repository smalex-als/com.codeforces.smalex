package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P592A {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    String[] rows = new String[8];
    for (int i = 0; i < 8; i++) {
      rows[i] = scanner.next();
    }
    scanner.close();
    int bestW = solve(rows, 'W');
    int bestB = solve(rows, 'B');
    System.out.println(bestW <= bestB ? 'A' : 'B');
  }

  public static int solve(String[] rows, char color) {
    int best = 10;
    for (int j = 0; j < 8; j++) {
      char[] row = new char[8];
      int dest = 0;
      if (color == 'W') {
        for (int i = 0; i < 8; i++) {
          row[i] = rows[i].charAt(j);
        }
      } else {
        for (int i = 7; i >= 0; i--){
          row[dest++] = rows[i].charAt(j);
        }
      }
      for (int i = 0; i < 8; i++) {
        if (row[i] == '.') {
        } else if (row[i] == color) {
          best = Math.min(best, i);
          break;
        } else {
          break;
        }
      }
    }
    return best;
  }
}
