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
#include <chrono>
#include <random>
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
#define umap(a) (a).reserve(1024);(a).max_load_factor(0.25);
using namespace std;
typedef pair<int,int> pii;
typedef pair<ll,ll> pll;
typedef pair<pii,int> piii;
//const double pi=acos(-1);
const int maxn=500005;
//const ll Mod=1000000007;
const ll Mod=998244353;

int a[maxn];
int dp[300005];

int main()
{
    sync;
    int t;
    int n;
    
    cin>>n;
    int sum=0;
    for(int i=1;i<=n;i++)
    {
        cin>>a[i];
        sum+=a[i];
    }
    int j;
    dp[0]=1;
    for(int i=1;i<=n;i++)
    {
        for(j=sum;j>=0;j--)
        {
            if(dp[j])
                dp[j+a[i]]=1;
        }
    }
    if(sum%2)
    {
        cout<<0<<endl;
        return 0;
    }
    if(dp[sum/2]==0)
    {
        cout<<0<<endl;
        return 0;
    }
    
    int id=0;
    int f=0;
    
    while(1)
    {
        for(int i=1;i<=n;i++)
        {
            if(a[i]%2)
            {
                id=i;
                f=1;
            }
        }
        if(f)
        {
            cout<<1<<endl;
            cout<<id<<endl;
            return 0;
        }
        for(int i=1;i<=n;i++)
            a[i]/=2;
    }
    
    return 0;
}