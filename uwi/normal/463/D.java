//package round264;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), K = ni();
		int[][] g = new int[n][n];
		for(int i = 0;i < K;i++){
			int[] a = na(n);
			for(int j = 0;j < n;j++){
				for(int k = j+1;k < n;k++){
					g[a[j]-1][a[k]-1]++;
				}
			}
		}
		boolean[][] bg = new boolean[n][n];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				bg[i][j] = g[i][j] == K;
			}
		}
		int[] ord = sortTopologically(bg);
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int ret = 0;
		for(int i = 0;i < n;i++){
			int cur = ord[i];
			for(int j = 0;j < n;j++){
				if(bg[j][cur]){
					dp[cur] = Math.max(dp[cur], dp[j] + 1);
				}
			}
			ret = Math.max(ret, dp[cur]);
		}
		out.println(ret);
	}
	
	public static int[] sortTopologically(boolean[][] g)
	{
		int n = g.length;
		int[] ec = new int[n];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				if(g[i][j])ec[j]++;
			}
		}
		int[] ret = new int[n];
		int p = 0;
		int q = 0;
		
		ost:
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				if(g[j][i])continue ost;
			}
			ret[q++] = i;
		}
		
		for(;p < q;p++){
			int cur = ret[p];
			for(int i = 0;i < n;i++){
				if(g[cur][i]){
					ec[i]--;
					if(ec[i] == 0)ret[q++] = i;
				}
			}
		}
		for(int i = 0;i < n;i++){
			if(ec[i] > 0){
				return null;
			}
		}
		return ret;
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
	
	public static void main(String[] args) throws Exception { new D().run(); }
	
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