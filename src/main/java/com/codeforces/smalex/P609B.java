package com.codeforces.smalex; 

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class P609B {
  public static String testString = "7 4 4 2 3 1 2 4 3";

  public static void main(String[] args) {
    BufferedInputStream in = new BufferedInputStream(
        new ByteArrayInputStream(testString.getBytes()));
    Scanner scanner = new Scanner(in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < n; i++) {
      b[scanner.nextInt() - 1]++;
    }
    int res = 0;
    
    for (int i = 2; i < (1 << m); i++) {
      if (Integer.bitCount(i) == 2) {
        int cur = 1;
        for (int j = 0; j < m; j++)  {
          if (((1 << j) & i) != 0) {
            cur *= b[j];
          }
        }
        res += cur;
      }
    }
    System.out.println(res);
  }
}
