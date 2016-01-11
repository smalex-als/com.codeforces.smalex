package com.codeforces.smalex;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class P611A {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    int lookNum = scanner.nextInt();
    scanner.next();
    String strType = scanner.next();
    Calendar calendar = new GregorianCalendar(2016, 0, 1, 0, 0, 0);
    Calendar calendarTo = new GregorianCalendar(2017, 0, 1, 0, 0, 0);
    int res = 0;
    if (strType.equalsIgnoreCase("week")) {
      while (calendar.before(calendarTo)) {
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) {
          day = 7;
        }
        if (day == lookNum) {
          res++;
        }
        calendar.add(Calendar.DATE, 1);
      } 
    } else {
      while (calendar.before(calendarTo)) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day == lookNum) {
          res++;
        }
        calendar.add(Calendar.DATE, 1);
      } 
    }
    System.out.println(res);
  }
}
