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
//#include <chrono>
//#include <random>
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
#define ch(a) (int(a-'a')+1)
#define rep(i, a, b) for(int i=a;i<=b;i++)
#define mkp(a, b) make_pair(a,b)
#define re register
using namespace std;
typedef pair<int,int> pii;
typedef pair<ll,ll> pll;
typedef pair<pii,int> piii;
//const double pi=acos(-1);
const int maxn=500005;
const ll Mod=1000000007;
//const ll Mod=998244353;

string s;
ll a[maxn],mn[2],pre[2],len[2];

int main()
{
    sync;
    int t;
    int n;
    cin>>t;
    while(t--)
    {
        cin>>n;
        for(int i=1;i<=n;i++)
            cin>>a[i];
        mn[0]=mn[1]=2e9;
        pre[0]=pre[1]=0ll;
        len[0]=0ll;
        len[1]=0ll;
        int id;
        ll ans=2e18;
        for(int i=1;i<=n;i++)
        {
            id=i%2;
            len[id]++;
            mn[id]=min(mn[id],a[i]);
            pre[id]+=a[i];
            if(i==1)
                continue;
            ans=min(ans,1ll*pre[0]+pre[1]+(1ll*n-len[0])*mn[0]+(1ll*n-len[1])*mn[1]);
        }
        cout<<ans<<endl;
        
    }
    
    return 0;
}