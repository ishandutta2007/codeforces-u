#include <bits/stdc++.h>
typedef long long ll;
typedef long double ld;
using namespace std;
#define F first
#define S second
#define pb push_back  
#define all(x) (x).begin(), (x).end()
#define SZ(x) (int)(x).size()
#define int ll
 
signed main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0); 
  cout.tie(0);
  int T;
  cin >> T;
  while (T--) {
    int n;
    cin >> n;
    map<int, int> cnt;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      int x;
      cin >> x;
      ans += cnt[x - i];
      cnt[x - i]++;
    }
    cout << ans << '\n';
  }
}