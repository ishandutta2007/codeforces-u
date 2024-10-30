//package round98;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class E {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		char[] line = ns(200000);
		int n = line.length;
		int[] sc = new int[n+1];
		for(int i = 0;i < n;i++){
			if(line[i] >= 'A' && line[i] <= 'Z'){
				line[i] += 'a' - 'A';
			}
			if("aeiou".indexOf(line[i]) >= 0){
				sc[i+1] = sc[i]-1;
			}else{
				sc[i+1] = sc[i]+2;
			}
		}
		
		int[] first = new int[600002];
		Arrays.fill(first, -1);
		int O = 200000;
		for(int i = 0;i <= n;i++){
			if(first[sc[i]+O] == -1){
				first[sc[i]+O] = i;
			}
		}
		
		int min = n+100;
		for(int i = 0;i <= 600001;i++){
			if(first[i] == -1 || min < first[i]){
				first[i] = min;
			}else{
				min = first[i];
			}
		}
		
		int maxlen = 0;
		for(int i = 1;i <= n;i++){
			maxlen = Math.max(maxlen, i-first[sc[i]+O]);
		}
		if(maxlen == 0){
			out.println("No solution");
		}else{
			int lc = 0;
			for(int i = maxlen;i <= n;i++){
				if(sc[i]-sc[i-maxlen] >= 0){
					lc++;
				}
			}
			out.println(maxlen + " " + lc);
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
	
	public static void main(String[] args) throws Exception
	{
		new E().run();
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