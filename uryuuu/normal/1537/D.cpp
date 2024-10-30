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
const int maxn=3005;
const ll Mod=1000000007;
//const ll Mod=998244353;

int sg[maxn],f[maxn];
int n,m;


void initsg()
{
    mm(sg);
    int j,t;
    for(int i=1;i<=n;i++)
    {
        set<int>s;
        for(j=2;j<i;j++)
        {
            if(i%j==0)
                s.insert(sg[i-j]);
        }
        t=0;
        while(s.count(t))
            t++;
        sg[i]=t;
    }
}

int main()
{
//    n=130;
//    initsg();
//    for(int i=1;i<=n;i++)
//        cout<<i<<' '<<sg[i]<<endl;
    sync;
    int t;
    cin>>t;
    while(t--)
    {
        ll x;
        cin>>x;
        if(x%2)
            cout<<"Bob"<<endl;
        else
        {
            int cnt=0;
            while(x%2==0)
            {
                x/=2;
                cnt++;
            }
            if(x==1)
            {
                if(cnt%2)
                    cout<<"Bob"<<endl;
                else
                    cout<<"Alice"<<endl;
            }
            else
            {
                cout<<"Alice"<<endl;
            }
        }
    }
    return 0;
}