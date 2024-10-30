//package abbyy2.hard;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class DM {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	int[][] b = new int[4][4];
	long S;
	
	void solve()
	{
		int n = ni();
		int nn = n*n;
		int[] a = new int[nn];
		for(int i = 0;i < nn;i++)a[i] = ni();
		int[] ord = new int[nn];
		for(int i = 0;i < nn;i++)ord[i] = i;
		S = 0;
		for(int i = 0;i < nn;i++){
			S += a[i];
		}
		S /= n;
		out.println(S);
		if(n <= 3){
			outer:
			do{
				for(int i = 0;i < n;i++){
					{
						long ls = 0;
						for(int j = 0;j < n;j++)ls += a[ord[i*n+j]];
						if(ls != S)continue outer;
					}
					{
						long ls = 0;
						for(int j = 0;j < n;j++)ls += a[ord[j*n+i]];
						if(ls != S)continue outer;
					}
				}
				{
					long ls = 0;
					for(int i = 0;i < n;i++){
						ls += a[ord[i*n+i]];
					}
					if(ls != S)continue outer;
				}
				{
					long ls = 0;
					for(int i = 0;i < n;i++){
						ls += a[ord[i*n+n-1-i]];
					}
					if(ls != S)continue outer;
				}
				for(int i = 0;i < n;i++){
					for(int j = 0;j < n;j++){
						if(j > 0)out.print(" ");
						out.print(a[ord[i*n+j]]);
					}
					out.println();
				}
				break;
			}while(nextPermutation(ord));
		}else{
			Arrays.sort(a);
			rec(0, -1>>>32-16, a);
		}
	}
	
	int[][] F = 
		{
		{0, 0},
		{0, 1},
		{0, 2},
		{0, 3},
		{1, 0},
		{2, 0},
		{3, 0},
		{2, 1},
		{1, 2},
		{1, 1},
		{1, 3},
		{3, 1},
		{2, 2},
		{2, 3},
		{3, 2},
		{3, 3}
	};
	
	int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4, 13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
	
	boolean rec(int pos, int left, int[] a)
	{
		if(pos == 4){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[0][i];
				if(ls != S)return false;
			}
		}else if(pos == 7){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][0];
				if(ls != S)return false;
			}
		}else if(pos == 9){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][3-i];
				if(ls != S)return false;
			}
		}else if(pos == 11){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[1][i];
				if(ls != S)return false;
			}
		}else if(pos == 12){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][1];
				if(ls != S)return false;
			}
		}else if(pos == 14){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[2][i];
				if(ls != S)return false;
			}
		}else if(pos == 15){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][2];
				if(ls != S)return false;
			}
		}else if(pos == 16){
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][3];
				if(ls != S)return false;
			}
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[3][i];
				if(ls != S)return false;
			}
			{
				long ls = 0;
				for(int i = 0;i < 4;i++)ls += b[i][i];
				if(ls != S)return false;
			}
			
			for(int i = 0;i < 4;i++){
				for(int j = 0;j < 4;j++){
					if(j > 0)out.print(" ");
					out.print(b[i][j]);
				}
				out.println();
			}
			return true;
		}
		
		int r = F[pos][0], c = F[pos][1];
		int prev = Integer.MIN_VALUE;
		for(int q = left;q > 0;q&=q-1){
			int bit = magic[(q&-q)*0x076be629>>>27];
			if(a[bit] == prev)continue;
			b[r][c] = a[bit];
			if(rec(pos+1, left^(q&-q), a))return true;
			prev = a[bit];
		}
		return false;
	}
	
	public static boolean nextPermutation(int[] src)
	{
		int i;
		for(i = src.length - 2;i >= 0 && src[i] > src[i+1];i--);
		if(i == -1)return false;
		int j;
		for(j = i + 1;j < src.length && src[i] < src[j];j++);
		int d = src[i]; src[i] = src[j - 1]; src[j - 1] = d;
		for(int p = i + 1, q = src.length - 1;p < q;p++,q--){
			d = src[p]; src[p] = src[q]; src[q] = d;
		}
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
	
	public static void main(String[] args) throws Exception
	{
		new DM().run();
	}
	
	public int ni()
	{
		try {
			int num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	public long nl()
	{
		try {
			long num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	public String ns()
	{
		try{
			int b = 0;
			StringBuilder sb = new StringBuilder();
			while((b = is.read()) != -1 && (b == '\r' || b == '\n' || b == ' '));
			if(b == -1)return "";
			sb.append((char)b);
			while(true){
				b = is.read();
				if(b == -1)return sb.toString();
				if(b == '\r' || b == '\n' || b == ' ')return sb.toString();
				sb.append((char)b);
			}
		} catch (IOException e) {
		}
		return "";
	}
	
	public char[] ns(int n)
	{
		char[] buf = new char[n];
		try{
			int b = 0, p = 0;
			while((b = is.read()) != -1 && (b == ' ' || b == '\r' || b == '\n'));
			if(b == -1)return null;
			buf[p++] = (char)b;
			while(p < n){
				b = is.read();
				if(b == -1 || b == ' ' || b == '\r' || b == '\n')break;
				buf[p++] = (char)b;
			}
			return Arrays.copyOf(buf, p);
		} catch (IOException e) {
		}
		return null;
	}
	
	
	double nd() { return Double.parseDouble(ns()); }
	boolean oj = System.getProperty("ONLINE_JUDGE") != null;
	void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}