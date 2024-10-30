//package afc2020;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class E {
	Scanner in;
	PrintWriter out;
	String INPUT = "";
	
	String enigma = "##########################............##########################\n" + 
			"######################....................######################\n" + 
			"####################........................####################\n" + 
			"#################.##...........................#################\n" + 
			"################..###...........................################\n" + 
			"##############.....##.............................##############\n" + 
			"#############......###.............................#############\n" + 
			"###########.........##...............................###########\n" + 
			"##########..........###...............................##########\n" + 
			"#########............##....................#...........#########\n" + 
			"########.............###.#.................####.........########\n" + 
			"#######...............##.#.###.#.............#...........#######\n" + 
			"#######...............##.###.###..#.........#####........#######\n" + 
			"######.................###.#.#.##.##...........#..........######\n" + 
			"#####.................##.#...#..###..........####.#........#####\n" + 
			"######................#....#.##.#.##.........#..###........#####\n" + 
			"####.##..............#######....#..##........##...###.......####\n" + 
			"###...##.............#.#.#...#.###..#.#.......##.##.##.......###\n" + 
			"###.#...............##...#####.#...####........#.#...##......###\n" + 
			"###.###............##..#..#......#.#.#........##...#.#.......###\n" + 
			"##..#.##...........#..###....#######.##........#####..........##\n" + 
			"##.##..............####.#.##.#.#.#....#.......##.#.##.#.......##\n" + 
			"#...#.#.#...........#...###......##.###.......#.....#.##.......#\n" + 
			"#####.###..........###.##...#.##....#.#.......##.##...#........#\n" + 
			"#.....#.#..........#.#..#.#.#..######..........#.#..#####......#\n" + 
			"#.#####.#............##...####.#...#...........####..#..#......#\n" + 
			"..#...#...............##.##.#....####....##...##..#.###.#.......\n" + 
			"###.#.###..............#.#....#.##........#....#.####...#.......\n" + 
			"....#...#..............####.#.#..##.....###..###..#.#.#.........\n" + 
			"###.###.##..............#.######.#..###..#..##.##.....##........\n" + 
			"..#.#...#....................#.......#..#####...#.##.##.........\n" + 
			".####.###...................######.#.####.#.###...#...#.........\n" + 
			"...#..#........................#...###.#..#...##.####.###.......\n" + 
			".#.#.##.#...................##...###.#.##......#....###.##......\n" + 
			".###....#..................##...##.....#......###.###...#.......\n" + 
			".....#.##...................###.#..##...........#..#..###.......\n" + 
			"###.##.#..................###.######..........#.#####.#.........\n" + 
			"..#.#..###..............###...#.#.............###.#...###.......\n" + 
			"#.#.####.#............###...###.###.#..........#..##.##.#......#\n" + 
			"#.###.#............##...###...#...###.........##.##.....#......#\n" + 
			"#.......#..........#..#...###...#..#...........#....#.###......#\n" + 
			"#.####.##..........######...##.#######.........###.##..#.......#\n" + 
			"###..###............#...#.#....#...#.#........##.#.#..........##\n" + 
			"##..##...#.........##.#...#.##...#...##......##..#####........##\n" + 
			"###....###..........###.#.#..#####.#..#.......##.#..#........###\n" + 
			"########...........##.#.####.....######..........##.#........###\n" + 
			"###..#..............#...#..#.##...#..#.........#..#..........###\n" + 
			"####...##...........##.###...#..#...###.......#####.........####\n" + 
			"########.............###.##.##.###.##.#.......#..#.........#####\n" + 
			"#####..##.............#..#..#..#...#.........##.##.........#####\n" + 
			"######..#............###...###.##.###.........#...........######\n" + 
			"#######...............####.#.#..###.#........###.........#######\n" + 
			"#######...............##.###...##.#..........#...........#######\n" + 
			"########.............###..#..###............###.........########\n" + 
			"#########............##..#####.##............#.........#########\n" + 
			"##########...........##...............................##########\n" + 
			"###########.........###..............................###########\n" + 
			"#############.......##.............................#############\n" + 
			"##############......##............................##############\n" + 
			"################...###..........................################\n" + 
			"#################..##..........................#################\n" + 
			"#####################.......................####################\n" + 
			"######################....................######################\n" + 
			"##########################............##########################\n" + 
			"";
	
	void solve()
	{
		String[] e = enigma.split("\n");
		int row = ni(), col = ni();
		out.println(e[col].charAt(row) == '#' ? "OUT" : "IN");
		
		
//		String path = "f5d68ed69f4ec8fcc71db0c55cf6acb9860b5e4a.png";
//		try {
//			BufferedImage bi = ImageIO.read(new File(path));
//			tr(bi.getWidth(), bi.getHeight());
//			boolean[][] g = new boolean[964][965];
//			for(int i = 0;i < 963;i++) {
//				for(int j = 0;j < 964;j++) {
//					g[i][j] = bi.getRGB(i, j) != -1;
//				}
//			}
//			
//			DJSet ds = new DJSet(964*965);
//			for(int i = 0;i < 964;i++) {
//				for(int j = 0;j < 965;j++) {
//					if(i+1 < 964 && !g[i][j] && !g[i+1][j]) {
//						ds.union(i*965+j, (i+1)*965+j);
//					}
//					if(j+1 < 965 && !g[i][j] && !g[i][j+1]) {
//						ds.union(i*965+j, i*965+j+1);
//					}
//				}
//			}
//			
//			boolean[][] ret = new boolean[64][64];
//			for(int i = 0;i < 64;i++) {
//				for(int j = 0;j < 64;j++) {
//					ret[i][j] = ds.equiv(0, (i*15+8)*965+(j*15+8));
//				}
//			}
//			for(int i = 0;i < 64;i++) {
//				tf(ret[i]);
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	public static void tf(boolean... r)
	{
		for(boolean x : r)System.out.print(x?'#':'.');
		System.out.println();
	}

	
	public static class DJSet {
		public int[] upper;

		public DJSet(int n) {
			upper = new int[n];
			Arrays.fill(upper, -1);
		}

		public int root(int x) {
			return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
		}

		public boolean equiv(int x, int y) {
			return root(x) == root(y);
		}

		public boolean union(int x, int y) {
			x = root(x);
			y = root(y);
			if (x != y) {
				if (upper[y] < upper[x]) {
					int d = x;
					x = y;
					y = d;
				}
				upper[x] += upper[y];
				upper[y] = x;
			}
			return x == y;
		}

		public int count() {
			int ct = 0;
			for (int u : upper)
				if (u < 0)
					ct++;
			return ct;
		}
	}

	
	void run() throws Exception
	{
		in = oj ? new Scanner(System.in) : new Scanner(INPUT);
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception
	{
		new E().run();
	}
	
	int ni() { return Integer.parseInt(in.next()); }
	long nl() { return Long.parseLong(in.next()); }
	double nd() { return Double.parseDouble(in.next()); }
	boolean oj = System.getProperty("ONLINE_JUDGE") != null;
	void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}