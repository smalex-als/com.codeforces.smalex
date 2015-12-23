package com.codeforces.smalex;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P606C {
  private static String testString = "6\n1 6 5 2 3 4\n";
  // private static String testString = "7\n" +
    //  "1 3 5 7 2 4 6\n";

  public static void main(String[] args) {
    BufferedInputStream in = new BufferedInputStream(
        new ByteArrayInputStream(testString.getBytes()));
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    int[] rev = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt() - 1;
      rev[a[i]] = i;
    }
    int last = -1;
    int len = 0;
    int res = 0;
    for (int i : rev) {
      if (i > last) {
        len++;
      } else {
        len = 1;
      }
      last = i;
      res = Math.max(res, len);
    }
    System.out.println(n - res);
  }

  public int getMax(int[] a, int n) {
    int max = 1;
    int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      d[i] = 1;
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i]) {
          d[i] = Math.max(d[i], 1 + d[j]);
          max = Math.max(d[i], max);
        }
      }
    }
    return max;
  }

  public static <E extends Comparable<? super E>> List<E> lis(List<E> n) {
    List<Node<E>> pileTops = new ArrayList<Node<E>>();
    // sort into piles
    for (E x : n) {
      Node<E> node = new Node<E>();
      node.value = x;
      int i = Collections.binarySearch(pileTops, node);
      if (i < 0) i = ~i;
      if (i != 0)
        node.pointer = pileTops.get(i - 1);
      if (i != pileTops.size())
        pileTops.set(i, node);
      else
        pileTops.add(node);
    }
    // extract LIS from nodes
    List<E> result = new ArrayList<E>();
    for (Node<E> node = pileTops.size() == 0 ? null : pileTops.get(pileTops.size() - 1);
         node != null; node = node.pointer)
      result.add(node.value);
    Collections.reverse(result);
    return result;
  }

  private static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>> {
    public E value;
    public Node<E> pointer;

    public int compareTo(Node<E> y) {
      return value.compareTo(y.value);
    }
  }
}
