//package round301;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class B {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), K = ni(), p = ni(), x = ni(), y = ni();
		int[] a = na(K);
		long sum = 0;
		for(int v : a)sum += v;
		for(int mid = y;mid <= p;mid++){
			int bigger = 0;
			int same = 0;
			int smaller = 0;
			for(int i = 0;i < K;i++){
				if(a[i] > mid){
					bigger++;
				}else if(a[i] == mid){
					same++;
				}else{
					smaller++;
				}
			}
			if(bigger > n/2)continue;
			if(smaller > n/2)continue;
			
//			tr(mid, bigger, same, smaller);
			if(same == 0){
				long s = sum;
				for(int i = 0;i < n/2-bigger+1;i++){
					s += mid;
				}
				for(int i = 0;i < n/2-smaller;i++){
					s += 1;
				}
				if(s <= x){
					for(int i = 0;i < n/2-bigger+1;i++){
						out.print(mid + " ");
					}
					for(int i = 0;i < n/2-smaller;i++){
						out.print(1 + " ");
					}
					out.println();
					return;
				}
			}else{
				int ex = Math.max(0, bigger + same - (n/2+1));
				long s = sum;
				for(int i = 0;i < n/2-ex-smaller;i++){
					s += 1;
				}
				for(int i = 0;i < n-(bigger+same+smaller+n/2-ex-smaller);i++){
					s += mid;
				}
				if(s <= x){
					for(int i = 0;i < n/2-ex-smaller;i++){
						out.print(1 + " ");
					}
					for(int i = 0;i < n-(bigger+same+smaller+n/2-ex-smaller);i++){
						out.print(mid + " ");
					}
					out.println();
					return;
				}
			}
		}
		out.println(-1);
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
	
	public static void main(String[] args) throws Exception { new B().run(); }
	
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