//package round263;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

public class E {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		long n = nl();
		char[] s = ns().toLowerCase().toCharArray();
		SuffixAutomatonOfBit sa = SuffixAutomatonOfBit.build(s);
		SuffixAutomatonOfBit.Node root = sa.nodes[0];
		long[][] M = new long[4][4];
		int m = sa.gen;
		for(char c = 'a';c <= 'd';c++){
			boolean[] ved = new boolean[m];
			SuffixAutomatonOfBit.Node sroot = root.getNext(c);
			Queue<SuffixAutomatonOfBit.Node> q = new ArrayDeque<SuffixAutomatonOfBit.Node>();
			q.add(sroot);
			int[] d = new int[m];
			ved[sroot.id] = true;
			int[] cycle = new int[4];
			Arrays.fill(cycle, 999999999);
			d[sroot.id] = 1;
			while(!q.isEmpty()){
				SuffixAutomatonOfBit.Node cur = q.poll();
				for(char lc = 'a';lc <= 'd';lc++){
					if(!cur.containsKeyNext(lc)){
						cycle[lc-'a'] = Math.min(cycle[lc-'a'], d[cur.id]);
					}else{
						SuffixAutomatonOfBit.Node next = cur.getNext(lc);
						if(!ved[next.id]){
							ved[next.id] = true;
							d[next.id] = d[cur.id] + 1;
							q.add(next);
						}
					}
				}
			}
			for(int i = 0;i < 4;i++){
				M[i][c-'a'] = cycle[i];
			}
		}
		
		double[][] MD = new double[4][4];
		for(int j = 0;j < 4;j++){
			for(int k = 0;k < 4;k++){
				MD[j][k] = M[j][k];
			}
		}
		
		long low = 0L, high = 1000000000000000002L;
		outer:
		while(high - low > 1){
			long x = high+low>>>1;
			double[] ud = pow(MD, new double[4], x);
			for(double w : ud){
				if(w > 2E18){
					high = x;
					continue outer;
				}
			}
		
			long[] u = pow(M, new long[4], x);
			long min = Long.MAX_VALUE;
			for(long v : u)min = Math.min(min, v);
			if(min >= n){
				high = x;
			}else{
				low = x;
			}
		}
		out.println(high);
//		tr(sa.toDot(true, false));
	}
	
	public static long[] mul(long[][] A, long[] v)
	{
		int m = A.length;
		int n = v.length;
		long[] w = new long[m];
		for(int i = 0;i < m;i++){
			long sum = Long.MAX_VALUE;
			for(int k = 0;k < n;k++){
				long x = A[i][k] + v[k];
				if(x < sum)sum = x;
			}
			w[i] = sum;
		}
		return w;
	}
	
	public static long[][] p2(long[][] A)
	{
		int n = A.length;
		long[][] C = new long[n][n];
		for(int i = 0;i < n;i++){
			Arrays.fill(C[i], Long.MAX_VALUE);
			for(int k = 0;k < n;k++){
				for(int j = 0;j < n;j++){
					long x = A[i][k] + A[k][j];
					if(x < C[i][j])C[i][j] = x;
				}
			}
		}
		return C;
	}
	
	// A^e*v
	public static long[] pow(long[][] A, long[] v, long m)
	{
		long[][] mu = A;
		long[] r = v;
		for(;m > 0;m>>>=1){
			if(m<<63<0)r = mul(mu, r);
			mu = p2(mu);
		}
		return r;
	}
	
	public static double[] mul(double[][] A, double[] v)
	{
		int m = A.length;
		int n = v.length;
		double[] w = new double[m];
		for(int i = 0;i < m;i++){
			double sum = Double.POSITIVE_INFINITY;
			for(int k = 0;k < n;k++){
				double x = A[i][k] + v[k];
				if(x < sum)sum = x;
			}
			w[i] = sum;
		}
		return w;
	}
	
	public static double[][] p2(double[][] A)
	{
		int n = A.length;
		double[][] C = new double[n][n];
		for(int i = 0;i < n;i++){
			Arrays.fill(C[i], Double.POSITIVE_INFINITY);
			for(int k = 0;k < n;k++){
				for(int j = 0;j < n;j++){
					double x = A[i][k] + A[k][j];
					if(x < C[i][j])C[i][j] = x;
				}
			}
		}
		return C;
	}
	
	// A^e*v
	public static double[] pow(double[][] A, double[] v, long m)
	{
		double[][] mu = A;
		double[] r = v;
		for(;m > 0;m>>>=1){
			if(m<<63<0)r = mul(mu, r);
			mu = p2(mu);
		}
		return r;
	}
	
	public static class SuffixAutomatonOfBit {
		public Node t0;
		public int len;
		public Node[] nodes;
		public int gen;
		
		private SuffixAutomatonOfBit(int n)
		{
			gen = 0;
			nodes = new Node[2*n];
			this.t0 = makeNode(0, null);
		}
		
		private Node makeNode(int len, Node original)
		{
			Node node = new Node();
			node.id = gen;
			node.original = original;
			node.len = len;
			nodes[gen++] = node;
			return node;
		}
		
		public static class Node
		{
			public int id;
			public int len;
			public char key;
			public Node link;
			private Node[] next = new Node[3];
			public Node original;
			public int np = 0;
			public int hit = 0;
			
			public void putNext(char c, Node to)
			{
				to.key = c;
				if(hit<<31-(c-'a')<0){
					for(int i = 0;i < np;i++){
						if(next[i].key == c){
							next[i] = to;
							return;
						}
					}
				}
				hit |= 1<<c-'a';
				if(np == next.length){
					next = Arrays.copyOf(next, np*2);
				}
				next[np++] = to;
			}
			
			public boolean containsKeyNext(char c)
			{
				return hit<<~(c-'a')<0;
//				for(int i = 0;i < np;i++){
//					if(next[i].key == c)return true;
//				}
//				return false;
			}
			
			public Node getNext(char c)
			{
				if(hit<<~(c-'a')<0){
					for(int i = 0;i < np;i++){
						if(next[i].key == c)return next[i];
					}
				}
				return null;
			}
			
			public List<String> suffixes(char[] s)
			{
				List<String> list = new ArrayList<String>();
				if(id == 0)return list;
				int first = original != null ? original.len : len;
				for(int i = link.len + 1;i <= len;i++){
					list.add(new String(s, first - i, i));
				}
				return list;
			}
		}

		public static SuffixAutomatonOfBit build(char[] str)
		{
			int n = str.length;
			SuffixAutomatonOfBit sa = new SuffixAutomatonOfBit(n);
			sa.len = str.length;
			
			Node last = sa.t0;
			for(char c : str){
				last = sa.extend(last, c);
			}
			
			return sa;
		}
		
		public Node extend(Node last, char c)
		{
			Node cur = makeNode(last.len+1, null);
			Node p;
			for(p = last; p != null && !p.containsKeyNext(c);p = p.link){
				p.putNext(c, cur);
			}
			if(p == null){
				cur.link = t0;
			}else{
				Node q = p.getNext(c); // not null
				if(p.len + 1 == q.len){
					cur.link = q;
				}else{
					Node clone = makeNode(p.len+1, q);
					clone.next = Arrays.copyOf(q.next, q.next.length);
					clone.hit = q.hit;
					clone.np = q.np;
					clone.link = q.link;
					for(;p != null && q.equals(p.getNext(c)); p = p.link){
						p.putNext(c, clone);
					}
					q.link = cur.link = clone;
				}
			}
			return cur;
		}
		
		public SuffixAutomatonOfBit lexSort()
		{
			for(int i = 0;i < gen;i++){
				Node node = nodes[i];
				Arrays.sort(node.next, 0, node.np, new Comparator<Node>() {
					public int compare(Node a, Node b) {
						return a.key - b.key;
					}
				});
			}
			return this;
		}
		
		public SuffixAutomatonOfBit sortTopologically()
		{
			int[] indeg = new int[gen];
			for(int i = 0;i < gen;i++){
				for(int j = 0;j < nodes[i].np;j++){
					indeg[nodes[i].next[j].id]++;
				}
			}
			Node[] sorted = new Node[gen];
			sorted[0] = t0;
			int p = 1;
			for(int i = 0;i < gen;i++){
				Node cur = sorted[i];
				for(int j = 0;j < cur.np;j++){
					if(--indeg[cur.next[j].id] == 0){
						sorted[p++] = cur.next[j];
					}
				}
			}
			
			for(int i = 0;i < gen;i++)sorted[i].id = i;
			nodes = sorted;
			return this;
		}
		
		// visualizer
		
		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			for(Node n : nodes){
				if(n != null){
					sb.append(String.format("{id:%d, len:%d, link:%d, cloned:%b, ",
							n.id,
							n.len,
							n.link != null ? n.link.id : null,
							n.original.id));
					sb.append("next:{");
					for(int i = 0;i < n.np;i++){
						sb.append(n.next[i].key + ":" + n.next[i].id + ",");
					}
					sb.append("}");
					sb.append("}");
					sb.append("\n");
				}
			}
			return sb.toString();
		}
		
		public String toGraphviz(boolean next, boolean suffixLink)
		{
			StringBuilder sb = new StringBuilder("http://chart.apis.google.com/chart?cht=gv:dot&chl=");
			sb.append("digraph{");
			for(Node n : nodes){
				if(n != null){
					if(suffixLink && n.link != null){
						sb.append(n.id)
						.append("->")
						.append(n.link.id)
						.append("[style=dashed],");
					}
					
					if(next && n.next != null){
						for(int i = 0;i < n.np;i++){
							sb.append(n.id)
							.append("->")
							.append(n.next[i].id)
							.append("[label=")
							.append(n.next[i].key)
							.append("],");
						}
					}
				}
			}
			sb.append("}");
			return sb.toString();
		}
		
		public String label(Node n)
		{
			if(n.original != null){
				return n.id + "C";
			}else{
				return n.id + "";
			}
		}
		
		public String toDot(boolean next, boolean suffixLink)
		{
			StringBuilder sb = new StringBuilder("digraph{\n");
			sb.append("graph[rankdir=LR];\n");
			sb.append("node[shape=circle];\n");
			for(Node n : nodes){
				if(n != null){
					if(suffixLink && n.link != null){
						sb.append("\"" + label(n) + "\"")
						.append("->")
						.append("\"" + label(n.link) + "\"")
						.append("[style=dashed];\n");
					}
					
					if(next && n.next != null){
						for(int i = 0;i < n.np;i++){
							sb.append("\"" + label(n) + "\"")
							.append("->")
							.append("\"" + label(n.next[i]) + "\"")
							.append("[label=\"")
							.append(n.next[i].key)
							.append("\"];\n");
						}
					}
				}
			}
			sb.append("}\n");
			return sb.toString();
		}
	}
	
	
	void run() throws Exception
	{
//		int n = 100000, m = 99999;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(gen.nextInt(1000000000)*(long)gen.nextInt(1000000000) + " ");
//		for (int i = 0; i < n; i++) {
//			sb.append((char)(gen.nextInt(4)+'A'));
//		}
//		INPUT = sb.toString();
		
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new E().run(); }
	
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