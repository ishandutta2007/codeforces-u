//package round219;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.InputMismatchException;

public class D3 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), K = ni();
		int[] from = new int[n-1];
		int[] to = new int[n-1];
		for(int i = 0;i < n-1;i++){
			from[i] = ni()-1;
			to[i] = ni()-1;
		}
		int[][] g = packU(n, from, to);
		int[][] pars = parents3(g, 0);
		int[] par = pars[0], dep = pars[2];
		int[][] rs = makeRights(g, par, 0);
		int[] ord = rs[0], iord = rs[1];
		int[][] spar = logstepParents(par);
		
//		tr(ord);
		int[] ft = new int[n+1];
		int num = 0;
		int p = 0;
		int maxlen = 0;
		for(int i = 0;i < n;i++){
			// +i
			int plus = i == 0 ? 1 : go(ft, ord, iord, spar, dep, i);
			num += plus;
			addFenwick(ft, iord[i], 1);
//			tr("i num plus", i, num, plus);
			
			while(num > K){
				addFenwick(ft, iord[p], -1);
				int minus = go(ft, ord, iord, spar, dep, p);
				num -= minus;
//				tr("i p num", i, p, num);
				p++;
			}
			
			maxlen = Math.max(maxlen, i-p+1);
		}
		out.println(maxlen);
	}
	
	int go(int[] ft, int[] ord, int[] iord, int[][] spar, int[] dep, int i)
	{
		int bef = before(ft, iord[i]);
		int aft = after(ft, iord[i]);
		if(bef != -1)bef = ord[bef];
		if(aft != -1)aft = ord[aft];
		int ld = 0;
//		tr("bef i aft", bef, i, aft);
		if(bef != -1 && aft != -1){
			{
				int lca = lca2(bef, aft, spar, dep);
				ld -= dep[bef] + dep[aft] - 2*dep[lca];
			}
			{
				int lca = lca2(bef, i, spar, dep);
				ld += dep[bef] + dep[i] - 2*dep[lca];
			}
			{
				int lca = lca2(aft, i, spar, dep);
				ld += dep[aft] + dep[i] - 2*dep[lca];
			}
			return ld/2;
		}else{
			int n = dep.length;
//			tr("Inof");
//			tr(sumFenwick(ft, n-1));
//			tr(restoreFenwick(ft));
//			tr(findGFenwick(ft, sumFenwick(ft, n-1)-1));
			int low = ord[first(ft)];
			int high = ord[findGFenwick(ft, sumFenwick(ft, n-1)-1)+1];
//			tr("low", "high", low, high);
			{
				int lca = lca2(low, high, spar, dep);
				ld -= dep[low] + dep[high] - 2*dep[lca];
			}
			{
				int lca = lca2(low, i, spar, dep);
				ld += dep[low] + dep[i] - 2*dep[lca];
			}
			{
				int lca = lca2(high, i, spar, dep);
				ld += dep[high] + dep[i] - 2*dep[lca];
			}
			return ld/2;
		}
	}
	
	public static int sumFenwick(int[] ft, int i)
	{
		int sum = 0;
		for(i++;i > 0;i -= i&-i)sum += ft[i];
		return sum;
	}
	
	public static void addFenwick(int[] ft, int i, int v)
	{
		if(v == 0)return;
		int n = ft.length;
		for(i++;i < n;i += i&-i)ft[i] += v;
	}
	
	public static int first(int[] ft)
	{
		int i = 0;
		int n = ft.length;
		int v = 0;
		for(int b = Integer.highestOneBit(n);b != 0 && i < n;b >>= 1){
			if(i + b < n){
				int t = i + b;
				if(v >= ft[t]){
					i = t;
					v -= ft[t];
				}
			}
		}
		return i;
	}
	
	
	public static int findGFenwick(int[] ft, int v)
	{
		int i = 0;
		int n = ft.length;
		for(int b = Integer.highestOneBit(n);b != 0 && i < n;b >>= 1){
			if(i + b < n){
				int t = i + b;
				if(v >= ft[t]){
					i = t;
					v -= ft[t];
				}
			}
		}
		return v != 0 ? -(i+1) : i-1;
	}
	
	public static int valFenwick(int[] ft, int i)
	{
		return sumFenwick(ft, i) - sumFenwick(ft, i-1);
	}
	
	public static int[] restoreFenwick(int[] ft)
	{
		int n = ft.length-1;
		int[] ret = new int[n];
		for(int i = 0;i < n;i++)ret[i] = sumFenwick(ft, i);
		for(int i = n-1;i >= 1;i--)ret[i] -= ret[i-1];
		return ret;
	}
	
	public static int before(int[] ft, int x)
	{
		int u = sumFenwick(ft, x-1);
		if(u == 0)return -1;
		return findGFenwick(ft, u-1)+1;
	}
	
	public static int after(int[] ft, int x)
	{
		int u = sumFenwick(ft, x);
		int f = findGFenwick(ft, u);
		if(f+1 >= ft.length-1)return -1;
		return f+1;
	}
	
	public static int lca2(int a, int b, int[][] spar, int[] depth)
	{
		if(depth[a] < depth[b]){
			b = ancestor(b, depth[b]-depth[a], spar);
		}else if(depth[a] > depth[b]){
			a = ancestor(a, depth[a]-depth[b], spar);
		}
		
		if(a == b)return a;
		int sa = a, sb = b;
		for(int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer.numberOfTrailingZeros(t);t > 0;t>>>=1,k--){
			if((low^high) >= t){
				if(spar[k][sa] != spar[k][sb]){
					low |= t;
					sa = spar[k][sa]; sb = spar[k][sb];
				}else{
					high = low|t-1;
				}
			}
		}
		return spar[0][sa];
	}
	
	protected static int ancestor(int a, int m, int[][] spar)
	{
		for(int i = 0;m > 0 && a != -1;m>>>=1,i++){
			if((m&1)==1)a = spar[i][a];
		}
		return a;
	}
	
	public static int[][] logstepParents(int[] par)
	{
		int n = par.length;
		int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n-1))+1;
		int[][] pars = new int[m][n];
		pars[0] = par;
		for(int j = 1;j < m;j++){
			for(int i = 0;i < n;i++){
				pars[j][i] = pars[j-1][i] == -1 ? -1 : pars[j-1][pars[j-1][i]];
			}
		}
		return pars;
	}
	
	public static int[][] parents3(int[][] g, int root)
	{
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);
		
		int[] depth = new int[n];
		depth[0] = 0;
		
		int[] q = new int[n];
		q[0] = root;
		for(int p = 0, r = 1;p < r;p++) {
			int cur = q[p];
			for(int nex : g[cur]){
				if(par[cur] != nex){
					q[r++] = nex;
					par[nex] = cur;
					depth[nex] = depth[cur] + 1;
				}
			}
		}
		return new int[][] {par, q, depth};
	}
	
	public static int[] sortByPreorder(int[][] g, int root){
		int n = g.length;
		int[] stack = new int[n];
		int[] ord = new int[n];
		BitSet ved = new BitSet();
		stack[0] = root;
		int p = 1;
		int r = 0;
		ved.set(root);
		while(p > 0){
			int cur = stack[p-1];
			ord[r++] = cur;
			p--;
			for(int e : g[cur]){
				if(!ved.get(e)){
					stack[p++] = e;
					ved.set(e);
				}
			}
		}
		return ord;
	}
	
	public static int[][] makeRights(int[][] g, int[] par, int root)
	{
		int n = g.length;
		int[] ord = sortByPreorder(g, root);
		int[] iord = new int[n];
		for(int i = 0;i < n;i++)iord[ord[i]] = i;
		
		int[] right = new int[n];
		for(int i = n-1;i >= 0;i--){
			int v = i;
			for(int e : g[ord[i]]){
				if(e != par[ord[i]]){
					v = Math.max(v, right[iord[e]]);
				}
			}
			right[i] = v;
		}
		return new int[][]{ord, iord, right};
	}
	
	static int[][] packU(int n, int[] from, int[] to) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for(int f : from)
			p[f]++;
		for(int t : to)
			p[t]++;
		for(int i = 0;i < n;i++)
			g[i] = new int[p[i]];
		for(int i = 0;i < from.length;i++){
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
	
	public static void main(String[] args) throws Exception { new D3().run(); }
	
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