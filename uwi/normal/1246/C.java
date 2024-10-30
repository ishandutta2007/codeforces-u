//package round596;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class C {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), m = ni();
		char[][] map = nm(n,m);
		int[][] right = new int[n][m];
		for(int i = 0;i < n;i++){
			int ro = 0;
			for(int j = m-1;j >= 0;j--){
				right[i][j] = ro;
				if(map[i][j] == 'R')ro++;
			}
		}
		int[][] down = new int[n+1][m];
		for(int i = 0;i < m;i++){
			int ro = 0;
			for(int j = n-1;j >= 0;j--){
				if(map[j][i] == 'R')ro++;
				down[j][i] = ro;
			}
		}
		
		int mod = 1000000007;
		long[][] dp = new long[m][n+1]; // col downrock
		dp[0][down[0][0]] = 1;
		long[] dpsum = new long[m];
		dpsum[0] = 1;
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				dpsum[j] += mod - dp[j][n-i];
				dp[j][n-i] = 0;
				if (dpsum[j] >= mod)
					dpsum[j] -= mod;
			}
			
			long[] imos = new long[m+1];
			for(int j = 0;j < m;j++){
				imos[j+1] += dpsum[j];
				if (imos[j+1] >= mod)
					imos[j+1] -= mod;
				imos[m-right[i][j]] += mod-dpsum[j];
				if (imos[m-right[i][j]] >= mod)
					imos[m-right[i][j]] -= mod;
			}
			for(int j = 0;j < m;j++){
				imos[j+1] += imos[j];
				if (imos[j+1] >= mod)
					imos[j+1] -= mod;
			}
			
			for(int j = 0;j < m;j++){
				dp[j][down[i+1][j]] += imos[j];
				if (dp[j][down[i+1][j]] >= mod)
					dp[j][down[i+1][j]] -= mod;
				dpsum[j] += imos[j];
				if (dpsum[j] >= mod)
					dpsum[j] -= mod;
			}
		}
		out.println(dpsum[m-1]);
	}
	
	void run() throws Exception
	{
//		int n = 10, m = 2000;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		for (int i = 0; i < n; i++) {
//			for(int j = 0;j < m;j++){
//				sb.append(".........R".charAt(gen.nextInt(10)));
//			}
//			sb.append("\n");
//		}
//		INPUT = sb.toString();

		
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new C().run(); }
	
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