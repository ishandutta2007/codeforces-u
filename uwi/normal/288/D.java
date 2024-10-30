//package round177;
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
//	String INPUT = "5 1 2 2 3 3 4 4 5";
//	String INPUT = "6 1 2 1 3 2 4 4 5 4 6";
//	String INPUT = "6 1 2 2 3 3 4 4 5 5 6";
//	String INPUT = "7 1 2 1 3 2 4 2 5 3 6 3 7";
//	String INPUT = "15 1 2 1 3 2 4 2 5 3 6 3 7 4 8 4 9 5 10 5 11 6 12 6 13 7 14 7 15";
	
	void solve()
	{
		int n = ni();
		int[] from = new int[n-1];
		int[] to = new int[n-1];
		for(int i = 0;i < n-1;i++){
			from[i] = ni()-1;
			to[i] = ni()-1;
		}
		int[][] g = packU(n, from, to);
		int[][] pars = parents3(g, 0);
		int[] par = pars[0], ord = pars[1];
		int[] des = new int[n];
		for(int i = n-1;i >= 0;i--){
			int cur = ord[i];
			des[cur]++;
			if(i != 0)des[par[cur]] += des[cur];
		}
		long[] shield = new long[n];
		for(int i = 0;i < n;i++){
			int cur = ord[i];
			shield[cur] = (long)(n-des[cur])*(n-des[cur]-1)/2;
		}
		for(int i = n-1;i >= 1;i--){
			int cur = ord[i];
			shield[cur] -= shield[par[cur]];
		}
		
		long[] attack = new long[n];
		for(int i = 0;i < n;i++){
			int cur = ord[i];
			attack[cur] = (long)des[cur]*(des[cur]-1)/2;
		}
		
		for(int i = 1;i <= n-1;i++){
			int cur = ord[i];
			attack[par[cur]] -= attack[cur];
		}
		
		long[] dup = new long[n];
		for(int i = 0;i < n;i++){
			int cur = ord[i];
			long sum = 0;
			for(int e : g[cur]){
				if(par[cur] != e){
					sum += (long)des[e]*(des[e]-1)/2;
				}
			}
			for(int e : g[cur]){
				if(par[cur] != e){
					dup[e] = dup[cur] + sum - (long)des[e]*(des[e]-1)/2;
				}
			}
		}
		
		long ret = 0;
		for(int i = 0;i < n;i++){
			int cur = ord[i];
			ret += attack[cur] * ((long)(n-des[cur])*(n-des[cur]-1)/2-dup[cur]) + (long)des[cur]*(des[cur]-1)/2 * shield[cur];
		}
		out.println(ret);
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
//		int n = 80000, m = 99999;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		for(int i = 0;i < n-1;i++){
//			sb.append(i+1 + " " + (i+2) + " ");
//		}
//		INPUT = sb.toString();
		
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