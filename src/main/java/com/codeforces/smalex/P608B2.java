package com.codeforces.smalex;

import java.util.Scanner;

public class P608B2 {
  // private static String testD = "0011 0110";
  // private static String testD = "01 00111";
  // private static String testD = "1001101001101110101101000 01111000010011111111110010001101000100011110101111";
  // private static String testD = "101 10110";

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    String a = scanner.next();
    String b = scanner.next();
    int len = b.length();
    int[] prefix = new int[len];
    int[] prefixZero = new int[len];
    prefix[0] = b.charAt(0) == '1' ? 1 : 0;
    prefixZero[0] = b.charAt(0) == '0' ? 1 : 0;
    for (int i = 1; i < len; i++) {
      prefix[i] = prefix[i-1] + (b.charAt(i) == '1' ? 1 : 0);
      prefixZero[i] = prefixZero[i-1] + (b.charAt(i) == '0' ? 1 : 0);
    }
    int res = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == '1') {
        int cnt0 = i > 0 ? prefixZero[i - 1] : 0;
        int to = b.length() - a.length() + i;
        int cnt1 = prefixZero[to];
        int cnt = cnt1 - cnt0;
        res += cnt;
      } else {
        int cnt = prefix[b.length() - a.length() + i] - (i > 0 ? prefix[i - 1] : 0);
        res += cnt;
      }
    }
    System.out.println(res);
  }
}
