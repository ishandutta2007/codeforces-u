#include <bits/stdc++.h>
//#pragma GCC optimize ("Ofast,unroll-loops")

using namespace std;
#define ms(x,a) memset(x,a,sizeof x)
typedef long long ll;
typedef pair<int,int> pii;
const int mod=1e9+7,seed=131,MAXN=0;

int main(){
    cin.tie(0); cin.sync_with_stdio(0);
    int t; cin >> t;
    while (t--){
        int n,m,k; cin >> n >> m >> k;
        if (m<=(n/k)) cout << m << '\n';
        else cout << (n/k)-ceil(((double)m-(double)(n/k))/(k-1)) << '\n';
    }
}