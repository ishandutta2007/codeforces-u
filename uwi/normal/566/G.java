//package vk2015.f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class G {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), m = ni();
		int x = ni(), y = ni();
		long[][] plus = new long[n][];
		for(int i = 0;i < n;i++){
			plus[i] = new long[]{ni(), ni()};
		}
		long[][] minus = new long[m+3][];
		long maxx = 0;
		long maxy = 0;
		for(int i = 0;i < m;i++){
			minus[i] = new long[]{ni(), ni()};
			maxx = Math.max(maxx, minus[i][0]);
			maxy = Math.max(maxy, minus[i][1]);
		}
		minus[m] = new long[]{maxx, 0};
		minus[m+1] = new long[]{0, maxy};
		minus[m+2] = new long[]{0, 0};
		long[][] hull = convexHull(minus);
		
		long[] xs = new long[hull.length-1];
		long[] ys = new long[hull.length-1];
		
		Arrays.sort(hull, new Comparator<long[]>() {
			public int compare(long[] a, long[] b) {
				if(a[0] != b[0])return Long.compare(a[0], b[0]);
				return -Long.compare(a[1], b[1]);
			}
		});
		int p = 0;
		for(int i = 0;i < hull.length;i++){
			if(hull[i][0] == 0 && hull[i][1] == 0)continue;
			xs[p] = hull[i][0];
			ys[p] = hull[i][1];
			p++;
		}
		
		for(int i = 0;i < n;i++){
			if(plus[i][0] >= maxx){
				out.println("Max");
				return;
			}
			if(plus[i][1] >= maxy){
				out.println("Max");
				return;
			}
			int lb = lowerBound(xs, plus[i][0]);
			if(lb > 0){
				if(ccw(xs[lb-1], ys[lb-1], xs[lb], ys[lb], plus[i][0], plus[i][1]) <= 0){
					out.println("Max");
					return;
				}
			}
		}
		out.println("Min");
	}
	
	public static int lowerBound(long[] a, long v)
	{
		int low = -1, high = a.length;
		while(high-low > 1){
			int h = high+low>>>1;
			if(a[h] >= v){
				high = h;
			}else{
				low = h;
			}
		}
		return high;
	}

	
	public static long[][] convexHull(long[][] co)
	{
		int n = co.length;
		if(n <= 1)return co;
		Arrays.sort(co, new Comparator<long[]>(){
			public int compare(long[] a, long[] b){
				if(a[0] != b[0])return Long.compare(a[0], b[0]);
				return Long.compare(a[1], b[1]);
			}
		});
		
		int[] inds = new int[n + 1];
		int p = 0;
		for(int i = 0;i < n;i++){
			if(p >= 1 && co[inds[p-1]][0] == co[i][0] && co[inds[p-1]][1] == co[i][1])continue;
			while(p >= 2 && ccw(co[inds[p-2]], co[inds[p-1]], co[i]) >= 0)p--; // if you need point on line
			inds[p++] = i;
		}
		
		int inf = p + 1;
		for(int i = n - 2;i >= 0;i--){
			if(co[inds[p-1]][0] == co[i][0] && co[inds[p-1]][1] == co[i][1])continue;
			while(p >= inf && ccw(co[inds[p-2]], co[inds[p-1]], co[i]) >= 0)p--; // if you need point on line
			inds[p++] = i;
		}
		
		long[][] ret = new long[p-1][];
		for(int i = 0;i < p-1;i++)ret[i] = co[inds[i]];
		return ret;
	}
	

	public static int ccw(long ax, long ay, long bx, long by, long tx, long ty)
	{
		return Long.signum((tx-ax)*(by-ay)-(bx-ax)*(ty-ay));
	}
	
	public static int ccw(long[] a, long[] b, long[] t)
	{
		return Long.signum((t[0]-a[0])*(b[1]-a[1])-(b[0]-a[0])*(t[1]-a[1]));
	}
	
	public static int ccw(int[] a, int[] b, int[] t)
	{
		return Long.signum((long)(t[0]-a[0])*(b[1]-a[1])-(long)(b[0]-a[0])*(t[1]-a[1]));
	}

	
	void run() throws Exception
	{
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new G().run(); }
	
	private byte[] inbuf = new byte[1024];
	private int lenbuf = 0, ptrbuf = 0;
	
	private int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private double nd() { return Double.parseDouble(ns()); }
	private char nc() { return (char)skip(); }
	
	private String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
	private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}