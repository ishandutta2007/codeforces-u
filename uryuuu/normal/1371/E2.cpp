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
//#include<unordered_map>
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
#define ch(a) (int(a-'a')+1)
#define rep(i,a,b) for(int i=a;i<=b;i++)
#define mkp(a,b) make_pair(a,b)
using namespace std;
typedef pair<int,int> pii;
const double pi=acos(-1);
const int maxn=200010;
const ll Mod=1000000007;
//const ll Mod=998244353;



int a[maxn];
int main()
{
    sync;
    int n,p;
    cin>>n>>p;
    for(int i=1;i<=n;i++)
    {
        cin>>a[i];
    }
    sort(a+1,a+n+1);
    int l=0,r=a[n]+2;
    for(int i=1;i<=n;i++)
    {
        l=max(l,a[i]-i+1);
    }
    for(int i=1;i<=n;i++)
    {
        if(i+p-1>n)
            break;
        r=min(r,a[i+p-1]-(i-1)-1);
    }
    if(l>r)
    {
        cout<<0<<endl;
        return 0;
    }
    cout<<r-l+1<<endl;
    for(int i=l;i<=r;i++)
    {
        cout<<i<<' ';
    }
    cout<<endl;
    
    return 0;
}