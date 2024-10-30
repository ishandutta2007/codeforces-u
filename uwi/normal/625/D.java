//package round342;
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
		char[] sb = ns().toCharArray();
		
		if(go(Arrays.copyOf(sb, sb.length), 0, 0, sb.length-1))return;
		if(sb[0] == '1' && go(Arrays.copyOf(sb, sb.length), 1, 1, sb.length-1))return;
		out.println(0);
	}
	
	boolean go(char[] s, int cu, int l, int r){
		if(l > r)return false;
		char[] t = new char[r-l+1];
		for(int i = l, j = r;i <= j;i++,j--){
			if(i < j){
				int d = cu*10+s[j]-'0';
				if(d == 19){
					d = 9;
				}
				t[j-l] = (char)(d-Math.min(9, d)+'0');
				t[i-l] = (char)(Math.min(9, d)+'0');
				s[i] -= d%10;
				if(d == 9 && cu == 1)s[i] += 10;
				if(d >= 10){
					for(int k = j-1;k >= i;k--){
						s[k]--;
						if(s[k] < '0'){
							s[k] = '9';
							if(k == i)return false;
						}else{
							break;
						}
					}
				}
				if(s[i] == '0'){
					cu = 0;
				}else if(s[i] == '1'){
					cu = 1;
				}else{
					return false;
				}
			}else{
				int d = cu*10+s[j]-'0';
				if(d % 2 == 0){
					t[j-l] = (char)('0'+(d/2));
					cu = 0;
				}else{
					return false;
				}
			}
		}
		if(cu != 0)return false;
		if(t[0] == '0')return false;
//		out.println("OK");
//		tr(t);
		out.println(new String(t));
		return true;
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