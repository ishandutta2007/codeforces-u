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
using namespace std;
const int maxn=105;
const ll Mod=1e9+7;

int main()
{
    sync;
    int n,a,b,c,t;
    cin>>t;
    while(t--)
    {
        cin>>a>>b>>c>>n;
        int s=a+b+c+n;
        if(s%3==0)
        {
            int ss=s/3;
            if(ss>=a&&ss>=b&&ss>=c)
                cout<<"YES"<<endl;
            else
                cout<<"NO"<<endl;
        }
        else
            cout<<"NO"<<endl;
    }
    return 0;
}