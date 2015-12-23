package com.codeforces.smalex;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P609C {
  public static String testString = "5\n" +
      "1 2 3 4 5";

  public static void main(String[] args) {
    BufferedInputStream in = new BufferedInputStream(
        new ByteArrayInputStream(testString.getBytes()));
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    long sum = 0;
    int[] cnt = new int[22222];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
      sum += a[i];
      cnt[a[i]]++;
    }
    Arrays.sort(a);
    long avg0 = sum / n;
    long avg1Cnt = sum - (avg0 * n);
    long avg0Cnt = n - avg1Cnt;
    long avg1 = avg0 + 1;
    long res = 0;
    for (int i = 1; i < cnt.length; i++) {
      if (cnt[i] == 0) {
        continue;
      }
      while (cnt[i] > 0) {
        if (avg1Cnt > 0 && avg0Cnt > 0) {
          if (Math.abs(avg0 - i) < Math.abs(avg1 - i)) {
            res += Math.abs(avg0 - i);
            avg0Cnt--;
          } else {
            res += Math.abs(avg1 - i);
            avg1Cnt--;
          }
        } else if (avg0Cnt > 0) {
          res += Math.abs(avg0 - i);
          avg0Cnt--;
        } else if (avg1Cnt > 0) {
          res += Math.abs(avg1 - i);
          avg1Cnt--;
        }
        cnt[i]--;
      }
    }
    System.out.println(res / 2);
  }
}
