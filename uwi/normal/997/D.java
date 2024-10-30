//package round493;
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
	
	final int mod = 998244353;
	
	void solve()
	{
		int n = ni(), m = ni(), K = ni();
		if(K % 2 == 1){
			out.println(0);
			return;
		}
		K /= 2;
		long[] r1 = calc(n, K);
		long[] r2 = calc(m, K);
		
		long[][] C = new long[200 + 1][200 + 1];
		for (int i = 0; i <= 200; i++) {
			C[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
				if (C[i][j] >= mod)
					C[i][j] -= mod;
			}
		}
		
		long ans = 0;
		for(int i = 0;i <= K;i++){
			ans += r1[i] * r2[K-i] % mod * C[2*K][2*i];
			ans %= mod;
		}
		out.println(ans);
	}
	
	long[] calc(int n, int K)
	{
		int[] from = new int[n - 1];
		int[] to = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			from[i] = ni() - 1;
			to[i] = ni() - 1;
		}
		int[][] g = packU(n, from, to);
		int[][] pars = parents3(g, 0);
		int[] par = pars[0], ord = pars[1], dep = pars[2];
		
		long[][] coss = new long[n][];
		long[][] dp = new long[n][K+1];
		for(int i = n-1;i >= 0;i--){
			int cur = ord[i];
			long[] udp = new long[K+1];
			udp[0] = 1;
			
			long[] cos = new long[K+1];
			coss[cur] = cos;
			for(int e : g[cur]){
				if(par[cur] == e)continue;
				for(int k = 0;k+1 <= K;k++){
					cos[k+1] += dp[e][k];
					if(cos[k+1] >= mod)cos[k+1] -= mod;
				}
			}
			
			for(int j = 1;j <= K;j++){
				for(int k = 1;k <= j;k++){
					udp[j] += udp[j-k] * cos[k];
					udp[j] %= mod;
				}
			}
			dp[cur] = udp;
		}
		
		long[] ret = Arrays.copyOf(dp[0], K+1);
		for(int i = 1;i < n;i++){
			int cur = ord[i];
			
			int p = par[cur];
			
			long[] pcos = coss[p];
			for(int k = 0;k+1 <= K;k++){
				pcos[k+1] -= dp[cur][k];
				if(pcos[k+1] < 0)pcos[k+1] += mod;
			}
			
			// from par
			long[] pudp = new long[K+1];
			pudp[0] = 1;
			
			for(int j = 1;j <= K;j++){
				for(int k = 1;k <= j;k++){
					pudp[j] += pudp[j-k] * pcos[k];
					pudp[j] %= mod;
				}
			}
			
			for(int k = 0;k+1 <= K;k++){
				pcos[k+1] += dp[cur][k];
				if(pcos[k+1] >= mod)pcos[k+1] -= mod;
			}
			
			
			long[] cos = coss[cur];
			for(int k = 0;k+1 <= K;k++){
				cos[k+1] += pudp[k];
				if(cos[k+1] >= mod)cos[k+1] -= mod;
			}
			
			long[] udp = new long[K+1];
			udp[0] = 1;
			
			for(int j = 1;j <= K;j++){
				for(int k = 1;k <= j;k++){
					udp[j] += udp[j-k] * cos[k];
					udp[j] %= mod;
				}
			}
			for(int j = 0;j <= K;j++){
				ret[j] += udp[j];
			}
		}
		
		for(int j = 0;j <= K;j++)ret[j] %= mod;
		return ret;
	}

	public static int[][] parents3(int[][] g, int root) {
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);

		int[] depth = new int[n];
		depth[0] = 0;

		int[] q = new int[n];
		q[0] = root;
		for (int p = 0, r = 1; p < r; p++) {
			int cur = q[p];
			for (int nex : g[cur]) {
				if (par[cur] != nex) {
					q[r++] = nex;
					par[nex] = cur;
					depth[nex] = depth[cur] + 1;
				}
			}
		}
		return new int[][] { par, q, depth };
	}

	static int[][] packU(int n, int[] from, int[] to) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for (int f : from)
			p[f]++;
		for (int t : to)
			p[t]++;
		for (int i = 0; i < n; i++)
			g[i] = new int[p[i]];
		for (int i = 0; i < from.length; i++) {
			g[from[i]][--p[from[i]]] = to[i];
			g[to[i]][--p[to[i]]] = from[i];
		}
		return g;
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