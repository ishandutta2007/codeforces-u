// Is the function convex? In this case it's possible to use DP slope optimization.
// {still not proven. :( }
// Total implementation time (last submission, before optimization): 20 minutes.
//
// Optimized code 2: no dynamic allocation.

#ifndef LOCAL
#define NDEBUG
#endif
#include<cassert>
#include<array>
#include<algorithm>

#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wconversion"
namespace io{using namespace std;static const int buf_size=4096;static unsigned char buf[buf_size];static int buf_len=0,buf_pos=0;inline bool isEof(){if(buf_pos==buf_len){buf_pos=0,buf_len=fread(buf,1,buf_size,stdin);if(buf_pos==buf_len)return 1;}return 0;}inline int getChar(){return isEof()?-1:buf[buf_pos++];}inline int peekChar(){return isEof()?-1:buf[buf_pos];}inline bool seekEof(){int c;while((c=peekChar())!=-1&&c<=32)buf_pos++;return c==-1;}inline void skipBlanks(){while(!isEof()&&buf[buf_pos]<=32U)buf_pos++;}inline int readChar(){int c=getChar();while(c!=-1&&c<=32)c=getChar();return c;}inline int readUInt(){int c=readChar(),x=0;while('0'<=c&&c<='9')x=x*10+c-'0',c=getChar();return x;}template<class T=int>inline T readInt(){int s=1,c=readChar();T x=0;if(c=='-')s=-1,c=getChar();else if(c=='+')c=getChar();while('0'<=c&&c<='9')x=x*10+c-'0',c=getChar();return s==1?x:-x;}inline double readDouble(){int s=1,c=readChar();double x=0;if(c=='-')s=-1,c=getChar();while('0'<=c&&c<='9')x=x*10+c-'0',c=getChar();if(c=='.'){c=getChar();double coef=1;while('0'<=c&&c<='9')x+=(c-'0')*(coef*=1e-1),c=getChar();}return s==1?x:-x;}inline void readWord(char*s){int c=readChar();while(c>32)*s++=c,c=getChar();*s=0;}inline bool readLine(char*s){int c=getChar();while(c!='\n'&&c!=-1)*s++=c,c=getChar();*s=0;return c!=-1;}static int write_buf_pos=0;static char write_buf[buf_size];inline void writeChar(int x){if(write_buf_pos==buf_size)fwrite(write_buf,1,buf_size,stdout),write_buf_pos=0;write_buf[write_buf_pos++]=x;}inline void flush(){if(write_buf_pos){fwrite(write_buf,1,write_buf_pos,stdout),write_buf_pos=0;fflush(stdout);}}template<class T>inline void writeInt(T x,char end=0,int output_len=-1){if(x<0)writeChar('-'),x=-x;char s[24];int n=0;while(x||!n)s[n++]='0'+x%10,x/=10;while(n<output_len)s[n++]='0';while(n--)writeChar(s[n]);if(end)writeChar(end);}inline void writeWord(const char*s){while(*s)writeChar(*s++);}inline void writeDouble(double x,int output_len=0){if(x<0)writeChar('-'),x=-x;int t=(int)x;writeInt(t),x-=t;writeChar('.');for(int i=output_len-1;i>0;i--){x*=10;t=std::min(9,(int)x);writeChar('0'+t),x-=t;}x*=10;t=std::min(9,(int)(x+0.5));writeChar('0'+t);}static struct buffer_flusher_t{~buffer_flusher_t(){flush();}}buffer_flusher;};
#pragma GCC diagnostic pop

struct item{
	int cost; // min
	int nchange; // maximum nchange s.t. cost is min

	bool operator<(item x)const{ // smaller item is better
		return cost!=x.cost ? cost<x.cost : nchange>x.nchange;
	}
};

std::array<item,1000001> dp;
std::array<char,1000001> s;
int slen,nchange,changelen;

int solve(){
	// minimum possible number of 1s given that operation can only change 1 to 0.
	auto const solve1=[&](int changecost){
		auto lastdp=dp[0]={0,0};
		for(unsigned i=1;i<changelen;++i){
			lastdp.cost+=s[i-1];
			dp[i]=lastdp;
		}

		for(int quot=1;quot<(slen+1)/changelen;++quot){
			for(unsigned i=0;i<changelen;++i){
				lastdp.cost+=s[quot*changelen+i-1];
				lastdp=std::min(lastdp,
						item{dp[i].cost+changecost, dp[i].nchange+1});
				dp[i]=lastdp;
			}
		}
		int quot=(slen+1)/changelen;
		for(unsigned i=0;quot*changelen+i<=slen;++i){
			lastdp.cost+=s[quot*changelen+i-1];
			lastdp=std::min(lastdp,
					item{dp[i].cost+changecost, dp[i].nchange+1});
			dp[i]=lastdp;
		}

		return lastdp;
	};

	int changecost=0,cost=0;
	for(int step=1<<30;step;step>>=1)if(changecost+step<=changelen){
		changecost+=step;
		auto [cost1,nchange1]=solve1(changecost);
		if(nchange1<nchange)
			changecost-=step;
		else{
			cost=cost1;
			if(nchange1==nchange)
				break;
		}
	}

	return cost-changecost*nchange;
}

int main(){
	slen=io::readUInt();nchange=io::readUInt();changelen=io::readUInt();
	io::readWord(s.data());

	assert('Z'<'a');
	std::for_each(begin(s),begin(s)+slen,[](char& c){
		c=c<'a';
		});

	int out=solve();
	std::for_each(begin(s),begin(s)+slen,[](char& c){
			c=not c;
			});
	out=std::min(out,solve());
	io::writeInt(out);
	io::writeChar('\n');
}