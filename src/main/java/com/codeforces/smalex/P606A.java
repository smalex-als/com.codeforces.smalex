package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P606A {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] a = new int[3];
    a[0] = scanner.nextInt();
    a[1] = scanner.nextInt();
    a[2] = scanner.nextInt();
    int[] b = new int[3];
    b[0] = scanner.nextInt();
    b[1] = scanner.nextInt();
    b[2] = scanner.nextInt();

    for (int i = 0; i < 3; i++) {
      a[i] -= b[i];
    }

    Arrays.sort(a);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3 && a[i] < 0; j++) {
        if (i != j && a[j] >= 2) {
          int add = Math.min(a[j] / 2, a[i] * -1);
          a[i] += add;
          a[j] -= add * 2;
        }
      }
    }
    for (int i = 0; i < 3; i++) {
      if (a[i] < 0) {
        System.out.println("No");
        return;
      }
    }
    System.out.println("Yes");
  }
}
