//package round454;
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
		int[] primes = sieveEratosthenes(100000);
		int n = ni(), m = ni();
		int[] tots = new int[1000];
		tots[0] = m;
		for(int i = 1;i < 1000;i++){
			tots[i] = totient(tots[i-1], primes);
			if(tots[i] == 1){
				tots = Arrays.copyOf(tots, i+1);
				break;
			}
		}
		int P = tots.length;
		
		int[] a = na(n);
		long[][] ps = new long[n][P+1];
		for(int i = 0;i < n;i++){
			Arrays.fill(ps[i], -1);
		}
		for(int Q = ni();Q > 0;Q--){
			int l =ni()-1, r = ni()-1;
			int d = r-l+1;
			if(d >= P)d = P;
			
			if(ps[l][d] == -1){
				long val = 1;
				for(int k = d-1;k >= 0;k--){
					val = powv(a[l+k], val, tots[k]);
				}
				ps[l][d] = val%m;
			}
			out.println(ps[l][d]);
		}
	}
	
	public static long powv(long a, long n, long mod) {
		long ret = 1;
		int x = 63 - Long.numberOfLeadingZeros(n);
		for (; x >= 0; x--) {
			ret = mulv(ret, ret, mod);
			if(n<<~x<0)ret = mulv(ret, a, mod);
		}
		return ret;
	}
	
	public static long mulv(long x, long y, long mod)
	{
		if(x == 0 || y == 0)return 0L;
		if(x >= mod || y >= mod || x * y >= mod){
			return mod + m(x,mod)*m(y,mod)%mod;
		}else{
			return x*y%mod;
		}
	}
	
//	public static long m(long x, long mod){ return x >= mod ? x - mod : x;}
	public static long m(long x, long mod){ return x >= 2*mod ? x % mod : x >= mod ? x - mod : x;}
	
	public static int totient(int n, int[] primes)
	{
		int ret = n;
		for(int p : primes){
			if(p * p > n)break;
			if(n % p == 0){
				ret /= p;
				ret *= p - 1;
			}
			while(n % p == 0)n /= p;
		}
		if(n != 1){
			ret /= n;
			ret *= n - 1;
		}
		return ret;
	}
	
	public static int[] sieveEratosthenes(int n) {
		if (n <= 32) {
			int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
			for (int i = 0; i < primes.length; i++) {
				if (n < primes[i]) {
					return Arrays.copyOf(primes, i);
				}
			}
			return primes;
		}

		int u = n + 32;
		double lu = Math.log(u);
		int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
		ret[0] = 2;
		int pos = 1;

		int[] isnp = new int[(n + 1) / 32 / 2 + 1];
		int sup = (n + 1) / 32 / 2 + 1;

		int[] tprimes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
		for (int tp : tprimes) {
			ret[pos++] = tp;
			int[] ptn = new int[tp];
			for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
				ptn[i >> 5] |= 1 << (i & 31);
			for (int j = 0; j < sup; j += tp) {
				for (int i = 0; i < tp && i + j < sup; i++) {
					isnp[j + i] |= ptn[i];
				}
			}
		}

		// 3,5,7
		// 2x+3=n
		int[] magic = { 0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4, 13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17,
				9, 6, 16, 5, 15, 14 };
		int h = n / 2;
		for (int i = 0; i < sup; i++) {
			for (int j = ~isnp[i]; j != 0; j &= j - 1) {
				int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
				int p = 2 * pp + 3;
				if (p > n)
					break;
				ret[pos++] = p;
				if ((long) p * p > n)
					continue;
				for (int q = (p * p - 3) / 2; q <= h; q += p)
					isnp[q >> 5] |= 1 << q;
			}
		}

		return Arrays.copyOf(ret, pos);
	}

	
	void run() throws Exception
	{
//		int n = 100000, m = 2;//541073537;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		for (int i = 0; i < n; i++) {
//			sb.append(gen.nextInt(1000000000) + " ");
//		}
//		sb.append(n + " ");
//		for (int i = 0; i < n; i++) {
//			while(true){
//				int v1 = gen.nextInt(n);
//				int v2 = v1 + gen.nextInt(n+40);
//				if(v2 >= n)continue;
//				sb.append(Math.min(v1, v2) + 1 + " ");
//				sb.append(Math.max(v1, v2) + 1 + " ");
//				break;
//			}
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