package com.codeforces.smalex;

import java.util.Scanner;

public class P602A {
  public static void main(String[] args) throws Exception {
    new P602A().run();
  }

  public void run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    long x = readNumber(scanner);
    long y = readNumber(scanner);
    if (x == y) {
      System.out.println("=");
    } else if (x < y) {
      System.out.println("<");
    } else {
      System.out.println(">");
    }
  }

  public long readNumber(Scanner scanner) {
    long res = 0L;
    int n = scanner.nextInt();
    int b = scanner.nextInt();
    for (int i = 0; i < n; i ++) {
      res *= b;
      res += scanner.nextInt();
    }
    return res;
  }
}

