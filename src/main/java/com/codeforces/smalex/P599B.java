package com.codeforces.smalex;

import java.util.Scanner;

public class P599B {
  public static void main(String[] args) {
    new P599B().run();
  }
  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] f = new int[n];
    int[] b = new int[m];
    int[] fsearch = new int[n + 2];
    int[] fsearchcnt = new int[n + 2];
    for (int i = 0; i < n; i++) {
      f[i] = scanner.nextInt();
      fsearch[f[i]] = i;
      fsearchcnt[f[i]]++;
    }
    for (int i = 0; i < m; i++) {
      b[i] = scanner.nextInt();
    }
    int[] a = new int[m];
    boolean ambiguity = false;
    for (int i = 0; i < m; i++) {
      int num = b[i];
      if (fsearchcnt[num] == 0) {
        System.out.println("Impossible");
        return;
      }
      int index = fsearch[num];
      a[i] = index + 1;
      if (fsearchcnt[num] > 1) {
        ambiguity = true;
      }
    }
    if (ambiguity) {
      System.out.println("Ambiguity");
    } else {
      System.out.println("Possible");
      for (int i : a) {
        System.out.print(i + " ");
      }
    }
  }

}
