package com.codeforces.smalex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P600B {
  public static void main(String[] args) {
    new P600B().run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    Arrays.sort(a);
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    for (int i = 0; i < m; i++) {
      int search = scanner.nextInt();
      if (cache.containsKey(search)) {
        System.out.print(cache.get(search) + " ");
        continue;
      }
      int lo = 0; int hi = n - 1;
      int res = 0;
      while (lo <= hi) {
        int mid = (lo + hi) / 2;
        int test = a[mid];
        if (search > test) {
          lo = mid + 1;
        } else if (search < test) {
          hi = mid - 1;
          res = mid;
        } else {
          res = mid;
          break;
        }
      }
      while (res < n && a[res] <= search) {
        res++;
      }

      cache.put(search ,res);
      System.out.print(res + " ");
    }
  }
}
