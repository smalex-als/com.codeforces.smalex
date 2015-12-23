package com.codeforces.smalex;

import java.util.Scanner;

public class P604A {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] x = new int[]{500, 1000, 1500, 2000, 2500};
    int[] m = new int[5];
    int[] w = new int[5];
    for (int i = 0 ; i < 5; i++){
      m[i] = scanner.nextInt();
    }
    for (int i = 0 ; i < 5; i++){
      w[i] = scanner.nextInt();
    }
    int hs = scanner.nextInt();
    int hu = scanner.nextInt();


    int res = 0;
    for (int i = 0; i < 5; i++) {
      int res2 = 250 * x[i] - x[i] * m[i] - 250 * 50 * w[i];
      res += Math.max(75 * x[i], res2);
    }
    res += 100 * 250 * hs - 50 * 250 * hu; 
    System.out.println(res / 250);
  }
}
