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
//#pragma comment(linker, "/STACK:36777216") //hdu 
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define sync std::ios::sync_with_stdio(false);std::cin.tie(0);
#define inf 0x3f3f3f3f
#define eqs 1e-10
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
const int maxn=50005;
//const ll Mod=1000000007;
const ll Mod=998244353;

ll a[maxn];
string s;

int main()
{
    sync;
    int t;
    cin>>t;
    int n;
    while(t--)
    {
        cin>>n;
        cin>>s;
        int mn=9999;
        for(int i=1;i<=n;i++)
        {
            if(s[i-1]=='R')
            {
                a[i]=1;
                mn=min(mn,i);
            }
            else if(s[i-1]=='B')
            {
                a[i]=2;
                mn=min(mn,i);
            }
            else
                a[i]=0;
        }
        if(mn==9999)
        {
            for(int i=1;i<=n;i++)
            {
                if(i%2)
                    cout<<'B';
                else
                    cout<<'R';
            }
            cout<<endl;
            continue;
        }
        for(int i=mn-1;i>=1;i--)
        {
            a[i]=3-a[i+1];
        }
        for(int i=mn+1;i<=n;i++)
        {
            if(a[i]==0)
                a[i]=3-a[i-1];
        }

        for(int i=1;i<=n;i++)
        {
            if(a[i]==2)
                cout<<'B';
            else
                cout<<'R';
        }
        cout<<endl;
        
        
    }
    return 0;
}