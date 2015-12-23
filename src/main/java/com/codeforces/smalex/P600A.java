package com.codeforces.smalex;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class P600A {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    int start = 0;
    int type = 1;
    StringBuilder a = new StringBuilder();
    StringBuilder b = new StringBuilder();
    a.append('"');
    b.append('"');
    char[] arr  = str.toCharArray();
    int aCnt = 0;
    int bCnt = 0;
    Pattern pattern = Pattern.compile("0|[1-9][0-9]*");
    ArrayList<String> strings = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      int c = arr[i];
      if (c == ';' || c == ',') {
        strings.add(new String(arr, start, i - start));
        start = i + 1;
      }
    }
    if (arr[arr.length - 1] == ';' ||arr[arr.length - 1] == ',') {
      strings.add(new String(""));
    } else {
      strings.add(new String(arr, start, arr.length - start));
    }
    for (String string : strings) {
      if (pattern.matcher(string).matches()) {
        if (aCnt > 0) {
          a.append(',');
        }
        aCnt++;
        a.append(string);
      } else {
        if (bCnt > 0) {
          b.append(',');
        }
        bCnt++;
        b.append(string);
      }
    }
    a.append('"');
    b.append('"');

    System.out.println(aCnt == 0 ? "-" : a.toString());
    System.out.println(bCnt == 0 ? "-" : b.toString());
  }
}
