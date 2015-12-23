package com.codeforces.smalex;

import java.util.Scanner;

public class P600C {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.next();
    int[] count = new int[26];
    for (int i = 0; i < s.length(); i++) {
      count[(int) s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < 26; i++) {
      if (count[i] % 2 == 1) {
        for (int j = 25; j >= 0; j--) {
          if (count[j] % 2 == 1) {
            count[i]++;
            count[j]--;
            break;
          }
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      while (count[i] >= 2) {
        count[i] -= 2;
        sb.append((char) (i + 'a'));
      }
    }
    System.out.print(sb.toString());
    for (int i = 0; i < 26; i++) {
      if (count[i] == 1) {
        sb.append((char) (i + 'a'));
      }
    }
    sb.reverse();
    System.out.println(sb.toString());
  }
}
