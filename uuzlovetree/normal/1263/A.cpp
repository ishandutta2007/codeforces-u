#include<bits/stdc++.h>
#define ll long long
#define ull unsigned long long
#define pii pair<int,int>
#define pll pair<long long,long long>
#define mpr(a,b) make_pair(a,b)
using namespace std;
ll gcd(ll a,ll b){if(!b)return a;return gcd(b,a%b);}
ll fastpow(ll a,ll p,ll mod)
{
	ll ans=1;
	while(p)
	{
		if(p&1)ans=ans*a%mod;
		a=a*a%mod;p>>=1;
	}
	return ans;
}
ll inv(ll x,ll mod){return fastpow(x,mod-2,mod);}
int T;
int a[4];
int main()
{
	cin>>T; 
	while(T--)
	{
		cin>>a[1]>>a[2]>>a[3];
		sort(a+1,a+4);
		int l=0,r=a[1],ans=0;
		while(l<=r)
		{
			int mid=(l+r)>>1;
			if(a[2]-mid>=a[3]-(a[1]-mid))ans=mid,l=mid+1;
			else r=mid-1;
		}
		cout<<a[1]+min(a[2]-ans,a[3]-(a[1]-ans))<<endl;
	}
}