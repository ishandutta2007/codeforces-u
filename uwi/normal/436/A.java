//package zepto2014;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class A {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int x = ni();
		int[][] a = new int[n][];
		for(int i = 0;i < n;i++){
			a[i] = new int[]{ni(), ni(), ni()};
		}
		
		int max = 0;
		{
			PriorityQueue<int[]> q0 = new PriorityQueue<int[]>(10000, new Comparator<int[]>(){
				public int compare(int[] a, int[] b){
					return -(a[2] - b[2]);
				}
			});
			PriorityQueue<int[]> q1 = new PriorityQueue<int[]>(10000, new Comparator<int[]>(){
				public int compare(int[] a, int[] b){
					return -(a[2] - b[2]);
				}
			});
			boolean[] ved = new boolean[n];
			for(int i = 0;i < n;i++){
				if(a[i][1] <= x){
					ved[i] = true;
					if(a[i][0] == 0){
						q0.add(a[i]);
					}else{
						q1.add(a[i]);
					}
				}
			}
			int ct = 0;
			long h = x;
			while(true){
				{
					if(q0.isEmpty())break;
					int[] f = q0.poll();
					h += f[2];
					ct++;
					for(int i = 0;i < n;i++){
						if(a[i][1] <= h && !ved[i]){
							ved[i] = true;
							if(a[i][0] == 0){
								q0.add(a[i]);
							}else{
								q1.add(a[i]);
							}
						}
					}
				}
				{
					if(q1.isEmpty())break;
					int[] f = q1.poll();
					h += f[2];
					ct++;
					for(int i = 0;i < n;i++){
						if(a[i][1] <= h && !ved[i]){
							ved[i] = true;
							if(a[i][0] == 0){
								q0.add(a[i]);
							}else{
								q1.add(a[i]);
							}
						}
					}
				}
			}
			max = Math.max(max, ct);
		}
		{
			PriorityQueue<int[]> q0 = new PriorityQueue<int[]>(10000, new Comparator<int[]>(){
				public int compare(int[] a, int[] b){
					return -(a[2] - b[2]);
				}
			});
			PriorityQueue<int[]> q1 = new PriorityQueue<int[]>(10000, new Comparator<int[]>(){
				public int compare(int[] a, int[] b){
					return -(a[2] - b[2]);
				}
			});
			boolean[] ved = new boolean[n];
			for(int i = 0;i < n;i++){
				if(a[i][1] <= x){
					ved[i] = true;
					if(a[i][0] == 0){
						q0.add(a[i]);
					}else{
						q1.add(a[i]);
					}
				}
			}
			int ct = 0;
			long h = x;
			while(true){
				{
					if(q1.isEmpty())break;
					int[] f = q1.poll();
					h += f[2];
					ct++;
					for(int i = 0;i < n;i++){
						if(a[i][1] <= h && !ved[i]){
							ved[i] = true;
							if(a[i][0] == 0){
								q0.add(a[i]);
							}else{
								q1.add(a[i]);
							}
						}
					}
				}
				{
					if(q0.isEmpty())break;
					int[] f = q0.poll();
					h += f[2];
					ct++;
					for(int i = 0;i < n;i++){
						if(a[i][1] <= h && !ved[i]){
							ved[i] = true;
							if(a[i][0] == 0){
								q0.add(a[i]);
							}else{
								q1.add(a[i]);
							}
						}
					}
				}
			}
			max = Math.max(max, ct);
		}
		out.println(max);
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
	
	public static void main(String[] args) throws Exception { new A().run(); }
	
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