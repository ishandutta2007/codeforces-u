//package croc2012.mbtu.fin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class A {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int n = ni();
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		
		int minus = 0;
		int[] len = new int[n];
		int p = 0;
		int prev = 0;
		for(int i = 0;i < n;i++){
			if(a[i] < 0){
				minus++;
				if(minus == 3){
					len[p++] = i-1-prev+1;
					prev = i;
					minus = 1;
				}
			}
		}
		len[p++] = n-1-prev+1;
		out.println(p);
		for(int i = 0;i < p;i++){
			if(i > 0)out.print(" ");
			out.print(len[i]);
		}
		out.println();
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
		new A().run();
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