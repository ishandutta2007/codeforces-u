//package round474;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class F {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int[] from = new int[n - 1];
		int[] to = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			from[i] = ni() - 1;
			to[i] = ni() - 1;
		}
		int[][] g = packU(n, from, to);
		int[][] pars = parents3(g, 0);
		int[] par = pars[0], ord = pars[1], dep = pars[2];
		int[][] chs = parentToChildren(par);
		
		int B = (int)Math.cbrt(n)+1;
		long ans = 0;
		for(int K = 2;K <= B && K <= n;K++){
			int[] dp = new int[n];
			Arrays.fill(dp, 1);
			long o = 1;
			int lim = 0;
			for(;o <= 320000;){
				o = o * K;
				lim++;
			}
			int[] temp = new int[lim];
			for(int i = n-1;i >= 0;i--){
				int cur = ord[i];
				if(chs[cur].length < K){
					dp[cur] = 1;
					continue;
				}
				
				for(int e : chs[cur]){
					temp[dp[e]]++;
				}
				dp[cur] = 1;
				int s = 0;
				for(int d = lim-1;d >= 0;d--){
					s += temp[d];
					if(s >= K){
						dp[cur] = d+1;
						break;
					}
				}
				for(int e : chs[cur]){
					temp[dp[e]]--;
				}
			}
			for(int i = n-1;i >= 0;i--){
				int cur = ord[i];
				if(i > 0)dp[par[cur]] = Math.max(dp[par[cur]], dp[cur]);
				ans += dp[cur];
			}
		}
		
		int[] h = new int[n];
		for(int i = n-1;i >= 0;i--){
			int cur = ord[i];
			h[cur] = 1;
			for(int e : g[cur]){
				if(par[cur] == e)continue;
				h[cur] = Math.max(h[cur], h[e] + 1);
			}
			ans += h[cur];
		}
		
		if(n >= B+1){
			ans += (long)(n-(B+1)+1)*n;
			int[][] hs = new int[2*n][];
			int p = 0;
			for(int i = 0;i < n;i++){
				int cur = ord[i];
				int ch = chs[cur].length;
				if(ch >= B+1){
					hs[p++] = new int[]{ch, cur};
					int[] lens = new int[ch];
					int q = 0;
					for(int e : chs[cur]){
						lens[q++] = chs[e].length;
					}
					Arrays.sort(lens);
					int ro = 0;
					for(int j = 0;j < ch;j++){
						ro = Math.max(ro, Math.min(lens[j], ch-j));
					}
					if(ro >= B+1){
						hs[p++] = new int[]{Math.min(ch, ro), cur, 1};
					}
				}
			}
			hs = Arrays.copyOf(hs, p);
			Arrays.sort(hs, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					if(a[0] != b[0])return a[0] - b[0];
					return a.length - b.length;
				}
			});
			
			int prev = n;
			int[] marked = new int[n];
			int nm = 0;
			for(int i = p-1;i >= 0;i--){
				ans += (long)(prev-hs[i][0])*nm;
				int x = hs[i][1];
				if(hs[i].length == 2){
					for(int y = x;y != -1 && marked[y] == 0;y = par[y]){
						marked[y] = 1;
						nm++;
					}
				}else{
					for(int y = x;y != -1 && marked[y] <= 1;y = par[y]){
						nm += 2-marked[y];
						marked[y] = 2;
					}
				}
				prev = hs[i][0];
			}
			ans += (long)(prev-B)*nm;
		}
		
		out.println(ans);
	}
	
	public static int[][] parentToChildren(int[] par)
	{
		int n = par.length;
		int[] ct = new int[n];
		for(int i = 0;i < n;i++){
			if(par[i] >= 0){
				ct[par[i]]++;
			}
		}
		int[][] g = new int[n][];
		for(int i = 0;i < n;i++){
			g[i] = new int[ct[i]];
		}
		for(int i = 0;i < n;i++){
			if(par[i] >= 0){
				g[par[i]][--ct[par[i]]] = i;
			}
		}
		
		return g;
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