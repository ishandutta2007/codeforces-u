//package educational.round36;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class G {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int[] lpf = enumLowestPrimeFactors(2000005);
		int[] mob = enumMobiusByLPF(2000004, lpf);
		int mod = 1000000007;
		int n = ni(), K = ni();
		long[] imos = new long[K+2];
		long[] pows = new long[2000005];
		for(int i = 1;i < pows.length;i++){
			pows[i] = pow(i, n, mod);
		}
		for(int i = K;i >= 1;i--){
			for(int j = i;j <= K;j+=i){
				int k = Math.min(j+i, K+1);
				imos[j] += mob[i] * pows[j/i];
				imos[k] -= mob[i] * pows[j/i];
			}
		}
		for(int i = 1;i < imos.length-1;i++){
			imos[i+1] += imos[i];
		}
		long x = 0;
		for(int i = 1;i <= K;i++){
			imos[i] %= mod;
			if(imos[i] < 0)imos[i] += mod;
			x += imos[i] ^ i;
		}
		out.println(x%mod);
	}
	
	public static int[] enumLowestPrimeFactors(int n) {
		int tot = 0;
		int[] lpf = new int[n + 1];
		int u = n + 32;
		double lu = Math.log(u);
		int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
		for (int i = 2; i <= n; i++)
			lpf[i] = i;
		for (int p = 2; p <= n; p++) {
			if (lpf[p] == p)
				primes[tot++] = p;
			int tmp;
			for (int i = 0; i < tot && primes[i] <= lpf[p] && (tmp = primes[i] * p) <= n; i++) {
				lpf[tmp] = primes[i];
			}
		}
		return lpf;
	}

	
	public static int[] enumMobiusByLPF(int n, int[] lpf)
	{
		int[] mob = new int[n+1];
		mob[1] = 1;
		for(int i = 2;i <= n;i++){
			int j = i/lpf[i];
			if(lpf[j] == lpf[i]){
//				mob[i] = 0;
			}else{
				mob[i] = -mob[j];
			}
		}
		return mob;
	}

	
	public static long pow(long a, long n, long mod) {
		//		a %= mod;
		long ret = 1;
		int x = 63 - Long.numberOfLeadingZeros(n);
		for (; x >= 0; x--) {
			ret = ret * ret % mod;
			if (n << 63 - x < 0)
				ret = ret * a % mod;
		}
		return ret;
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
	
	public static void main(String[] args) throws Exception { new G().run(); }
	
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