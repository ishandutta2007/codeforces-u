//package round450;
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
		char[] s = ns(n);
		int K = ni();
		int[] plus = new int[n];
		int[] minus = new int[n];
		for(int i = 0;i < n;i++){
			if(s[i] == 'a'){
				plus[i] = i % 2 == 0 ? 1 : 0;
				minus[i] = i % 2 == 0 ? 0 : 1;
			}
			if(s[i] == 'b'){
				plus[i] = i % 2 == 0 ? 0 : 1;
				minus[i] = i % 2 == 0 ? 1 : 0;
			}
		}
		int[] cump = new int[n+1];
		for(int i = 0;i < n;i++)cump[i+1] = cump[i] + plus[i];
		int[] cumm = new int[n+1];
		for(int i = 0;i < n;i++)cumm[i+1] = cumm[i] + minus[i];
		
		int[] dp = new int[n+1];
		int[] ep = new int[n+1];
		for(int i = 1;i <= n;i++){
			if(i-K >= 0 && (cump[i] - cump[i-K] == 0 && (i+K) % 2 == 1 || cumm[i] - cumm[i-K] == 0 && (i+K) % 2 == 0)){
				int q = K - Math.max(cump[i] - cump[i-K], cumm[i] - cumm[i-K]);
				if(dp[i-1] == dp[i-K] || dp[i-K] == dp[i-1] - 1 && ep[i-K] + q < ep[i-1]){
					dp[i] = dp[i-K] + 1;
					ep[i] = ep[i-K] + q;
					continue;
				}
			}
			dp[i] = dp[i-1];
			ep[i] = ep[i-1];
		}
		out.println(ep[n]);
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