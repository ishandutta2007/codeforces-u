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
//#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'a')+1)
#define rep(i, a, b) for(int i=a;i<=b;i++)
#define mkp(a, b) make_pair(a,b)
using namespace std;
typedef pair<int,int> pii;
//const double pi=acos(-1);
const int maxn=500010;  // 
const ll Mod=1000000007;
//const ll Mod = 998244353;

int a[maxn],pos[maxn];
int vis[maxn];
map<int,int>mp;

int main()
{
    sync;
    int t;
    int n;
    int m;
    cin>>t;
    while(t--)
    {
        cin>>n>>m;
        int x;
        for(int i=0;i<=n+5;i++)
        {
            a[i]=0;
            pos[i]=0;
            vis[i]=0;
        }
        for(int i=1;i<=m;i++)
        {
            cin>>x>>a[x];
            pos[a[x]]=x;
            if(x==a[x])
                a[x]=0;
        }
        int ans=0;
//        cout<<"FUCK"<<endl;
        for(int i=1;i<=n;i++)
        {
            if(vis[i])
                continue;
            if(a[i]==0)
            {
                continue;
            }
            int d=i,dd;
            mp.clear();
            while(a[d])
            {
//                cout<<d<<endl;
                mp[d]=1;
                vis[d]=1;
                ans++;
                dd=a[d];
                a[d]=0;
                d=dd;
            }
            if(mp[d])
                ans++;
        }
        cout<<ans<<endl;
    }
    
    return 0;
}