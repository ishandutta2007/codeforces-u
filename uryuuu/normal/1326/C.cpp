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
#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'a')+1)
#define rep(i,a,b) for(int i=a;i<=b;i++)
using namespace std;
typedef pair<int,int> pii;
const double pi=acos(-1);
const int maxn=200005;
//const ll Mod=1000000007;
const ll Mod=998244353;
using namespace std;
 
 
 
ll a[maxn];
 
int main()
{
    sync;
    int n,k;
    cin>>n>>k;
    for(int i=1;i<=n;i++)
    {
        cin>>a[i];
    }
    ll sum=1ll*(n+(n-k+1))*k/2;
    cout<<sum<<' ';
    ll s=1;
    ll pos=1;
    ll d=n-k+1;
    while(pos<=n)
    {
        if(a[pos]>=d)
        {
            break;
        }
        pos++;
    }
    int cnt=1;
    pos++;
    ll len=1;
    while(pos<=n)
    {
        if(a[pos]<d)
        {
            len++;
        }
        else
        {
            cnt++;
            s=1ll*s*len%Mod;
            len=1;
        }
        if(cnt==k)
            break;
        pos++;
    }
    cout<<s<<endl;
    
    
    return 0;
}