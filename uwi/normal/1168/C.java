//package round562;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class C {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int Q = ni();
		int[] a = na(n);
		int[][] qs = new int[Q][];
		for(int i = 0;i < Q;i++){
			qs[i] = new int[]{ni()-1, ni()-1, i};
		}
		boolean[] anss = new boolean[Q];
		
		Arrays.sort(qs, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1] != b[1])return a[1] - b[1];
				return -(a[0] - b[0]);
			}
		});
		
		int[][] g = new int[19][n];
		for(int i = 0;i < 19;i++){
			Arrays.fill(g[i], -1);
		}
		int[] lasts = new int[19];
		Arrays.fill(lasts, -1);
		
		int p = 0;
		for(int i = 0;i < n;i++){
			for(int j = 0;j < 19;j++){
				if(a[i]<<~j<0 && lasts[j] != -1){
					for(int k = 0;k < 19;k++){
						g[k][i] = Math.max(g[k][i], g[k][lasts[j]]);
					}
					g[j][i] = Math.max(g[j][i], lasts[j]);
				}
			}
			for(int j = 0;j < 19;j++){
				if(a[i]<<~j<0){
					lasts[j] = i;
				}
			}
			
			while(p < Q && qs[p][1] == i){
				for(int j = 0;j < 19;j++){
					if(a[qs[p][0]]<<~j<0 && g[j][i] >= qs[p][0]){
						anss[qs[p][2]] = true;
						break;
					}
				}
				p++;
			}
		}
		for(boolean ans : anss){
			out.println(ans ? "Shi" : "Fou");
		}
	}
	
	void run() throws Exception
	{
//		int n = 300000, m = 299999;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		for (int i = 0; i < n; i++) {
//			sb.append(gen.nextInt(300000) + " ");
//		}
//		for (int i = 0; i < m; i++) {
//			int v1 = gen.nextInt(n);
//			int v2 = gen.nextInt(n);
//			sb.append(Math.min(v1, v2) + 1 + " ");
//			sb.append(Math.max(v1, v2) + 1 + " ");
//		}
//		INPUT = sb.toString();

		
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new C().run(); }
	
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