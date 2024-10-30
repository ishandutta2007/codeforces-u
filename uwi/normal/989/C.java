//package round487;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class C {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int A = ni(), B = ni(), C = ni(), D = ni();
		
		char[][] amap = go(A, B);
		char[][] cmap = go(C, D);
		out.println(48 + " " + 50);
		for(char[] row : amap){
			out.println(new String(row));
		}
		for(char[] row : cmap){
			out.println(new String(row).replace('B', 'D').replace('A', 'C'));
		}
	}
	
	char[][] go(int A, int B)
	{
		char[][] ret = new char[24][50];
		for(int i = 0;i < 24;i++){
			Arrays.fill(ret[i], 'A');
			ret[i][49] = 'B';
		}
		for(int i = 1;i < 24;i+=2){
			for(int j = 1;j < 50;j++){
				ret[i][j] = 'B';
			}
		}
		
		A--; B--;
		for(int i = 0;i < 24;i+=2){
			if(A > 0){
				int s = Math.min(A, 23);
				for(int j = 0;j < s;j++){
					ret[i][47-j*2] = 'B';
				}
				A -= s;
			}else if(B > 0){
				int s = Math.min(B, 23);
				for(int j = 0;j < s;j++){
					ret[i+1][j*2+2] = 'A';
				}
				B -= s;
			}
		}
		assert A == 0;
		assert B == 0;
		
		return ret;
	}
	
	void print(char[][] u)
	
	{
		for(char[] row : u)tr(new String(row));
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