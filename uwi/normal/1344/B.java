//package round639;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class B {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
//	String INPUT = "4 1 # # # #";
	
	void solve()
	{
		int n = ni(), m = ni();
		char[][] map = nm(n,m);
		
		boolean rs = false;
		boolean cs = false;
		outer:
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				if(map[i][j] != '.')continue outer;
			}
			rs = true;
		}
		outer:
		for(int i = 0;i < m;i++){
			for(int j = 0;j < n;j++){
				if(map[j][i] != '.')continue outer;
			}
			cs = true;
		}
		
		for(int i = 0;i < n;i++){
			int stand = 0;
			for(int j = 0;j < m;j++){
				if(map[i][j] == '#' && (j == 0 || map[i][j-1] == '.')){
					stand++;
				}
			}
			if(stand >= 2){
				out.println(-1);
				return;
			}
		}
		for(int i = 0;i < m;i++){
			int stand = 0;
			for(int j = 0;j < n;j++){
				if(map[j][i] == '#' && (j == 0 || map[j-1][i] == '.')){
					stand++;
				}
			}
			if(stand >= 2){
				out.println(-1);
				return;
			}
		}
		
		DJSet ds = new DJSet(n*m);
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				if(i+1 < n && map[i][j] == '#' && map[i+1][j] == '#'){
					ds.union(i*m+j, (i+1)*m+j);
				}
				if(j+1 < m && map[i][j] == '#' && map[i][j+1] == '#'){
					ds.union(i*m+j, i*m+j+1);
				}
			}
		}
		int ct = 0;
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				if(ds.root(i*m+j) == i*m+j && map[i][j] == '#'){
					ct++;
				}
			}
		}
		if(ct == 0){
			out.println(ct);
		}else if((rs^cs)){
			out.println(-1);
		}else{
			out.println(ct);
		}
	}
	
	public static class DJSet {
		public int[] upper;

		public DJSet(int n) {
			upper = new int[n];
			Arrays.fill(upper, -1);
		}

		public int root(int x) {
			return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
		}

		public boolean equiv(int x, int y) {
			return root(x) == root(y);
		}

		public boolean union(int x, int y) {
			x = root(x);
			y = root(y);
			if (x != y) {
				if (upper[y] < upper[x]) {
					int d = x;
					x = y;
					y = d;
				}
				upper[x] += upper[y];
				upper[y] = x;
			}
			return x == y;
		}

		public int count() {
			int ct = 0;
			for (int u : upper)
				if (u < 0)
					ct++;
			return ct;
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
	
	public static void main(String[] args) throws Exception { new B().run(); }
	
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