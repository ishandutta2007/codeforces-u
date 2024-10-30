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


int a[maxn],cnt[maxn],fir[maxn],last[maxn];

int main()
{
    sync;
    int n,q;
    cin>>n>>q;
    for(int i=1;i<=n;i++)
    {
        cin>>a[i];
        if(fir[a[i]]==0)
            fir[a[i]]=i;
        last[a[i]]=i;
    }
    int sum=0;
    for(int i=1,j=last[a[1]];i<=n;i=j+1)
    {
        int st=i;
        j=max(j,last[a[i]]);
        while(i<j)
        {
            i++;
            j=max(j,last[a[i]]);
        }
        int s=0;
        for(int k=st;k<=j;k++)
        {
            s=max(s,++cnt[a[k]]);
        }
        sum+=j-st+1-s;
    }
    cout<<sum<<endl;
    return 0;
}