//package round553;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class F {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), K = ni();
		int[] a = na(n);
		int o = 0;
		for(int i = 0;i < n;i++)o += a[i];
		int s = 0;
		for(int i = 0;i < o;i++)s += a[n-1-i];
		
		// o-i/n-o   i/o
		
		int[][] M = new int[o+1][o+1];
		long all = n*(n-1);
		
		long iall = invl(all, mod);
		for(int i = 0;i <= o;i++){
			for(int j = -1;j <= 1;j++){
				int t = i+j;
				if(t >= 0 && t <= o){
					if(j == 1){
						M[t][i] = (int)(2*(o-i)*(o-i)*iall%mod);
					}else if(j == -1){
						M[t][i] = (int)(2*((n-o)-(o-i))*i*iall%mod);
					}else{
						M[t][i] = (int)((all-2*(o-i)*(o-i)-2*((n-o)-(o-i))*i)*iall%mod);
					}
				}
			}
		}
		int[] v = new int[o+1];
		v[s] = 1;
		out.println(pow(M, v, K)[o]);
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
	}
	
	///////// begin
	public static final int mod = 1000000007;
	public static final long m2 = (long)mod*mod;
	public static final long BIG = 8L*m2;
	
	// A^e*v
	public static int[] pow(int[][] A, int[] v, long e)
	{
		for(int i = 0;i < v.length;i++){
			if(v[i] >= mod)v[i] %= mod;
		}
		int[][] MUL = A;
		for(;e > 0;e>>>=1) {
			if((e&1)==1)v = mul(MUL, v);
			MUL = p2(MUL);
		}
		return v;
	}
	
	// int matrix*int vector
	public static int[] mul(int[][] A, int[] v)
	{
		int m = A.length;
		int n = v.length;
		int[] w = new int[m];
		for(int i = 0;i < m;i++){
			long sum = 0;
			for(int k = 0;k < n;k++){
				sum += (long)A[i][k] * v[k];
				if(sum >= BIG)sum -= BIG;
			}
			w[i] = (int)(sum % mod);
		}
		return w;
	}
	
	// int matrix^2 (be careful about negative value)
	public static int[][] p2(int[][] A)
	{
		int n = A.length;
		int[][] C = new int[n][n];
		for(int i = 0;i < n;i++){
			long[] sum = new long[n];
			for(int k = 0;k < n;k++){
				for(int j = 0;j < n;j++){
					sum[j] += (long)A[i][k] * A[k][j];
					if(sum[j] >= BIG)sum[j] -= BIG;
				}
			}
			for(int j = 0;j < n;j++){
				C[i][j] = (int)(sum[j] % mod);
			}
		}
		return C;
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
	
	public static void main(String[] args) throws Exception { new F().run(); }
	
	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;
	
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