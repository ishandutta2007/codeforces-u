//package round160;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class A {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int m = ni();
		int[] q = na(m);
		int n = ni();
		int[] a = na(n);
		q = radixSort(q);
		a = radixSort(a);
		int[] cum = new int[n+1];
		for(int i = 0;i < n;i++){
			cum[i+1] = cum[i] + a[i];
		}
		int[] dp = new int[n+1];
		for(int i = n-1;i >= 0;i--){
			dp[i] = dp[i+1] + a[i];
			if(i+q[0]+1 <= n)dp[i] = Math.min(dp[i], dp[i+q[0]+1] + cum[i+q[0]+1] - cum[i+1]);
			if(i+q[0]+2 <= n)dp[i] = Math.min(dp[i], dp[i+q[0]+2] + cum[i+q[0]+2] - cum[i+2]);
		}
		out.println(dp[0]);
	}
	
	public static int[] radixSort(int[] f)
	{
		int[] to = new int[f.length];
		{
			int[] b = new int[65537];
			for(int i = 0;i < f.length;i++)b[1+(f[i]&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < f.length;i++)to[b[f[i]&0xffff]++] = f[i];
			int[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < f.length;i++)b[1+(f[i]>>>16)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < f.length;i++)to[b[f[i]>>>16]++] = f[i];
			int[] d = f; f = to;to = d;
		}
		return f;
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