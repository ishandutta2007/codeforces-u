//package round431;
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
		int mod = 1000000007;
		int n = ni(), m = ni();
		
		if(m > n+1){
			out.println(0);
			return;
		}
		
		long[][] dp = {
				{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 6, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 20, 15, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 78, 60, 18, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 320, 269, 90, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1404, 1196, 452, 102, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 6354, 5550, 2232, 566, 105, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 29660, 26148, 11129, 3004, 608, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 141356, 125797, 55564, 15992, 3401, 621, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 685720, 613696, 279559, 84232, 18975, 3551, 624, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 3372972, 3033312, 1414754, 443388, 104533, 20372, 3596, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 16787362, 15150580, 7205309, 2330591, 572601, 115463, 20879, 3609, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 84374296, 76373402, 36905066, 12260498, 3118939, 651005, 120188, 21042, 3612, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 427657604, 388029184, 190039924, 64569370, 16932666, 3645556, 689414, 121903, 21088, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 183422138, 985084652, 983358608, 340626132, 91705041, 20316053, 3932165, 705215, 122449, 21101, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 218595807, 216827574, 111123886, 800216575, 495958406, 112739002, 22325180, 4062841, 710843, 122615, 21104, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 965416379, 866331606, 673968136, 532890011, 680029370, 623595485, 126193365, 23327903, 4114503, 712681, 122661, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 993455088, 862080862, 725923843, 580538538, 477345228, 440384026, 710624473, 133452405, 23761056, 4132780, 713240, 122674, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 905119307, 192776929, 427183902, 902740653, 205572806, 943087579, 988485039, 760890378, 136845231, 23927353, 4138765, 713407, 122677, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 983258926, 856652574, 419294183, 314218472, 565633404, 144225184, 323294025, 324568580, 786075109, 138248537, 23985741, 4140643, 713453, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 768913899, 874476794, 159058735, 376378817, 200939010, 909767346, 644939341, 508636359, 503698048, 797229154, 138775923, 24005038, 4141205, 713466, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 823642493, 705094318, 65810807, 545788941, 142710068, 49894552, 580875908, 538421095, 739525857, 588102612, 801695573, 138960083, 24011149, 4141372, 713469, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 499072817, 566780682, 543283862, 201724451, 58271802, 344858002, 63874460, 300408434, 761612286, 353031679, 623964235, 803344922, 139021264, 24013040, 4141418, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 576529878, 241212759, 870406317, 516608637, 786808826, 170564828, 502043776, 190085463, 974194583, 74582715, 628375911, 637942018, 803919124, 139040931, 24013603, 4141431, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 282664742, 137969620, 386494112, 872056910, 282992318, 176896609, 111724215, 824867747, 799878112, 461513992, 110630666, 741379513, 643041881, 804110861, 139047082, 24013770, 4141434, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 608570317, 801652211, 274963061, 886518820, 90837790, 626361724, 699033037, 540982188, 987824503, 727135089, 45389606, 987874590, 784549175, 644812246, 804173102, 139048976, 24013816, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 357486479, 181089468, 969172260, 289931681, 772500290, 740282998, 829587032, 842625948, 138113736, 232645342, 395674948, 621904062, 338297429, 800139849, 645406477, 804192896, 139049539, 24013829, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 254234510, 292608126, 417414987, 111595904, 430339254, 77443136, 291720282, 257053540, 606934755, 335685102, 729928927, 235560375, 363847245, 469945923, 805548789, 645601127, 804199060, 139049706, 24013832, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 595455172, 310857698, 142929150, 451089427, 328175846, 775787438, 501396673, 447925018, 130822422, 58418115, 769951318, 773701088, 10940059, 434786651, 517174485, 807371391, 645663741, 804200955, 139049752, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 349426455, 464387898, 431999991, 893280928, 77931049, 52569295, 64921457, 666455225, 189432628, 773839352, 165726163, 690284554, 801367433, 438034612, 832160214, 533545110, 807973529, 645683575, 804201518, 139049765, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 702575145, 801280561, 442448096, 451076534, 362114194, 647176951, 12722649, 733600088, 928868484, 314273055, 676922181, 390832804, 195357510, 179246035, 671930508, 973917989, 539088258, 808169252, 645689742, 804201685, 139049768, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 899116600, 365887165, 540851416, 477622706, 621209745, 41855534, 381872526, 568727969, 382270909, 755519068, 830377562, 327016443, 53743758, 200196957, 718037620, 859696293, 23095734, 540931780, 808231993, 645691637, 804201731, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 28443452, 454707299, 720701160, 419189620, 636769312, 212745640, 720255594, 47264013, 4353233, 571423322, 317657958, 770671406, 566775337, 229432738, 486965531, 377266778, 282006828, 39807033, 541536871, 808251840, 645692200, 804201744, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 708370999, 745748618, 582217318, 455319063, 603859790, 231341973, 348942460, 170184178, 689724591, 110508688, 857619861, 426992165, 588329004, 979260570, 343202258, 927818650, 898766596, 428587224, 45404704, 541732968, 808258008, 645692367, 804201747, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 220300732, 46609850, 375712662, 568586423, 941804380, 601855282, 597025225, 210361053, 58336959, 790143347, 618095230, 176358732, 30543080, 993842771, 937341158, 423396479, 514187911, 147490617, 478619188, 47256254, 541795749, 808259903, 645692413, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 491767445, 782125933, 760844150, 771643255, 631272756, 200568934, 776727389, 125809805, 89178072, 942931662, 158053887, 262399651, 379208459, 503988395, 438193938, 774920020, 818726507, 874419280, 581626996, 495470440, 47862421, 541815599, 808260466, 645692426, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 751552393, 507106191, 927020229, 340983299, 824935876, 982944264, 112403021, 736470034, 712046769, 464383985, 718862672, 472451638, 74303981, 410907563, 666832122, 904514562, 425593301, 712078493, 543662654, 730328807, 501089364, 48058645, 541821767, 808260633, 645692429, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 73145977, 945393539, 491610692, 289456339, 427592987, 156456089, 856721546, 643411286, 438709919, 830334785, 521084183, 802961067, 577757709, 979743576, 751951426, 169028881, 473063432, 677653095, 991492477, 821146133, 780715488, 502943880, 48121439, 541823662, 808260679, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 437082018, 559756622, 712764202, 175256028, 39001965, 169118126, 961041841, 330531579, 726355685, 652058564, 184476311, 959023349, 36529059, 566693578, 286624497, 631545034, 213553699, 420454245, 108890823, 707021032, 260499053, 797622167, 503550421, 48141290, 541824225, 808260692, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 911273698, 461802381, 157547978, 651886959, 991079947, 904206440, 752012728, 91490856, 970456086, 827908805, 200404310, 46817164, 95037060, 872320139, 544155656, 866678032, 265740321, 62232640, 640825471, 56958810, 445660277, 410088790, 803249159, 503746685, 48147458, 541824392, 808260695, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 99547327, 792772093, 413279596, 39759631, 598831434, 274143461, 681931397, 242545472, 634618776, 733602547, 401821126, 689076310, 138792047, 867529343, 871445219, 80442445, 172358416, 884450832, 787463230, 130635330, 184459417, 735860525, 460617750, 805104752, 503809482, 48149353, 541824438, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 482186350, 273967294, 649045886, 108980254, 570311699, 966889002, 666859727, 546817455, 689881573, 84630300, 893298736, 354835358, 214615309, 168750989, 158197112, 45797168, 168957735, 782446817, 163825770, 628696027, 196779450, 66205132, 177414259, 477545803, 805711420, 503829333, 48149916, 541824451, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 725343523, 72904069, 317229993, 651086888, 760199404, 971917773, 359172971, 877096152, 866895001, 443077851, 620349470, 706453237, 575090255, 745511976, 849766738, 965850920, 588577451, 178627725, 235164813, 372125002, 603898412, 155168897, 835596754, 327364579, 483175764, 805907697, 503835501, 48150083, 541824454, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 227169509, 545240090, 21373631, 4703558, 149454127, 906947250, 436226575, 231453773, 520829752, 657885509, 940170245, 816566317, 211196753, 118819980, 726741982, 596789649, 939963244, 486112971, 653096931, 608998341, 286609915, 821835078, 678102815, 131197840, 377949300, 485031731, 805970495, 503837396, 48150129, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0, 0},
				{0, 0, 812941513, 632016277, 587752971, 907369437, 245365463, 237152744, 770806419, 254057260, 256931484, 657088179, 685991399, 165335468, 793178596, 943503554, 488208964, 862646224, 801219250, 113427800, 680954452, 973579312, 138931569, 370928377, 622393891, 633659803, 573653877, 394885434, 485638439, 805990346, 503837959, 48150142, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0, 0},
				{0, 0, 831284912, 920930216, 121837637, 363197429, 615372675, 818973396, 465784421, 249654262, 962863505, 443733107, 862431829, 259718701, 674328903, 72003370, 27941538, 457774123, 769643354, 316096474, 198211169, 732391072, 973293491, 813065869, 598354179, 515441584, 416189909, 723747383, 400516472, 485834719, 805996514, 503838126, 48150145, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0, 0},
				{0, 0, 833292674, 298848298, 168903770, 545074331, 52799215, 758871225, 252495511, 924121755, 374266136, 669478514, 60769576, 806058403, 907911655, 394903137, 121208854, 373718471, 645725760, 64527479, 804531912, 436447112, 588689529, 502994369, 593283887, 897804374, 214286791, 714026394, 774353518, 402372566, 485897517, 805998409, 503838172, 48150146, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0, 0},
				{0, 0, 987390633, 352790884, 614135688, 73248057, 535068088, 778378333, 304699153, 787437186, 778598899, 436272891, 717326240, 327325089, 183229809, 956509573, 178816507, 780681714, 939063227, 78846995, 42468967, 482999731, 874463719, 105692341, 586003573, 658414122, 894144031, 201549678, 156845346, 791292622, 402979287, 485917368, 805998972, 503838185, 48150146, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1, 0},
				{0, 0, 637245807, 499830334, 750632612, 151627894, 974119694, 567468624, 244879604, 269170082, 396392266, 280843475, 343181990, 746080370, 730888752, 810540922, 910735707, 103336517, 104943638, 266354616, 664188130, 338775312, 887579699, 740792318, 467888722, 667892662, 673680617, 203733589, 989562192, 306994734, 796924034, 403175568, 485923536, 805999139, 503838188, 48150146, 541824455, 808260696, 645692430, 804201748, 139049769, 24013833, 4141435, 713470, 122678, 21105, 3613, 625, 106, 19, 3, 1}
		};
		out.println(dp[n][m]);
	}
	
	long C(long n, long r, int mod)
	{
		if(r < 0 || n < 0 || r > n)return 0;
		long num = 1, den = 1;
		for(int i = 0;i < r;i++){
			num = num * (n-i) % mod;
			den = den * (i+1) % mod;
		}
		return num * invl(den, mod) % mod;
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
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