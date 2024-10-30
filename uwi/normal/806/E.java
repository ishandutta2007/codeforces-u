//package round412;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class E2 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int[] a = na(n);
		TMSP.Node root = null;
		TSS.Node rootss = null;
		for(int v : a){
			TMSP.Node node = new TMSP.Node(v);
			root = TMSP.insertb(root, node);
			int ind = TMSP.index(node);
			TSS.add(rootss, ind, TSS.count(rootss), -1);
			rootss = TSS.insert(rootss, ind, new TSS.Node(v-ind));
			
			int trans = TMSP.searchx(root);
			out.println(Math.min(TSS.min(rootss, trans, TSS.count(rootss)) + TSS.count(rootss) - 1, -trans + (TSS.count(rootss) - trans)));
		}
	}
	
	public static class TSS {
		public static Random gen = new Random(0);
		
		static public class Node
		{
			public int v; // value
			public long priority;
			public Node left, right, parent;
			
			public int count;
			
			public int add; // add cover
			public int min; 
			public Node argmin;
			public int tempmin;
			
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
				builder.append(", add=");
				builder.append(add);
				builder.append(", min=");
				builder.append(min);
				builder.append("]");
				return builder.toString();
			}
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


		public static Node update(Node a)
		{
			if(a == null)return null;
			a.count = 1;
			if(a.left != null)a.count += a.left.count;
			if(a.right != null)a.count += a.right.count;
			
			a.min = Integer.MAX_VALUE;
			a.argmin = null;
			if(a.left != null && a.left.min+a.add < a.min){
				a.min = a.left.min+a.add; a.argmin = a.left.argmin;
			}
			if(a.v < a.min){
				a.min = a.v; a.argmin = a;
			}
			if(a.right != null && a.right.min+a.add < a.min){
				a.min = a.right.min+a.add; a.argmin = a.right.argmin;
			}
			return a;
		}
		
		public static Node disconnect(Node a)
		{
			if(a == null)return null;
			a.left = a.right = a.parent = null;
			return update(a);
		}
		
		public static Node root(Node x)
		{
			if(x == null)return null;
			while(x.parent != null)x = x.parent;
			return x;
		}
		
		public static int count(Node a)
		{
			return a == null ? 0 : a.count;
		}
		
		public static void setParent(Node a, Node par)
		{
			if(a != null)a.parent = par;
		}
		
		public static int min(Node a, int L, int R)
		{
			if(a == null || L >= R || L >= count(a) || R <= 0)return Integer.MAX_VALUE / 2;
			if(L <= 0 && R >= count(a)){
				return a.min;
			}else{
				int lmin = min(a.left, L, R);
				int rmin = min(a.right, L-count(a.left)-1, R-count(a.left)-1);
				int min = Math.min(lmin, rmin) + a.add;
				if(L <= count(a.left) && count(a.left) < R)min = Math.min(min, a.v);
				return min;
			}
		}
		
		public static int tempmin(Node x){ return x == null ? Integer.MAX_VALUE : x.tempmin; }
		
		public static Node argmin(Node a, int L, int R)
		{
			if(a == null || L >= R || L >= count(a) || R <= 0)return null;
			if(L <= 0 && R >= count(a)){
				a.argmin.tempmin = a.min;
				return a.argmin;
			}else{
				fall(a);
				Node lmin = argmin(a.left, L, R);
				Node rmin = argmin(a.right, L-count(a.left)-1, R-count(a.left)-1);
				if(lmin != null)lmin.tempmin += a.add;
				if(rmin != null)rmin.tempmin += a.add;
				
				Node argmin = lmin;
				if(L <= count(a.left) && count(a.left) < R){
					if(tempmin(argmin) > a.v){
						argmin = a;
						argmin.tempmin = a.v;
					}
				}
				if(tempmin(argmin) > tempmin(rmin)){
					argmin = rmin;
				}
				return argmin;
			}
		}
		
		static int val(Node n){ return n == null ? Integer.MAX_VALUE / 2 : n.v; }
		
		public static void add(Node a, int L, int R, int V)
		{
			if(a == null || L >= R || L >= count(a) || R <= 0)return;
			if(L <= 0 && R >= count(a)){
				a.v += V;
				a.add += V;
			}else{
				add(a.left, L, R, V);
				add(a.right, L-count(a.left)-1, R-count(a.left)-1, V);
				if(L <= count(a.left) && count(a.left) < R)a.v += V;
			}
			update(a);
		}
		
		public static void fall(Node a)
		{
			if(a == null)return;
			if(a.left != null){
				a.left.add += a.add;
				a.left.v += a.add;
				update(a.left);
			}
			if(a.right != null){
				a.right.add += a.add;
				a.right.v += a.add;
				update(a.right);
			}
			a.add = 0;
			update(a);
		}
		
		public static Node merge(Node a, Node b)
		{
			if(b == null)return a;
			if(a == null)return b;
			if(a.priority > b.priority){
				fall(a);
				setParent(a.right, null);
				setParent(b, null);
				a.right = merge(a.right, b);
				setParent(a.right, a);
				return update(a);
			}else{
				fall(b);
				setParent(a, null);
				setParent(b.left, null);
				b.left = merge(a, b.left);
				setParent(b.left, b);
				return update(b);
			}
		}
		
		// [0,K),[K,N)
		public static Node[] split(Node a, int K)
		{
			if(a == null)return new Node[]{null, null};
			fall(a);
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
			fall(a);
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
				fall(a);
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
				fall(a);
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
		
		static Node[] Q = new Node[100];
		public static Node update(Node a, int K, int v)
		{
			int p = 0;
			while(a != null){
				Q[p++] = a;
				if(K < count(a.left)){
					a = a.left;
				}else if(K == count(a.left)){
					break;
				}else{
					K = K - count(a.left)-1;
					a = a.right;
				}
			}
			a.v = v;
			while(p > 0){
				update(Q[--p]);
			}
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
		
		public static Node[] nodes(Node a) { return nodes(a, new Node[count(a)], 0, count(a)); }
		public static Node[] nodes(Node a, Node[] ns, int L, int R)
		{
			if(a == null)return ns;
			nodes(a.left, ns, L, L+count(a.left));
			ns[L+count(a.left)] = a;
			nodes(a.right, ns, R-count(a.right), R);
			return ns;
		}

	}
	
	public static class TMSP {
		public static Random gen = new Random();
		
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
			
			// TODO
			return a;
		}
		
		public static void propagate(Node x)
		{
			for(;x != null;x = x.parent)update(x);
		}
		
		public static Node disconnect(Node a)
		{
			if(a == null)return null;
			a.left = a.right = a.parent = null;
			return update(a);
		}
		
		public static Node root(Node x)
		{
			if(x == null)return null;
			while(x.parent != null)x = x.parent;
			return x;
		}
		
		public static int count(Node a)
		{
			return a == null ? 0 : a.count;
		}
		
		public static void setParent(Node a, Node par)
		{
			if(a != null)a.parent = par;
		}
		
		public static Node merge(Node a, Node b, Node... c)
		{
			Node x = merge(a, b);
			for(Node n : c)x = merge(x, n);
			return x;
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
		
		public static Node[] split(Node x)
		{
			if(x == null)return new Node[]{null, null};
			if(x.left != null)x.left.parent = null;
			Node[] sp = new Node[]{x.left, x};
			x.left = null;
			update(x);
			while(x.parent != null){
				Node p = x.parent;
				x.parent = null;
				if(x == p.left){
					p.left = sp[1];
					if(sp[1] != null)sp[1].parent = p;
					sp[1] = p;
				}else{
					p.right = sp[0];
					if(sp[0] != null)sp[0].parent = p;
					sp[0] = p;
				}
				update(p);
				x = p;
			}
			return sp;
		}
		
		public static Node[] split(Node a, int... ks)
		{
			int n = ks.length;
			if(n == 0)return new Node[]{a};
			for(int i = 0;i < n-1;i++){
				if(ks[i] > ks[i+1])throw new IllegalArgumentException(Arrays.toString(ks));
			}
			
			Node[] ns = new Node[n+1];
			Node cur = a;
			for(int i = n-1;i >= 0;i--){
				Node[] sp = split(cur, ks[i]);
				cur = sp[0];
				ns[i] = sp[0];
				ns[i+1] = sp[1];
			}
			return ns;
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
		
		public static Node insertb(Node root, Node x)
		{
			int ind = lowerBound(root, x.v);
			return insert(root, ind, x);
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
		
		public static Node mergeTechnically(Node x, Node y)
		{
			if(count(x) > count(y)){
				Node d = x; x = y; y = d;
			}
			// |x|<=|y|
			for(Node cur : nodesdfs(x))y = insertb(y, disconnect(cur));
			return y;
		}
		
		public static int lowerBound(Node a, int q)
		{
			int lcount = 0;
			while(a != null){
				if(a.v >= q){
					a = a.left;
				}else{
					lcount += count(a.left) + 1;
					a = a.right;
				}
			}
			return lcount;
		}
		
		public static int search(Node a, int q)
		{
			int lcount = 0;
			while(a != null){
				if(a.v == q){
					lcount += count(a.left);
					break;
				}
				if(q < a.v){
					a = a.left;
				}else{
					lcount += count(a.left) + 1;
					a = a.right;
				}
			}
			return a == null ? -(lcount+1) : lcount;
		}
		
		public static int searchx(Node a)
		{
			int lcount = 0;
			while(a != null){
				if(a.v < -(lcount + count(a.left))){
					lcount += count(a.left) + 1;
					a = a.right;
				}else{
					a = a.left;
				}
			}
			return lcount;
		}
		
		public static Node next(Node x)
		{
			if(x == null)return null;
			if(x.right != null){
				x = x.right;
				while(x.left != null)x = x.left;
				return x;
			}else{
				while(true){
					Node p = x.parent;
					if(p == null)return null;
					if(p.left == x)return p;
					x = p;
				}
			}
		}
		
		public static Node prev(Node x)
		{
			if(x == null)return null;
			if(x.left != null){
				x = x.left;
				while(x.right != null)x = x.right;
				return x;
			}else{
				while(true){
					Node p = x.parent;
					if(p == null)return null;
					if(p.right == x)return p;
					x = p;
				}
			}
		}
		
		public static Node[] nodes(Node a) { return nodes(a, new Node[count(a)], 0, count(a)); }
		public static Node[] nodes(Node a, Node[] ns, int L, int R)
		{
			if(a == null)return ns;
			nodes(a.left, ns, L, L+count(a.left));
			ns[L+count(a.left)] = a;
			nodes(a.right, ns, R-count(a.right), R);
			return ns;
		}
		
		// faster than nodes but inconsistent
		public static Node[] nodesdfs(Node a) { return nodesdfs(a, new Node[a.count], new int[]{0}); }
		public static Node[] nodesdfs(Node a, Node[] ns, int[] pos)
		{
			if(a == null)return ns;
			ns[pos[0]++] = a;
			nodesdfs(a.left, ns, pos);
			nodesdfs(a.right, ns, pos);
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