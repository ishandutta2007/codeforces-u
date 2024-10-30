//package round667;
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
		char[] s = ns(n);
		char[] t = ns(2);
		if(t[0] == t[1]){
			int has = 0;
			for(char c : s){
				if(c == t[0]){
					has++;
				}
			}
			int to = Math.min(has + K, n);
			out.println(to*(to-1)/2);
		}else{
			long[][] dp = new long[K+1][n+1]; // #replace #t[0]
			for(int i = 0;i <= K;i++){
				Arrays.fill(dp[i], Long.MIN_VALUE / 2);
			}
			dp[0][0] = 0;
			for(int i = 0;i < n;i++){
				long[][] ndp = new long[K+1][n+1];
				for(int j = 0;j <= K;j++){
					Arrays.fill(ndp[j], Long.MIN_VALUE / 2);
				}
				for(int j = 0;j <= K;j++){
					for(int k = 0;k <= n;k++){
						if(s[i] == t[1]){
							ndp[j][k] = Math.max(ndp[j][k], dp[j][k] + k);
						}else if(j+1 <= K){
							ndp[j+1][k] = Math.max(ndp[j+1][k], dp[j][k] + k);
						}
						if(s[i] == t[0] && k+1 <= n){
							ndp[j][k+1] = Math.max(ndp[j][k+1], dp[j][k]);
						}else if(j+1 <= K && k+1 <= n){
							ndp[j+1][k+1] = Math.max(ndp[j+1][k+1], dp[j][k]);
						}
						ndp[j][k] = Math.max(ndp[j][k], dp[j][k]);
					}
				}
				dp = ndp;
			}
			long ans = 0;
			for(int i = 0;i <= K;i++){
				for(int j = 0;j <= n;j++){
					ans = Math.max(ans, dp[i][j]);
				}
			}
			out.println(ans);
		}
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