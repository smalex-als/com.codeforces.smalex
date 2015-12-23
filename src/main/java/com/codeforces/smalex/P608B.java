package com.codeforces.smalex;

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class P608B {
  private static class LongNum {
    private int[] val;
    private int length;

    private LongNum(String str) {
      int len = ((str.length() + 31) / 32);
      val = new int[len];
      length = str.length();
      int offset = 0;
      for (int i = 0; i < len; i++) {
        int v = 0;
        for (int j = 0 ; j < 32; j++, offset++) {
          v <<= 1;
          if (offset < str.length() && str.charAt(offset) == '1') {
            v++;
          }
        }
        val[i] = v;
      }
    }

    public void shiftLeftAndAdd(boolean one) {
      for (int i = 0; i < val.length; i++) {
        val[i] <<= 1;
        if (i < val.length -1 && (val[i + 1] >> 31) == 1) {
          val[i]++;
        }
      }
      if (one) {
        int offset = 32 - (length % 32);
        val[val.length - 1] += (1 << offset);
      }
    }

    @Override 
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0 ; i < val.length; i++) {
        sb.append(String.format("%32s",
              Integer.toBinaryString(val[i])).replace(' ', '0'));
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedInputStream in = new BufferedInputStream(
        new ByteArrayInputStream("101 10110".getBytes()));
    Scanner scanner = new Scanner(in);
    String a = scanner.next();
    String b = scanner.next();
    LongNum numA = new LongNum(a);
    LongNum numB = new LongNum(b.substring(0, a.length()));
    int res = 0;
    for (int j = 0; j < numA.val.length; j++) {
      res += Integer.bitCount(numA.val[j] ^ numB.val[j]);
    }
    int offset = a.length();
    while (offset < b.length()) { 
      numB.shiftLeftAndAdd(b.charAt(offset) == '1');
      for (int j = 0; j < numA.val.length; j++) {
        res += Integer.bitCount(numA.val[j] ^ numB.val[j]);
      }
      offset++;
    }
    System.out.println(res);
  }
}
