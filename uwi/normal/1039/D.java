//package round507;
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
	
	int[][] g;
	int[] par, ord, dep;
	
	int[] dp, ch;
	
	void solve()
	{
		int n = ni();
		int[] from = new int[n - 1];
		int[] to = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			from[i] = ni() - 1;
			to[i] = ni() - 1;
		}
		g = packU(n, from, to);
		int[][] pars = parents3(g, 0);
		par = pars[0]; ord = pars[1]; dep = pars[2];
		
		dp = new int[n];
		ch = new int[n];
		
		int[] anss = new int[n+1];
		Arrays.fill(anss, -1);
		anss[1] = solve(1);
		anss[n] = solve(n);
		dfs(1, n+1, anss);
		for(int i = 1;i <= n;i++){
			out.println(anss[i]);
		}
	}
	
	void dfs(int l, int r, int[] anss)
	{
//		tr(l, r, anss[l], anss[r-1]);
		if(anss[l] == anss[r-1]){
			for(int i = l;i < r;i++){
				anss[i] = anss[l];
			}
			return;
		}
		if(r-l <= 2)return;
		
		int h = (l+r-1)/2;
		if(anss[h] == -1)anss[h] = solve(h);
		
		dfs(l, h+1, anss);
		dfs(h, r, anss);
	}
	
	int solve(int K)
	{
		int n = g.length;
		if(K == 1)return n;
//		int[] dp = new int[n];
//		int[] ch = new int[n];
		for(int i = n-1;i >= 0;i--){
			int cur = ord[i];
			int maxch1 = -9999999;
			int maxch2 = -9999999;
			int s = 0;
			for(int e : g[cur]){
				if(par[cur] == e)continue;
				s += dp[e];
				if(ch[e] > maxch1){
					maxch2 = maxch1;
					maxch1 = ch[e];
				}else if(ch[e] > maxch2){
					maxch2 = ch[e];
				}
			}
			if(maxch1 < 0){
				// leaf
				dp[cur] = 0;
				ch[cur] = 2;
				continue;
			}
			
			if(maxch1 - 1 + 1 >= K){
				dp[cur] = s + 1;
				ch[cur] = 1;
				continue;
			}
			if(maxch1 - 1 + maxch2 - 1 + 1 >= K){
				dp[cur] = s + 1;
				ch[cur] = 1;
				continue;
			}
			dp[cur] = s;
			ch[cur] = maxch1 + 1;
		}
		return dp[0];
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