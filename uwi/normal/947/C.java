//package round470;
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
		int n = ni();
		int[] a = na(n);
		int[] P = na(n);
		TrieArrayBinary trie = new TrieArrayBinary(31);
		for(int v : P)trie.add(v);
		for(int v : a){
			long ret = trie.xormin(v);
			out.print(ret + " ");
			trie.remove(ret^v);
		}
	}
	
	
	public static class L {
		public int[] a;
		public int p = 0;
		
		public L(int n) { a = new int[n]; }
		public L add(int n)
		{
			if(p >= a.length)a = Arrays.copyOf(a, a.length*3/2+1);
			a[p++] = n;
			return this;
		}
		public int size() { return p; }
		public L clear() { p = 0; return this; }
		public int[] toArray() { return Arrays.copyOf(a, p); }
		@Override
		public String toString() {return Arrays.toString(toArray());}
	}
	
	public static class TrieArrayBinary {
		// root = 0
		public L next;
		public int gen;
		public int W;
		public L hit;
		
		public TrieArrayBinary(int W)
		{
			this.W = W;
			this.next = new L(2);
			this.hit = new L(1);
			this.gen = 1;
			this.next.add(-1).add(-1);
			this.hit.add(0);
		}
		
		public void add(long s)
		{
			int cur = 0;
			for(int d = W-1;d >= 0;d--){
				int v = (int)(s>>>d&1);
				int nex = next.a[cur*2+v];
				if(nex == -1){
					nex = next.a[cur*2+v] = gen++;
					next.add(-1).add(-1);
					hit.add(0);
				}
				cur = nex;
			}
			hit.a[cur]++;
		}
		
		public void remove(long s)
		{
			int cur = 0;
			int[] hist = new int[W];
			for(int d = W-1;d >= 0;d--){
				hist[d] = cur;
				int v = (int)(s>>>d&1);
				int nex = next.a[cur*2+v];
				if(nex == -1){
					throw new RuntimeException();
				}
				cur = nex;
			}
			if(--hit.a[cur] == 0){
				for(int d = 0;d < W;d++){
					int v = (int)(s>>>d&1);
					next.a[hist[d]*2|v] = -1;
					if(next.a[hist[d]*2|v^1] != -1)break;
				}
			}
		}
		
		public long xormin(long x)
		{
			int cur = 0;
			long ret = 0;
			for(int d = W-1;d >= 0;d--){
				if(cur == -1){
					ret |= x<<-d>>>-d;
					break;
				}
				int xd = (int)(x>>>d&1);
				if(next.a[cur*2|xd] != -1){
					cur = next.a[cur*2|xd];
				}else{
					ret |= 1L<<d;
					cur = next.a[cur*2|xd^1];
				}
			}
			return ret;
		}
		
		public int[] des()
		{
			int[] des = new int[gen];
			for(int i = gen-1;i >= 0;i--){
				if(next.a[2*i] != -1)des[i] += des[next.a[2*i]];
				if(next.a[2*i+1] != -1)des[i] += des[next.a[2*i+1]];
				if(des[i] == 0)des[i] = 1;
			}
			return des;
		}
		
		public int mex(long x, int[] des)
		{
			int ret = 0;
			for(int cur = 0, d = W-1;d >= 0 && cur != -1;d--){
				int xd = (int)(x>>>d&1);
				if(next.a[2*cur|xd] != -1 && des[next.a[2*cur|xd]] == 1<<d){
					ret |= 1<<d;
					cur = next.a[2*cur|xd^1];
				}else{
					cur = next.a[2*cur|xd];
				}
			}
			return ret;
		}
		
		public boolean contains(long x, int low)
		{
			int cur = 0;
			for(int d = W-1;d >= low;d--){
				int v = (int)(x>>>d&1);
				int nex = next.a[cur*2+v];
				if(nex == -1)return false;
			}
			return true;
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