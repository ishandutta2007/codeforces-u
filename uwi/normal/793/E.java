//package tinkoff2017.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E2 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int[] a = na(4);
		for(int i = 0;i < 4;i++){
			a[i]--;
		}
		int[] par = new int[n];
		par[0] = -1;
		for(int i = 1;i <n;i++){
			par[i] = ni()-1;
		}
		int[][] g = parentToG(par);
		
		int[][] pars = parents3(g, 0);
		int[] ord = pars[1];
		boolean[][] dp = new boolean[n][];
		int[] dom = new int[n];
		Arrays.fill(dom, -1);
		int[] nl = new int[n];
		outer:
		for(int i = n-1;i >= 1;i--){
			int cur = ord[i];
			if(g[cur].length == 1){
				nl[cur]++;
			}
			nl[par[cur]] += nl[cur];
			for(int j = 0;j < 4;j++){
				if(a[j] == cur){
					dom[cur] = j;
					dp[cur] = new boolean[]{true};
					continue outer;
				}
			}
			int dome = -1;
			for(int e : g[cur]){
				if(par[cur] == e)continue;
				if(dom[e] != -1){
					dom[cur] = dom[e];
					dome = e;
					break;
				}
			}
			if(dom[cur] == -1)continue;
			dp[cur] = Arrays.copyOf(dp[dome], nl[cur]);
			for(int e : g[cur]){
				if(par[cur] == e)continue;
				if(dome == e)continue;
				for(int j = dp[cur].length-1-nl[e];j >= 0;j--){
					dp[cur][j+nl[e]] |= dp[cur][j];
				}
			}
		}
//		tr(dom);
		if(nl[0] % 2 != 0){
			out.println("NO");
			return;
		}
		
		boolean[] ep = new boolean[n+1];
		ep[0] = true;
		boolean[][] dps = new boolean[4][];
		int[] fa = new int[4];
		for(int e : g[0]){
			if(dom[e] == -1){
				for(int j = n;j >= nl[e];j--){
					ep[j] |= ep[j-nl[e]];
				}
			}else{
				dps[dom[e]] = dp[e];
				fa[dom[e]] = dp[e].length;
//				tr(dom[e]);
//				tf(dp[e]);
			}
		}
//		tf(ep);
		// a c b d
		{
			boolean[] canab = new boolean[n+1];
			for(int i = 0;i < dps[0].length;i++){
				for(int j = 0;j < dps[1].length;j++){
					if(dps[0][i] && dps[1][j]){
						int len = nl[0]/2-1-(dps[0].length-i-1 + j) - fa[2];
						if(len >= 0)canab[len] = true;
					}
				}
			}
			boolean[] cancd = new boolean[n+1];
			for(int i = 0;i < dps[2].length;i++){
				for(int j = 0;j < dps[3].length;j++){
					if(dps[2][i] && dps[3][j]){
						int len = nl[0]/2-1-(dps[2].length-i-1 + j) - fa[1];
						if(len >= 0)cancd[len] = true;
					}
				}
			}
			for(int i = 0;i <= n;i++){
				for(int j = 0;j <= n;j++){
					if(ep[i] && ep[j] && canab[i] && cancd[j]){
						out.println("YES");
						return;
					}
				}
			}
		}
		{
			boolean[] canab = new boolean[n+1];
			for(int i = 0;i < dps[0].length;i++){
				for(int j = 0;j < dps[1].length;j++){
					if(dps[0][i] && dps[1][j]){
						int len = nl[0]/2-1-(dps[0].length-i-1 + j) - fa[3];
						if(len >= 0)canab[len] = true;
					}
				}
			}
			boolean[] cancd = new boolean[n+1];
			for(int i = 0;i < dps[3].length;i++){
				for(int j = 0;j < dps[2].length;j++){
					if(dps[3][i] && dps[2][j]){
						int len = nl[0]/2-1-(dps[3].length-i-1 + j) - fa[1];
						if(len >= 0)cancd[len] = true;
					}
				}
			}
			for(int i = 0;i <= n;i++){
				for(int j = 0;j <= n;j++){
					if(ep[i] && ep[j] && canab[i] && cancd[j]){
						out.println("YES");
						return;
					}
				}
			}
		}
		out.println("NO");
	}
	
	public static void tf(boolean... r)
	{
		for(boolean x : r)System.out.print(x?'#':'.');
		System.out.println();
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

	
	public static int[][] parentToG(int[] par)
	{
		int n = par.length;
		int[] ct = new int[n];
		for(int i = 0;i < n;i++){
			if(par[i] >= 0){
				ct[i]++;
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
				g[i][--ct[i]] = par[i];
			}
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
	
	public static void main(String[] args) throws Exception { new E2().run(); }
	
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