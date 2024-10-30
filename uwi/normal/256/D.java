//package round156;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D2 {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
	
	void solve()
	{
		int[][] ret = new int[257][];
		ret[1] = new int[]{0, 1};
		ret[2] = new int[]{1, 2, 1};
		ret[4] = new int[]{109, 80, 30, 32, 5};
		ret[8] = new int[]{7593125, 4898872, 1897056, 1757896, 415870, 147224, 59808, 6824, 541};
		ret[16] = new int[]{391464593, 569389279, 312106242, 578944191, 515619293, 117354594, 161873957, 472062098, 428982705, 565460332, 735127505, 33900006, 293783147, 746623577, 290516100, 776830421, 166257661};
		ret[32] = new int[]{212568440, 186175544, 519860281, 612549793, 737154512, 651912135, 44684703, 387329805, 195188679, 444199674, 20119449, 142138221, 132906648, 628771752, 513156108, 486011304, 225200475, 391579767, 342079395, 647490480, 264125799, 629394768, 360252438, 398169441, 157617135, 229471758, 736955478, 211593382, 468868529, 683961846, 584837659, 261086313, 464207287};
		ret[64] = new int[]{550671827, 520987612, 63744786, 421203111, 279421821, 202356819, 370826673, 67309914, 291740751, 496385218, 199307003, 433540170, 643714911, 239654268, 554473341, 156006018, 469781928, 522840969, 320813094, 628905585, 575661807, 611444862, 352474101, 676760679, 232198470, 732674124, 446575689, 184587732, 390739055, 190181628, 578972226, 61292868, 135769095, 226078251, 129676638, 582889860, 448939463, 551553401, 56407680, 275899176, 87664059, 206607807, 374020479, 391428765, 104256117, 298053147, 51326142, 506108358, 383350905, 398730015, 74806569, 703150560, 165216555, 554790222, 509737025, 459185405, 366667722, 672407328, 509425833, 156126222, 6511239, 581127897, 239509872, 240805271, 382725349};
		ret[128] = new int[]{149150650, 232966794, 487159055, 30540300, 458170419, 480722613, 605295159, 37220268, 292922628, 530767740, 138718678, 257373347, 44594256, 340129188, 610216692, 416259390, 657133515, 479433192, 317959019, 500636381, 12787348, 669954708, 68131467, 526429710, 423048528, 254706039, 42951006, 282387700, 303717460, 341834527, 333633477, 65589678, 376367145, 4802637, 415705878, 352406796, 72018835, 52410890, 241289776, 343900011, 624827616, 353784942, 615036450, 508213986, 138765186, 668528077, 437011960, 710035809, 735862995, 676063665, 294926121, 565603164, 111746817, 335375775, 27702486, 299270097, 434962491, 501639192, 286484229, 578826927, 685038942, 700348950, 654339672, 143420103, 104733162, 590145264, 371892402, 506813013, 596108961, 533997135, 244690731, 261388683, 663532359, 429824745, 435828036, 705296781, 474743430, 427694175, 346831137, 54648783, 310453920, 465470131, 33785059, 738490312, 39521181, 671903001, 487033953, 685013007, 478908360, 93337440, 208947235, 407997044, 591219559, 594676173, 777331779, 387099846, 265613565, 739707108, 95984586, 500815609, 703097347, 561797418, 495032139, 705610017, 137170026, 676047609, 498253248, 750976272, 124604900, 756201264, 517628076, 773939082, 652682670, 55761813, 671961765, 168908523, 243630450, 175889805, 231881111, 478771358, 621882744, 678182556, 341766705, 36099042, 154906374, 462063756, 768209115, 482164403, 642497124};
		ret[256] = new int[]{51933421, 410403148, 334527774, 772784623, 616557739, 119956662, 238586775, 63611061, 336008070, 669986155, 113924623, 290147622, 396846646, 112511021, 506832921, 18685233, 505761984, 225925875, 457760646, 658656684, 195193908, 757727334, 640171035, 277206615, 551718468, 545555745, 681825249, 115612245, 685740951, 231158277, 622498779, 374707494, 691786683, 666008595, 585462906, 146037150, 466218648, 547014447, 290039148, 190245195, 363920382, 156455586, 278403867, 327398400, 586278000, 393846327, 543672234, 561963717, 580966092, 753823584, 130668327, 353120823, 249229170, 166684527, 751104774, 309569589, 415359657, 723936555, 583194366, 494437752, 518796945, 39580443, 776201013, 42414435, 612152037, 608226003, 99972432, 558262341, 106017282, 690040638, 28879011, 512108856, 337388940, 551043738, 450089262, 360676008, 402578568, 120337812, 519804558, 324290610, 23385663, 225772848, 745389414, 233672418, 19259856, 276174402, 744786693, 375112647, 758102058, 444609585, 510907446, 172560633, 142626330, 429471231, 211245489, 577291428, 91991781, 9088632, 259025598, 596818971, 43978329, 157324491, 3103092, 462674016, 627500097, 512182818, 338032656, 603489402, 54829908, 501181650, 736006257, 286368453, 389728458, 40215357, 534475872, 64943298, 112310856, 682966116, 69045921, 439587099, 469819224, 695798271, 38780154, 396695565, 631539342, 88089750, 632887668, 56238084, 569195676, 708023862, 80301375, 1768977, 434685258, 475528473, 421403409, 775975599, 511142751, 131177718, 748118511, 296645895, 110110707, 639416484, 194905872, 211085469, 309238398, 11740617, 693768537, 652625388, 324264456, 640051812, 584206074, 361094166, 224922159, 89967528, 349541550, 591638112, 410970006, 291661029, 15985323, 613778508, 65323503, 341231688, 468358191, 521572968, 361541124, 429215724, 127304142, 228266073, 11703825, 304947090, 589390074, 128418948, 3862068, 377654949, 714128667, 456248898, 147611205, 5728464, 21997836, 396817281, 734158215, 341780733, 747135963, 339589404, 417125457, 44524053, 339589656, 681789969, 494463186, 220301928, 453528621, 517248963, 675326610, 507964023, 351220905, 574648641, 689828796, 695091546, 465336054, 429224274, 405628839, 696994956, 611296308, 357242229, 176520078, 721298331, 127701891, 117572859, 511306362, 452641455, 771641703, 283264821, 443032569, 441456687, 139696515, 627111387, 665791056, 388047555, 332840025, 259736526, 473910948, 191720088, 102052314, 654363234, 399056049, 496688157, 691964847, 654378921, 766395126, 111085128, 722623041, 492109128, 523195911, 645447222, 614109258, 706116537, 242583957, 533025234, 553627998, 488329191, 743539734, 315527373, 490300965, 715317967, 493725043, 228877044, 317300809, 663739304, 80515854, 396414708, 317067177, 102404925, 289303402, 139952108, 386543325, 412133651, 507720408};
		
		int n = ni(), k = ni();
		out.println(ret[n][n-k]);
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
	
	public static void main(String[] args) throws Exception { new D2().run(); }
	
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