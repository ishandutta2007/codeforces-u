//package round138;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class C {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), K = ni();
		int mod = 1000000007;
		int[] v = new int[n];
		for(int i = 0;i < n;i++)v[n-1-i] = ni();
		
		int[] a = new int[n];
		Arrays.fill(a, 1);
		
		int[] ret = pow(a, v, K, mod);
		for(int i = 0;i < n;i++){
			out.println(ret[n-1-i]);
		}
	}
	
	// int*
	public static int[] mul(int[] A, int[] v, int mod)
	{
		int m = A.length;
		int n = v.length;
		int[] w = new int[m];
		for(int i = 0;i < m;i++){
			long sum = 0;
			for(int k = i;k < n;k++){
				sum += (long)A[k-i] * v[k];
				sum %= mod;
			}
			w[i] = (int)sum;
		}
		return w;
	}
	
	// A^e*v
	public static int[] pow(int[] A, int[] v, long e, int mod)
	{
		int[] MUL = A;
		for(int i = 0;i < v.length;i++)v[i] %= mod;
		for(;e > 0;e>>>=1) {
			if((e&1)==1)v = mul(MUL, v, mod);
			MUL = p2tu(MUL, mod);
		}
		return v;
	}
	
	static int[] p2tu(int[] A, int mod)
	{
		int n = A.length;
		int[] C = new int[n];
		for(int i = 0;i < n;i++){
			long sum = 0;
			for(int j = 0;j <= i;j++){
				sum += (long)A[j] * A[i-j];
				sum %= mod;
			}
			C[i] = (int)sum;
		}
		return C;
	}
	
	public static int[][] p2tu(int[][] A, int mod)
	{
		int n = A.length;
		int[][] C = new int[n][n];
		for(int i = 0;i < n;i++){
			long sum = 0;
			for(int j = 0;j <= i;j++){
				sum += (long)A[0][j] * A[0][i-j];
				sum %= mod;
			}
			for(int j = i;j < n;j++){
				C[j-i][j] = (int)sum;
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
	
	public static void main(String[] args) throws Exception
	{
		new C().run();
	}
	
	public int ni()
	{
		try {
			int num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	public long nl()
	{
		try {
			long num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	public String ns()
	{
		try{
			int b = 0;
			StringBuilder sb = new StringBuilder();
			while((b = is.read()) != -1 && (b == '\r' || b == '\n' || b == ' '));
			if(b == -1)return "";
			sb.append((char)b);
			while(true){
				b = is.read();
				if(b == -1)return sb.toString();
				if(b == '\r' || b == '\n' || b == ' ')return sb.toString();
				sb.append((char)b);
			}
		} catch (IOException e) {
		}
		return "";
	}
	
	public char[] ns(int n)
	{
		char[] buf = new char[n];
		try{
			int b = 0, p = 0;
			while((b = is.read()) != -1 && (b == ' ' || b == '\r' || b == '\n'));
			if(b == -1)return null;
			buf[p++] = (char)b;
			while(p < n){
				b = is.read();
				if(b == -1 || b == ' ' || b == '\r' || b == '\n')break;
				buf[p++] = (char)b;
			}
			return Arrays.copyOf(buf, p);
		} catch (IOException e) {
		}
		return null;
	}
	
	
	double nd() { return Double.parseDouble(ns()); }
	boolean oj = System.getProperty("ONLINE_JUDGE") != null;
	void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}