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
#include<time.h>
#include<cstdlib>
typedef long long ll;
//#pragma comment(linker, "/STACK:1024000000,1024000000")
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define sync std::ios::sync_with_stdio(false);std::cin.tie(0);
#define inf 0x3f3f3f3f
//#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'0'))
#define rep(i,a,b) for(int i=a;i<=b;i++)
using namespace std;
typedef pair<int,int> pii;
const double pi=acos(-1);
const int maxn=300010;
//const ll Mod=1000000007;
const ll Mod=998244353;
using namespace std;


int n,m;
ll a[maxn];
 
int main()
{
    sync;
    cin>>n>>m;
    for(int i=1;i<=n;++i)
        cin>>a[i];
    for(int i=n-1;i>=1;--i)
        a[i]+=a[i+1];
    sort(a+2,a+n+1);
    ll ans=a[1];
    for(int i=1;i<=m-1;++i)
        ans+=a[n-i+1];
    cout<<ans<<endl;
    
    return 0;
}