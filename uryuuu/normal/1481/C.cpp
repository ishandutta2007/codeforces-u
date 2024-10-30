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

int a[maxn],b[maxn],c[maxn],ans[maxn];
vector<int>g[maxn];
int vis[maxn];

int main()
{
    sync;
    int t;
    int n,m;
    cin>>t;
    while(t--)
    {
        cin>>n>>m;
        for(int i=1;i<=n;i++)
        {
            g[i].clear();
            vis[i]=0;
        }
        for(int i=1;i<=n;i++)
            cin>>a[i];
        for(int i=1;i<=n;i++)
        {
            cin>>b[i];
            if(b[i]!=a[i])
            {
                g[b[i]].push_back(i);
            }
            vis[b[i]]=i;
        }
        
        int f=0,sz;
        for(int i=1;i<=m;i++)
        {
            cin>>c[i];
        }
        for(int i=m;i>=1;i--)
        {
            if(g[c[i]].size())
            {
                sz=g[c[i]].size()-1;
                ans[i]=g[c[i]][sz];
                g[c[i]].pop_back();
                continue;
            }
            if(vis[c[i]])
            {
                ans[i]=vis[c[i]];
                continue;
            }
            if(i<m)
            {
                ans[i]=ans[m];
            }
            else
            {
                f=1;
                break;
            }
        }
        for(int i=1;i<=n;i++)
        {
            if(g[i].size()>0)
            {
                f=1;
                break;
            }
        }
        if(f)
        {
            cout<<"NO"<<endl;
            continue;
        }
        cout<<"YES"<<endl;
        for(int i=1;i<=m;i++)
        {
            cout<<ans[i]<<' ';
        }
        cout<<endl;
    }
    return 0;
}