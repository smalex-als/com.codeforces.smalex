package com.codeforces.smalex;

import java.util.Scanner;

public class P602B {
  public static void main(String[] args) throws Exception {
    new P602B().run();
  }

  public void run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    int[] min = new int[n];
    int[] max = new int[n];
    int[] res = new int[n];
    int[] prevlen = new int[n];
    min[0] = a[0];
    max[0] = a[0];
    res[0] = 1;
    int maxRes = 0;
    for (int i = 1; i < n; i++) {
      if (Math.abs(a[i] - min[i - 1]) <= 1 
          && Math.abs(a[i] - max[i - 1]) <= 1) {
        res[i] = res[i - 1] + 1;
        min[i] = Math.min(a[i], min[i - 1]);
        max[i] = Math.max(a[i], max[i - 1]);
      } else if (Math.abs(a[i] - a[i - 1]) <= 1 
          && Math.abs(a[i] - a[i - 1]) <= 1) {
        res[i] = prevlen[i - 1] + 2;
        min[i] = Math.min(a[i], a[i - 1]);
        max[i] = Math.max(a[i], a[i - 1]);
      } else {
        res[i] = 1;
        min[i] = a[i];
        max[i] = a[i];
      }
      if (a[i - 1] == a[i]) {
        prevlen[i] = prevlen[i - 1] + 1;
      }
    }
    for (int i = 0 ; i < n ; i++) {
      maxRes = Math.max(res[i], maxRes);
    }
    System.out.println(maxRes);
  }
}
