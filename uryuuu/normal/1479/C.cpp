#include<iostream>
#include<stdio.h>
#include<cstdio>
#include<vector>
#include<algorithm>
#include<cstring>
#include<cmath>
#include<queue>
#include<string>
#include<stack>
#include<set>
#include<map>
#include<bitset>
#include<unordered_map>
#include<time.h>
#include<cstdlib>
typedef long long ll;
typedef unsigned long long ull;
//#pragma comment(linker, "/STACK:1024000000,1024000000")
//#pragma comment(linker, "/STACK:36777216")  //hdu 
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define sync std::ios::sync_with_stdio(false);std::cin.tie(0);
#define inf 0x3f3f3f3f
#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'A')+1)
#define rep(i, a, b) for(int i=a;i<=b;i++)
#define mkp(a, b) make_pair(a,b)
#define re register
using namespace std;
typedef pair<int,int> pii;
typedef pair<pii,int> piii;
//const double pi=acos(-1);
const int maxn=500005;
const ll Mod=1000000007;
//const ll Mod=998244353;

struct node
{
    int u,v,w;
    node(int u1=0,int v1=0,int w1=0)
    {
        u=u1;
        v=v1;
        w=w1;
    }
}ans[maxn];

int main()
{
    sync;
    int L,R;
    cin>>L>>R;
    cout<<"YES"<<endl;
    int cnt=0;
    for(int i=2;i<=22;i++)
    {
        ans[++cnt]=node(1,i,L);
    }
    for(int i=2,x=1;i<=20;i++,x*=2)
    {
        for(int j=i+1;j<=21;j++)
        {
            ans[++cnt]=node(i,j,x);
        }
    }
    R-=L;
    for(int i=2,x=0;i<=21;i++,x++)
    {
        if(R&(1<<x))
        {
            R-=(1<<x);
            ans[++cnt]=node(i,22,R+1);
        }
    }
    cout<<22<<' '<<cnt<<endl;
    for(int i=1;i<=cnt;i++)
    {
        cout<<ans[i].u<<' '<<ans[i].v<<' '<<ans[i].w<<endl;
    }
    
    return 0;
}