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
typedef vector<int> vi;
const double pi=acos(-1);
const int maxn=100005;
//const ll Mod=1000000007;
const ll Mod=998244353;

struct node
{
    int u,v;
}b[100];

int a[10],vis[10][10];
int zd;
int n,m;


void dfs(int pos)
{
    if(pos==n+1)
    {
        int cnt=0;
        mm(vis);
        for(int i=0;i<m;i++)
        {
//            if(v[ab[i].u][b[i].v])
            int u=a[b[i].u],v=a[b[i].v];
            if(u>v)
                swap(u,v);
            if(vis[u][v]==0)
            {
                vis[u][v]=1;
                cnt++;
            }
        }
        zd=max(zd,cnt);
        return;
    }
    for(int i=1;i<=6;i++)
    {
        a[pos]=i;
        dfs(pos+1);
    }
}




int main()
{
    sync;
    cin>>n>>m;
    for(int i=0;i<m;i++)
    {
        cin>>b[i].u>>b[i].v;
//        if(b[i].u>b[i].v)
//            swap(b[i].u,b[i].v);
    }
    
    zd=0;
    
    dfs(1);
    cout<<zd<<endl;
    return 0;
}