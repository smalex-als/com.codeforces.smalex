package com.codeforces.smalex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class P602C {
  public static void main(String[] args) throws Exception {
    new P602C().run();
  }

  public void run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int p = scanner.nextInt();
    int[][] rail = new int[n][n];
    int[][] car = new int[n][n];
    for (int i = 0; i < p; i++) {
      int x = scanner.nextInt() - 1;
      int y = scanner.nextInt() - 1;
      rail[x][y] = 1;
      rail[y][x] = 1;
    }
    for (int i = 0; i < rail.length; i++) {
      int[] row = rail[i];
      for (int j = 0; j < row.length; j++) {
        if (row[j] == 0) {
          car[j][i] = 1;
          car[i][j] = 1;
        }
      }
    }
    int[] pathCar = new int[n];
    int[] pathRail = new int[n];
    int minCost = getMinCost(n, rail, pathRail, pathCar);
    int minCost1 = getMinCost(n, car, pathCar, pathRail);
    int[] pathCar2 = new int[n];
    int[] pathRail2 = new int[n];
    int minCost21 = getMinCost(n, car, pathCar2, pathRail2);
    int minCost2 = getMinCost(n, rail, pathRail2, pathCar2);
    if ((minCost == Integer.MAX_VALUE || minCost1 == Integer.MAX_VALUE)
        && (minCost2 == Integer.MAX_VALUE || minCost21 == Integer.MAX_VALUE)) {
      System.out.println(-1);
    } else {
      System.out.println(Math.min(Math.max(minCost, minCost1), Math.max(minCost21, minCost2)));
    }
  }

  private int getMinCost(int n, int[][] rail, int[] cost, int[] anotherCost) {
    LinkedList<Integer> q = new LinkedList<>();
    q.add(0);
    Arrays.fill(cost, Integer.MAX_VALUE);
    cost[0] = 0;
    while (!q.isEmpty()) {
      Integer city = q.poll();
      int[] join = rail[city];
      for (int i = 0; i < join.length; i++) {
        if (join[i] == 1 && (cost[i] == Integer.MAX_VALUE || cost[city] + 1 < cost[i])
            && anotherCost[i] != cost[city] + 1) {
          cost[i] = cost[city] + 1;
          q.add(i);
        }
      }
    }
    return cost[n - 1];
  }
}
