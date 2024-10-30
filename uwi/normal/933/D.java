//package round462;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D3 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		long m = nl();
		int mod = 1000000007;
		long mm = m%mod;
		long ans = 0;
		for(long x = 1;x*x <= m;x++){
			long n = (long)Math.sqrt(m-x*x);
			ans += invl(420, mod) * (n+1) % mod
					* ((70*mm%mod*mm%mod*mm+210*mm%mod*mm
							-42*mm%mod*n%mod*n%mod*n%mod*n%mod
							-63*mm%mod*n%mod*n%mod*n%mod
							-140*mm%mod*n%mod*n%mod*x%mod*x%mod
							+63*mm%mod*n%mod*n%mod
							-70*mm%mod*n%mod*x%mod*x%mod
							+42*mm%mod*n%mod
							-210*mm%mod*x%mod*x%mod*x%mod*x%mod
							+210*mm%mod*x%mod*x%mod
							+140*mm%mod
							+20*n%mod*n%mod*n%mod*n%mod*n%mod*n%mod
							+50*n%mod*n%mod*n%mod*n%mod*n%mod
							+84*n%mod*n%mod*n%mod*n%mod*x%mod*x%mod
							-64*n%mod*n%mod*n%mod*n%mod
							+126*n%mod*n%mod*n%mod*x%mod*x%mod
							-146*n%mod*n%mod*n%mod
							+140*n%mod*n%mod*x%mod*x%mod*x%mod*x%mod
							-266*n%mod*n%mod*x%mod*x%mod
							+76*n%mod*n%mod
							+70*n%mod*x%mod*x%mod*x%mod*x%mod
							-154*n%mod*x%mod*x%mod
							+64*n%mod
							+140*x%mod*x%mod*x%mod*x%mod*x%mod*x%mod
							-420*x%mod*x%mod*x%mod*x%mod
							+280*x%mod*x%mod
							)%mod)%mod;
		}
		ans = ans * 4 + mm*(mm+1)%mod*(mm+2)%mod*invl(6,mod)%mod;
		ans %= mod;
		if(ans < 0)ans += mod;
		out.println(ans);
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
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