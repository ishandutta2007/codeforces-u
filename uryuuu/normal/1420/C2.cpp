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
#define flush fflush(stdout)
using namespace std;
typedef pair<int,ll> pii;
const double pi=acos(-1);
const int maxn=300010;
//const ll Mod=1000000007;
const ll Mod = 998244353;
//#define int long long

ll a[maxn],vis[maxn];

ll ans;
int n;

void add(int pos,ll k)
{
    if((pos==n||a[pos]>a[pos+1])&&(pos==1||a[pos]>a[pos-1]))
    {
        ans+=1ll*k*a[pos];
    }
    if((pos<n&&a[pos]<a[pos+1])&&(pos>1&&a[pos]<a[pos-1]))
    {
        ans-=1ll*k*a[pos];
    }
}

int main()
{
    sync;
    int t;
    cin>>t;
    while(t--)
    {
        int q;
        cin>>n>>q;
        for(int i=1;i<=n;i++)
            cin>>a[i];
        a[0]=0;
        a[n+1]=0;
        ll minus=0,plus=0;
        for(int i=1;i<=n;i++)
        {
            minus=max(plus-a[i],minus);
            plus=max(minus+a[i],plus);
        }
        ans=plus;
        cout<<ans<<endl;
        int l,r;
        int pos;
        int L,R;
        while(q--)
        {
            cin>>l>>r;
            if(l==r)
            {
                cout<<ans<<endl;
                continue;
            }
            for(int i=max(l-1,1);i<=min(n,l+1);i++)
                vis[i]=0;
            for(int i=max(r-1,1);i<=min(n,r+1);i++)
                vis[i]=0;
            
            for(int i=max(l-1,1);i<=min(n,l+1);i++)
            {
                if(vis[i]==0)
                    add(i,-1);
                vis[i]=1;
            }
            for(int i=max(r-1,1);i<=min(n,r+1);i++)
            {
                if(vis[i]==0)
                    add(i,-1);
                vis[i]=1;
            }
            swap(a[l],a[r]);
            for(int i=max(l-1,1);i<=min(n,l+1);i++)
                vis[i]=0;
            for(int i=max(r-1,1);i<=min(n,r+1);i++)
                vis[i]=0;
            for(int i=max(l-1,1);i<=min(n,l+1);i++)
            {
                if(vis[i]==0)
                    add(i,1);
                vis[i]=1;
            }
            for(int i=max(r-1,1);i<=min(n,r+1);i++)
            {
                if(vis[i]==0)
                    add(i,1);
                vis[i]=1;
            }
            cout<<ans<<endl;
        }
    }
    
    
    return 0;
}