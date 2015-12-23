package com.codeforces.smalex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P609D {
  static BufferedReader in;
  static StringTokenizer tok;
  static StringBuilder out = new StringBuilder();

  private static int[] pound;
  private static int[] dollar;
  private static int[] types;
  private static int[] amounts;
  private static int m;
  private static int s;
  private static int k;
  private static long[] x;



  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new InputStreamReader(System.in));
    int n = nextInt();
    m = nextInt();
    k = nextInt();
    s = nextInt();
    x = new long[m];
    dollar = new int[n];
    for (int i = 0; i < n; i++) {
      dollar[i] = nextInt();
    }
    pound = new int[n];
    for (int i = 0; i < n; i++) {
      pound[i] = nextInt();
    }

    types = new int[m];
    amounts = new int[m];
    for (int i = 0; i < m; i++) {
      int t = nextInt();
      int c = nextInt();
      types[i] = t;
      amounts[i] = c;
    }
    int lo = 1;
    int hi = n;
    int best = Integer.MAX_VALUE;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      long cost = check(mid, false);
      if (cost <= s) {
        hi = mid - 1;
        best = Math.min(mid, best);
      } else {
        lo = mid + 1;
      }
    }
    if (best == Integer.MAX_VALUE) {
      out.append(-1).append("\n");
    } else {
      out.append(best).append("\n");
      check(best, true);
    }
    System.out.print(out.toString());
  }

  public static long check(int days, boolean output) {
    long mind = Integer.MAX_VALUE;
    long minp = Integer.MAX_VALUE;
    int minDollarIndex = 0;
    int minPoundIndex = 0;
    for (int i = 0; i < days; i++) {
      if (dollar[i] < mind) {
        mind = dollar[i];
        minDollarIndex = i;
      }
      if (pound[i] < minp) {
        minp = pound[i];
        minPoundIndex = i;
      }
    }

    for (int i = 0; i < m; i++) {
      long cost = amounts[i];
      if (types[i] == 1) {
        cost *= mind;
      } else {
        cost *= minp;
      }
      x[i] = (cost * (m + 100L) + (long) i);
    }
    Arrays.sort(x);
    long res = 0;
    for (int i = 0; i < k; i++) {
      res += x[i] / (m + 100L);
      
      if (output) {
        int index = (int) (x[i] % (m + 100L));
        out.append((index + 1)).append(" ");
        if (types[index] == 1) {
          out.append(minDollarIndex + 1);
        } else {
          out.append(minPoundIndex + 1);
        }
        out.append("\n");
      }
    }
    return res;
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(next());
  }

  static int[] nextIntArray(int len, int start) throws IOException {
    int[] a = new int[len];
    for (int i = start; i < len; i++)
      a[i] = nextInt();
    return a;
  }

  static long nextLong() throws IOException {
    return Long.parseLong(next());
  }

  static long[] nextLongArray(int len, int start) throws IOException {
    long[] a = new long[len];
    for (int i = start; i < len; i++)
      a[i] = nextLong();
    return a;
  }

  static String next() throws IOException {
    while (tok == null || !tok.hasMoreTokens()) {
      tok = new StringTokenizer(in.readLine());
    }
    return tok.nextToken();
  }

}
