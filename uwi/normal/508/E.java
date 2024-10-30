//package round288;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	int I = 9999;
	
	void solve()
	{
		int n = ni();
		int[][] rs = new int[n][];
		for(int i = 0;i < n;i++){
			rs[i] = new int[]{ni(), ni()};
		}
		int[][] dp = new int[n][n];
		for(int i = 0;i < n;i++){
			Arrays.fill(dp[i], -1);
		}
		for(int len = 1;len <= n;len++){
			inner:
			for(int i = 0;i+len-1 < n;i++){
				int j = i+len-1;
				for(int k = i;k < j;k++){
					if(dp[i][k] >= 0 && dp[k+1][j] >= 0){
						dp[i][j] = k;
						continue inner;
					}
				}
				if(i+1 <= j && dp[i+1][j] >= 0 || i == j){
					int reach = 2*(j-i)+1;
					if(rs[i][0] <= reach && reach <= rs[i][1]){
						dp[i][j] = I;
					}
				}
			}
		}
		if(dp[0][n-1] == -1){
			out.println("IMPOSSIBLE");
			return;
		}
		
		char[] ret = new char[2*n];
		Arrays.fill(ret, '.');
		dfs(0, 2*n-1, 0, n-1, dp, ret);
		out.println(new String(ret));
	}
	
	void dfs(int l, int r, int cl, int cr, int[][] dp, char[] ret)
	{
		if(cl == -1 || cr == -1 || cl > cr)return;
		if(dp[cl][cr] == I){
			ret[l] = '('; ret[r] = ')';
			dfs(l+1, r-1, cl+1, cr, dp, ret);
		}else{
			dfs(l, (dp[cl][cr]-cl+1)*2+l-1, cl, dp[cl][cr], dp, ret);
			dfs((dp[cl][cr]-cl+1)*2+l, r, dp[cl][cr]+1, cr, dp, ret);
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
	
	public static void main(String[] args) throws Exception { new E().run(); }
	
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