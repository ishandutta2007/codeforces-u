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
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define sync std::ios::sync_with_stdio(false);std::cin.tie(0);
//#pragma comment(linker, "/STACK:1024000000,1024000000")
#define inf 0x3f3f3f3f
#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'a')+1)
using namespace std;
typedef pair<int,int> pii;
//const double pi=acos(-1);
const int maxn=100005;
const ll Mod=1000000007;




int main()
{
    sync;
    int t;
    cin>>t;
    while(t--)
    {
        ll a,b,n;
        cin>>a>>b>>n;
        if(n%3==0)
            cout<<a<<endl;
        else if(n%3==1)
            cout<<b<<endl;
        else
            cout<<(a^b)<<endl;
    }
    
    return 0;
}