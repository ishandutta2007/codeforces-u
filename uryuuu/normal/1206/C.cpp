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
//const double pi=acos(-1);
const int maxn=200005;
const ll Mod=1000000007;


ll a[maxn];

int main()
{
    sync;
    int n;
    cin>>n;
    if(n%2==0)
    {
        cout<<"NO"<<endl;
        return 0;
    }
    else
    {
        cout<<"YES"<<endl;
    }
    int k=1;
    int d=1;
    int x,y;
    for(int i=1;i<=n;i++)
    {
        if(k)
        {
            x=d++;
            y=d++;
            k=0;
        }
        else
        {
            y=d++;
            x=d++;
            k=1;
        }
        a[i]=x;
        a[i+n]=y;
    }
    rep(i,1,2*n)
    {
        cout<<a[i]<<' ';
    }
    cout<<endl;
    return 0;
}