#include<iostream>
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
typedef long long ll;
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define tm(a,b,c) max(max(a,b),c)
#define inf 0x3f3f3f3f
const ll Mod=1e9+7;
#define maxn 50010
using namespace std;
int dd[4][2]={{1,0},{0,1},{-1,0},{0,-1}};
int main()
{
    int t;
    cin>>t;
    int n,x;
    while(t--)
    {
        scanf("%d%d",&n,&x);
        cout<<2*x<<endl;
    }
    return 0;
}