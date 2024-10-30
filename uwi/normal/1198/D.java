//package round576;
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
		int n = ni();
		char[][] map = nm(n,n);
		int[][] cum = new int[n+1][n+1];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				cum[i+1][j+1] = cum[i+1][j] + cum[i][j+1] - cum[i][j] + 
						(map[i][j] == '#' ? 1 : 0);
			}
		}
		
		int[][][][] dp = new int[n][n][n][n];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				dp[i][j][i][j] = map[i][j] == '#' ? 1 : 0;
			}
		}
		for(int h = 1;h <= n;h++){
			for(int w = 1;w <= n;w++){
				if(h+w == 2)continue;
				for(int i = 0;i+h-1 < n;i++){
					for(int j = 0;j+w-1 < n;j++){
						if(cum[i+h][j+w] - cum[i+h][j] - cum[i][j+w] + cum[i][j] == 0){
							dp[i][j][i+h-1][j+w-1] = 0;
						}else{
							int v = Math.max(h, w);
//							dp[i][j][i+h-1][j+w-1] = Math.max(h, w);
							for(int k = i+1;k <= i+h-1;k++){
								v = Math.min(
										v,
										dp[i][j][k-1][j+w-1] + dp[k][j][i+h-1][j+w-1]
										);
							}
							for(int k = j+1;k <= j+w-1;k++){
								v = Math.min(
										v,
										dp[i][j][i+h-1][k-1] + dp[i][k][i+h-1][j+w-1]
										);
							}
							dp[i][j][i+h-1][j+w-1] = v;
						}
					}
				}
			}
		}
		out.println(dp[0][0][n-1][n-1]);
	}
	
	void run() throws Exception
	{
//		StringBuilder sb = new StringBuilder();
//		Random gen = new Random(1000000009L);
//		int n = 50;
//		sb.append(n + " ");
//		for(int i = 0;i < n;i++){
//			for(int j = 0;j < n;j++){
//				sb.append(".....#".charAt(gen.nextInt(6)));
////				sb.append("######".charAt(gen.nextInt(6)));
//			}
//			sb.append("\n");
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