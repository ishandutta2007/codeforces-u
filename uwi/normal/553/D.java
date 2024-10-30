//package round309;
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
	
	MinHeapL oks;
	
	void solve()
	{
		int n = ni(), m = ni(), K = ni();
		boolean[] fb = new boolean[n];
		for(int i = 0;i < K;i++)fb[ni()-1] = true;
		int[] from = new int[m];
		int[] to = new int[m];
		for (int i = 0; i < m; i++) {
			from[i] = ni() - 1;
			to[i] = ni() - 1;
		}
		
		int[][] g = packU(n, from, to);
		if(ok(1, 1, g, fb)){
			out.println(oks.size());
			while(oks.size() > 0){
				out.print(oks.argmin() + 1 + " ");
				oks.remove(oks.argmin());
			}
			out.println();
			return;
		}
		long bp = 1, bq = 0;
		long p = 0, q = 1;
		out:
		while(q <= 10000000){
			{
				for(long a = 1;;a *= 2){
					if(a > 10000000)break out;
					if(ok(a*p+bp, a*q+bq, g, fb)){
						long low = a/2, high = a;
						while(high - low > 1){
							long ha = (high+low)/2;
							if(ok(ha*p+bp, ha*q+bq, g, fb)){
								high = ha;
							}else{
								low = ha;
							}
						}
						long np = low*p+bp, nq = low*q+bq;
						bp = p; bq = q;
						p = np; q = nq;
						break;
					}
				}
			}
			{
				for(long a = 1;;a *= 2){
					if(a > 10000000)break out;
					if(!ok(a*p+bp, a*q+bq, g, fb)){
						long low = a/2, high = a;
						while(high - low > 1){
							long ha = (high+low)/2;
							if(ok(ha*p+bp, ha*q+bq, g, fb)){
								low = ha;
							}else{
								high = ha;
							}
						}
						long np = low*p+bp, nq = low*q+bq;
						bp = p; bq = q;
						p = np; q = nq;
						break;
					}
				}
			}
		}
//		out.println(p + " " + q);
		if(ok(p, q, g, fb)){
			out.println(oks.size());
			while(oks.size() > 0){
				out.print(oks.argmin() + 1 + " ");
				oks.remove(oks.argmin());
			}
			out.println();
		}else{
			throw new RuntimeException();
		}
	}
	
	boolean ok(long p, long q, int[][] g, boolean[] fb)
	{
		// num*q-den*p >= 0
		int n = g.length;
		MinHeapL h = new MinHeapL(n);
		for(int i = 0;i < n;i++){
			if(!fb[i]){
				long val = -g[i].length*p;
				for(int e : g[i]){
					if(!fb[e]){
						val += q;
					}
				}
				h.add(i, val);
			}
		}
		while(h.size() > 0){
			if(h.min() >= 0){
				oks = h;
				return true;
			}else{
				int cur = h.argmin();
				h.remove(cur);
				for(int e : g[cur]){
					if(h.imap[e] != -1){
						h.update(e, h.a[h.imap[e]]-q);
					}
				}
			}
		}
		return false;
	}
	
	public static class MinHeapL {
		public long[] a;
		public int[] map;
		public int[] imap;
		public int n;
		public int pos;
		public static long INF = Long.MAX_VALUE;
		
		public MinHeapL(int m)
		{
			n = Integer.highestOneBit((m+1)<<1);
			a = new long[n];
			map = new int[n];
			imap = new int[n];
			Arrays.fill(a, INF);
			Arrays.fill(map, -1);
			Arrays.fill(imap, -1);
			pos = 1;
		}
		
		public long add(int ind, long x)
		{
			int ret = imap[ind];
			if(imap[ind] < 0){
				a[pos] = x; map[pos] = ind; imap[ind] = pos;
				pos++;
				up(pos-1);
			}
			return ret != -1 ? a[ret] : x;
		}
		
		public long update(int ind, long x)
		{
			int ret = imap[ind];
			if(imap[ind] < 0){
				a[pos] = x; map[pos] = ind; imap[ind] = pos;
				pos++;
				up(pos-1);
			}else{
				a[ret] = x;
				up(ret);
				down(ret);
			}
			return x;
		}
		
		public long remove(int ind)
		{
			if(pos == 1)return INF;
			if(imap[ind] == -1)return INF;
			
			pos--;
			int rem = imap[ind];
			long ret = a[rem];
			map[rem] = map[pos];
			imap[map[pos]] = rem;
			imap[ind] = -1;
			a[rem] = a[pos];
			a[pos] = INF;
			map[pos] = -1;
			
			up(rem);
			down(rem);
			return ret;
		}
		
		public long min() { return a[1]; }
		public int argmin() { return map[1]; }
		public int size() {	return pos-1; }
		
		private void up(int cur)
		{
			for(int c = cur, p = c>>>1;p >= 1 && a[p] > a[c];c>>>=1, p>>>=1){
				long d = a[p]; a[p] = a[c]; a[c] = d;
				int e = imap[map[p]]; imap[map[p]] = imap[map[c]]; imap[map[c]] = e;
				e = map[p]; map[p] = map[c]; map[c] = e;
			}
		}
		
		private void down(int cur)
		{
			for(int c = cur;2*c < pos;){
				int b = a[2*c] < a[2*c+1] ? 2*c : 2*c+1;
				if(a[b] < a[c]){
					long d = a[c]; a[c] = a[b]; a[b] = d;
					int e = imap[map[c]]; imap[map[c]] = imap[map[b]]; imap[map[b]] = e;
					e = map[c]; map[c] = map[b]; map[b] = e;
					c = b;
				}else{
					break;
				}
			}
		}
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