//author: extraterrestrial_
#include <bits/stdc++.h>
#pragma GCC optimize("O3,Ofast,unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,avx,tune=native")
using namespace std;
//#define int ll
typedef long double ld;
typedef long long ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
#define _ inline
#define pb push_back
#define F first
#define S second
#define MP make_pair
#define SZ(x) ((int)x.size())
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define pw(x) (1ll << (x))
#ifdef extraterrestrial_
  #define debug(x) cerr << #x << ": " << x << '\n';
#else
  #define debug(x)
#endif
const int INF = 1e9 + 10;
const ll BINF = 1e18 + 10;

int n, a, b, best = INF;
vector<int> ans;

void go(int cur, int cnt, vector<int> res, vector<int> arr) {
  if (cur == n - 1) {
    if (arr[n - 1] < 0 && arr[n - 2] < 0 && cnt < best) {
      best = cnt;
      ans = res;
    }
    return;
  }
  while (arr[cur - 1] >= 0) {
    arr[cur] -= a;
    arr[cur - 1] -= b;
    arr[cur + 1] -= b;
    cnt++;
    res.pb(cur + 1);
  }
  go(cur + 1, cnt, res, arr);
  while (arr[cur] >= 0 || (cur + 1 == n - 1 && arr[cur + 1] >= 0)) {
    arr[cur] -= a;
    arr[cur + 1] -= b;
    res.pb(cur + 1);
    cnt++;
    go(cur + 1, cnt, res, arr);
  }
}

void source() {
  cin >> n >> a >> b;
  vector<int> arr(n);
  for (auto &it : arr) {
    cin >> it;
  }
  go(1, 0, {}, arr);
  cout << best << '\n';
  for (auto it : ans) {
    cout << it << ' ';
  }
}
 
signed main() {
#ifdef extraterrestrial_
  assert(freopen("input.txt", "r", stdin));
  //assert(freopen("output.txt", "w", stdout));
#else
#define TASK ""
  //assert(freopen(TASK".in", "r", stdin));
  //assert(freopen(TASK".out", "w", stdout));
#endif
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  source();
#ifdef extraterrestrial_
  cerr << "\n\nTIME :: " << clock() * 1.0 / CLOCKS_PER_SEC;
#endif
  return 0;
}