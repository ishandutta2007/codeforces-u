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
//#include<unordered_map>
#include<time.h>
#include<cstdlib>
typedef long long ll;
//#pragma comment(linker, "/STACK:1024000000,1024000000")
#define mm(a) memset(a,0,sizeof(a))
#define lr rt<<1
#define rr rt<<1|1
#define sync std::ios::sync_with_stdio(false);std::cin.tie(0);
#define inf 0x3f3f3f3f
//#define eqs 1e-8
#define lb(x) (x&(-x))
#define ch(a) (int(a-'a')+1)
#define rep(i,a,b) for(int i=a;i<=b;i++)
#define mkp(a,b) make_pair(a,b)
using namespace std;
typedef pair<int,int> pii;
const double pi=acos(-1);
const int maxn=200010;
//const ll Mod=1000000007;
const ll Mod=998244353;


struct node
{
    int val,id;
}b[maxn],c[maxn];

int a[maxn];

int main()
{
    sync;
    int t;
    ll n;
    cin>>t;
    while(t--)
    {
        cin>>n;
        if(n==1)
        {
            cout<<"FastestFinger"<<endl;
        }
        else if(n==2)
        {
            cout<<"Ashishgup"<<endl;
        }
        else if(n%2)
            cout<<"Ashishgup"<<endl;
        else
        {
            int f=0;
            for(int i=2;i<=32;i++)
            {
                if((1ll<<i)==1ll*n)
                {
                    f=1;
                    break;
                }
            }
            if(f==1)
            {
                cout<<"FastestFinger"<<endl;
                continue;
            }
            n/=2;
            if(n%2==0)
                cout<<"Ashishgup"<<endl;
            else
            {
                while(n%2==0)
                    n/=2;
                f=0;
//                cout<<n<<endl;
                for(int i=2;i*i<=n;i++)
                {
                    if(n%i==0)
                    {
                        f=1;
                        break;
                    }
                }
//                cout<<"HH"<<endl;
                if(f==0)
                    cout<<"FastestFinger"<<endl;
                else
                    cout<<"Ashishgup"<<endl;
            }
        }
    }
    return 0;
}