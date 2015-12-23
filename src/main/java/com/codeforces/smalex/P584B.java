package com.codeforces.smalex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by smalex on 06/10/15.
 */
public class P584B {
  public static void main(String[] args) throws Exception {
    new P584B().run();
  }

  private void run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    // brute force
    // int res2 = 0;
    // int[] a = new int[n * 3];
    // while (true) {
    //   for (int j = 0; j < 3 * n; j += 3) {
    //     if (a[j] + a[j + 1] + a[j + 2] != 3) {
    //       res2++;
    //       break;
    //     }
    //   }
    //   int i = 0;
    //   for (i = a.length - 1; i >= 0 && a[i] == 2; i--);
    //   if (i < 0) {
    //     break;
    //   } 
    //   a[i]++;
    //   i++;
    //   for (; i < a.length; i++) {
    //     a[i] = 0;
    //   }
    // }
    // System.out.println(res2);
    long mod = 1000000007L;
    long res = 1;
    long res3 = 1;
    for (int i = 0; i < n; i++) {
      res *= 27L;
      res = res % mod;
      res3 *= 7L;
      res3 = res3 % mod;
    }

   System.out.println((res - res3 + mod) % mod);
  }

  private InputStream getInputStream() throws FileNotFoundException {
    if ("smalex".equals(System.getenv("USER"))) {
      Class clazz = getClass();
      URL resource = clazz.getResource(clazz.getSimpleName() + ".in");
      if (resource == null) {
        System.out.println("file not exists");
        return System.in;
      }
      return new FileInputStream(new File(resource.getFile()));
    } else {
      return System.in;
    }
  }

}
