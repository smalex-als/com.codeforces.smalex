package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P816B {
  // problem http://codeforces.com/contest/816/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT = "3 2 4\n" +
                  "91 94\n" +
                  "92 97\n" +
                  "97 99\n" +
                  "92 94\n" +
                  "93 97\n" +
                  "95 96\n" +
                  "90 100\n" ;
  
  void solve() {
    int n = ni();
    int k = ni();
    int q = ni();
    SumIntervalTree tree = new SumIntervalTree(200002);
    for (int i = 0; i < n; i++) {
      tree.update(ni(), ni(), 1);
    }
    for (int i = 0; i < 200002; i++) {
      long v = tree.query(i, i);
      if (v > 0) {
        if (v < k) {
          tree.update(i, i, -v);
        } else {
          tree.update(i, i, -v + 1);
        }
      }
    }
    for (int i = 0; i < q; i++) {
      out.println(tree.query(ni(), ni()));
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P816B().run();
  }
  
  void run() throws Exception {
    is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
    out = new PrintWriter(System.out);
  
    long s = System.currentTimeMillis();
    solve();
    out.flush();
    tr(System.currentTimeMillis() - s + "ms");
  }
  
  private byte[] inbuf = new byte[1024];
  private int lenbuf = 0, ptrbuf = 0;
  
  private int readByte() {
    if (lenbuf == -1)
      throw new InputMismatchException();
    if (ptrbuf >= lenbuf) {
      ptrbuf = 0;
      try {
        lenbuf = is.read(inbuf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (lenbuf <= 0)
        return -1;
    }
    return inbuf[ptrbuf++];
  }
  
  private boolean isSpaceChar(int c) {
    return !(c >= 33 && c <= 126);
  }
  
  private int skip() {
    int b;
    while ((b = readByte()) != -1 && isSpaceChar(b))
      ;
    return b;
  }
  
  private double nd() {
    return Double.parseDouble(ns());
  }
  
  private char nc() {
    return (char) skip();
  }
  
  private String ns() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }
  
  private char[] ns(int n) {
    char[] buf = new char[n];
    int b = skip(), p = 0;
    while (p < n && !(isSpaceChar(b))) {
      buf[p++] = (char) b;
      b = readByte();
    }
    return n == p ? buf : Arrays.copyOf(buf, p);
  }
  
  private char[][] nm(int n, int m) {
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++)
      map[i] = ns(m);
    return map;
  }
  
  private int[] na(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = ni();
    return a;
  }
  
  private int ni() {
    int num = 0, b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }

  private long nl() {
    long num = 0;
    int b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }

  private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

  private void tr(Object... o) {
    if (!oj)
      System.out.println(Arrays.deepToString(o));
  }
  public abstract class LongIntervalTree extends IntervalTree {
    protected long[] value;
    protected long[] delta;

    protected LongIntervalTree(int size) {
      this(size, true);
    }

    public LongIntervalTree(int size, boolean shouldInit) {
      super(size, shouldInit);
    }

    @Override
    protected void initData(int size, int nodeCount) {
      value = new long[nodeCount];
      delta = new long[nodeCount];
    }

    protected abstract long joinValue(long left, long right);

    protected abstract long joinDelta(long was, long delta);

    protected abstract long accumulate(long value, long delta, int length);

    protected abstract long neutralValue();

    protected abstract long neutralDelta();

    protected long initValue(int index) {
      return neutralValue();
    }

    @Override
    protected void initAfter(int root, int left, int right, int middle) {
      value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
      delta[root] = neutralDelta();
    }

    @Override
    protected void initBefore(int root, int left, int right, int middle) {
    }

    @Override
    protected void initLeaf(int root, int index) {
      //    System.out.println("root = " + root + " index = " + index);
      value[root] = initValue(index);
      delta[root] = neutralDelta();
      //    System.out.println("value[root] = " + value[root] + " delta[root] = " + delta[root]);
      //    System.out.println("value = " + Arrays.toString(value));
      //    System.out.println("delta = " + Arrays.toString(delta));
    }

    @Override
    protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
      value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
    }

    @Override
    protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
      pushDown(root, left, middle, right);
    }

    protected void pushDown(int root, int left, int middle, int right) {
      value[2 * root + 1] = accumulate(value[2 * root + 1], delta[root], middle - left + 1);
      value[2 * root + 2] = accumulate(value[2 * root + 2], delta[root], right - middle);
      delta[2 * root + 1] = joinDelta(delta[2 * root + 1], delta[root]);
      delta[2 * root + 2] = joinDelta(delta[2 * root + 2], delta[root]);
      delta[root] = neutralDelta();
    }

    @Override
    protected void updateFull(int root, int left, int right, int from, int to, long delta) {
      value[root] = accumulate(value[root], delta, right - left + 1);
      this.delta[root] = joinDelta(this.delta[root], delta);
    }

    @Override
    protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
      return joinValue(leftResult, rightResult);
    }

    @Override
    protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {
      pushDown(root, left, middle, right);
    }

    @Override
    protected long queryFull(int root, int left, int right, int from, int to) {
      return value[root];
    }

    @Override
    protected long emptySegmentResult() {
      return neutralValue();
    }
  }
  public abstract class IntervalTree {
    protected int size;

    protected IntervalTree(int size) {
      this(size, true);
    }

    public IntervalTree(int size, boolean shouldInit) {
      this.size = size;
      int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
      initData(size, nodeCount);
      if (shouldInit)
        init();
    }

    protected abstract void initData(int size, int nodeCount);
    protected abstract void initAfter(int root, int left, int right, int middle);
    protected abstract void initBefore(int root, int left, int right, int middle);
    protected abstract void initLeaf(int root, int index);
    protected abstract void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle);
    protected abstract void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle);
    protected abstract void updateFull(int root, int left, int right, int from, int to, long delta);
    protected abstract long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult);
    protected abstract void queryPreProcess(int root, int left, int right, int from, int to, int middle);
    protected abstract long queryFull(int root, int left, int right, int from, int to);
    protected abstract long emptySegmentResult();

    public void init() {
      if (size == 0)
        return;
      init(0, 0, size - 1);
    }

    private void init(int root, int left, int right) {
      if (left == right) {
        initLeaf(root, left);
      } else {
        int middle = (left + right) >> 1;
        initBefore(root, left, right, middle);
        init(2 * root + 1, left, middle);
        init(2 * root + 2, middle + 1, right);
        initAfter(root, left, right, middle);
      }
    }

    public void update(int from, int to, long delta) {
      update(0, 0, size - 1, from, to, delta);
    }

    protected void update(int root, int left, int right, int from, int to, long delta) {
      if (left > to || right < from)
        return;
      if (left >= from && right <= to) {
        updateFull(root, left, right, from, to, delta);
        return;
      }
      int middle = (left + right) >> 1;
      updatePreProcess(root, left, right, from, to, delta, middle);
      update(2 * root + 1, left, middle, from, to, delta);
      update(2 * root + 2, middle + 1, right, from, to, delta);
      updatePostProcess(root, left, right, from, to, delta, middle);
    }

    public long query(int from, int to) {
      return query(0, 0, size - 1, from, to);
    }

    protected long query(int root, int left, int right, int from, int to) {
      if (left > to || right < from)
        return emptySegmentResult();
      if (left >= from && right <= to)
        return queryFull(root, left, right, from, to);
      int middle = (left + right) >> 1;
      queryPreProcess(root, left, right, from, to, middle);
      long leftResult = query(2 * root + 1, left, middle, from, to);
      long rightResult = query(2 * root + 2, middle + 1, right, from, to);
      return queryPostProcess(root, left, right, from, to, middle, leftResult, rightResult);
    }
  }

  public class SumIntervalTree extends LongIntervalTree {
    private final long[] array;

    public SumIntervalTree(int size) {
      super(size);
      array = null;
    }

    public SumIntervalTree(long[] array) {
      super(array.length, false);
      this.array = array;
      init();
    }

    @Override
    protected long initValue(int index) {
      if (array == null) {
        return 0;
      }
      return array[index];
    }

    @Override
    protected long joinValue(long left, long right) {
      return left + right;
    }

    @Override
    protected long joinDelta(long was, long delta) {
      return was + delta;
    }

    @Override
    protected long accumulate(long value, long delta, int length) {
      return value + delta * length;
    }

    @Override
    protected long neutralValue() {
      return 0;
    }

    @Override
    protected long neutralDelta() {
      return 0;
    }
  }

}
