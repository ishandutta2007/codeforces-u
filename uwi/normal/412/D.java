//package codestrike.r1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class D {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni(), m = ni();
		int[] from = new int[m];
		int[] to = new int[m];
		for(int i = 0;i < m;i++){
			from[i] = ni()-1;
			to[i] = ni()-1;
		}
		int[][] g = packD(n, to, from);
		
		Node root = null;
		Node[] nodes = new Node[n];
		for(int i = 0;i < n;i++)nodes[i] = new Node(i);
		root = insert(root, 0, nodes[0]);
		for(int i = 1;i < n;i++){
			int K = i;
			for(int e : g[i]){
				if(e < i){
					K = Math.min(K, index(nodes[e]));
				}
			}
			root = insert(root, K, nodes[i]);
		}
		Node[] ar = nodes(root);
		for(int i = 0;i < ar.length;i++){
			out.print(ar[i].v+1);
			out.print(" ");
		}
		out.println();
	}
	
		static Random gen = new Random();
		
		static public class Node
		{
			public int v; // value
			public long priority;
			public Node left, right, parent;
			
			public int count;
			
			public Node(int v)
			{
				this.v = v;
				priority = gen.nextLong();
				update(this);
			}

			@Override
			public String toString() {
				StringBuilder builder = new StringBuilder();
				builder.append("Node [v=");
				builder.append(v);
				builder.append(", count=");
				builder.append(count);
				builder.append(", parent=");
				builder.append(parent != null ? parent.v : "null");
				builder.append("]");
				return builder.toString();
			}
		}

		public static Node update(Node a)
		{
			if(a == null)return null;
			a.count = 1;
			if(a.left != null)a.count += a.left.count;
			if(a.right != null)a.count += a.right.count;
			return a;
		}
		
		public static Node disconnect(Node a)
		{
			if(a == null)return null;
			a.left = a.right = a.parent = null;
			return update(a);
		}
		
		public static Node merge(Node a, Node b)
		{
			if(b == null)return a;
			if(a == null)return b;
			if(a.priority > b.priority){
				setParent(a.right, null);
				setParent(b, null);
				a.right = merge(a.right, b);
				setParent(a.right, a);
				return update(a);
			}else{
				setParent(a, null);
				setParent(b.left, null);
				b.left = merge(a, b.left);
				setParent(b.left, b);
				return update(b);
			}
		}
		
		public static int count(Node a)
		{
			return a == null ? 0 : a.count;
		}
		
		public static void setParent(Node a, Node par)
		{
			if(a != null)a.parent = par;
		}
		
		// [0,K),[K,N)
		public static Node[] split(Node a, int K)
		{
			if(a == null)return new Node[]{null, null};
			if(K <= count(a.left)){
				setParent(a.left, null);
				Node[] s = split(a.left, K);
				a.left = s[1];
				setParent(a.left, a);
				s[1] = update(a);
				return s;
			}else{
				setParent(a.right, null);
				Node[] s = split(a.right, K-count(a.left)-1);
				a.right = s[0];
				setParent(a.right, a);
				s[0] = update(a);
				return s;
			}
		}
		
		public static Node insert(Node a, int K, Node b)
		{
			if(a == null)return b;
			if(b.priority < a.priority){
				if(K <= count(a.left)){
					a.left = insert(a.left, K, b);
					setParent(a.left, a);
				}else{
					a.right = insert(a.right, K-count(a.left)-1, b);
					setParent(a.right, a);
				}
				return update(a);
			}else{
				Node[] ch = split(a, K);
				b.left = ch[0]; b.right = ch[1];
				setParent(b.left, b);
				setParent(b.right, b);
				return update(b);
			}
		}
		
		// delete K-th
		public static Node erase(Node a, int K)
		{
			if(a == null)return null;
			if(K < count(a.left)){
				a.left = erase(a.left, K);
				setParent(a.left, a);
				return update(a);
			}else if(K == count(a.left)){
				setParent(a.left, null);
				setParent(a.right, null);
				Node aa = merge(a.left, a.right);
				disconnect(a);
				return aa;
			}else{
				a.right = erase(a.right, K-count(a.left)-1);
				setParent(a.right, a);
				return update(a);
			}
		}
		
		public static Node get(Node a, int K)
		{
			while(a != null){
				if(K < count(a.left)){
					a = a.left;
				}else if(K == count(a.left)){
					break;
				}else{
					K = K - count(a.left)-1;
					a = a.right;
				}
			}
			return a;
		}
		
		public static Node update(Node a, int K, int v)
		{
			Node f = get(a, K);
			a = erase(a, K);
			f.v = v;
			a = insert(a, K, f);
			return a;
		}
		
		public static int index(Node a)
		{
			if(a == null)return -1;
			int ind = count(a.left);
			while(a != null){
				Node par = a.parent;
				if(par != null && par.right == a){
					ind += count(par.left) + 1;
				}
				a = par;
			}
			return ind;
		}
		
		public static Node[] nodes(Node a) { return nodes(a, new Node[a.count], 0, a.count); }
		public static Node[] nodes(Node a, Node[] ns, int L, int R)
		{
			if(a == null)return ns;
			nodes(a.left, ns, L, L+count(a.left));
			ns[L+count(a.left)] = a;
			nodes(a.right, ns, R-count(a.right), R);
			return ns;
		}
		
		public static String toString(Node a, String indent)
		{
			if(a == null)return "";
			StringBuilder sb = new StringBuilder();
			sb.append(toString(a.left, indent + "  "));
			sb.append(indent).append(a).append("\n");
			sb.append(toString(a.right, indent + "  "));
			return sb.toString();
		}
	
	static int[][] packD(int n, int[] from, int[] to) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for(int f : from)
			p[f]++;
		for(int i = 0;i < n;i++)
			g[i] = new int[p[i]];
		for(int i = 0;i < from.length;i++){
			g[from[i]][--p[from[i]]] = to[i];
		}
		return g;
	}
	
	void run() throws Exception
	{
//		int n = 30000, m = 100000;
//		Random gen = new Random(1);
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		out:
//		for(int i = 0;i < n;i++){
//			for(int j = i+1;j < n;j++){
//				if(gen.nextBoolean()){
//					sb.append(i+1 + " ");
//					sb.append(j+1 + " ");
//				}else{
//					sb.append(j+1 + " ");
//					sb.append(i+1 + " ");
//				}
//				if(--m == 0)break out;
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