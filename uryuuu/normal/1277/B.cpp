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
using namespace std;
typedef pair<int,int> pii;
const int maxn=200005;
const ll Mod=1000000007;

map<int,int>mp;

int main()
{
    sync;
    int t;
    cin>>t;
    while(t--)
    {
        mp.clear();
        int n;
        cin>>n;
        int a,sum=0;
        for(int i=0;i<n;i++)
        {
            cin>>a;
            int s=0;
            while(a%2==0)
            {
                s++;
                a/=2;
            }
            if(s)
            {
                if(mp[a]<s)
                {
                    sum+=(s-mp[a]);
                    mp[a]=s;
                }
            }
        }
        cout<<sum<<endl;
    }
    return 0;
}