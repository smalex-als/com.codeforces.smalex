package com.codeforces.smalex;

import java.util.Scanner;

public class P612C {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    char[] stack = new char[str.length()];
    int pos = 0;
    int res = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (isOpen(c)) {
        stack[pos] = c;
        pos++;
      } else {
        if (pos == 0) {
          System.out.println("Impossible");
          return;
        }
        pos--;
        char prev = stack[pos];
        switch (c) {
          case ']':
            if (prev != '[') res++;
            break;
          case ')':
            if (prev != '(') res++;
            break;
          case '>':
            if (prev != '<') res++;
            break;
          case '}':
            if (prev != '{') res++;
            break;
        }
      }
    }
    if (pos > 0) {
      System.out.println("Impossible");
      return;
    }
    System.out.println(res);
  }

  private static boolean isOpen(char c) {
    return c == '[' || c == '{' || c == '<' || c == '(';
  }
}
