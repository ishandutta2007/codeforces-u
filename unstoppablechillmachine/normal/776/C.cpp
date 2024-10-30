#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

//#pragma GCC optimize("O3")
#pragma GCC optimize ("Ofast,no-stack-protector")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
#pragma GCC target("avx,tune=native")

using namespace std;
using namespace __gnu_pbds;

typedef long double ld;
typedef long long ll;
typedef unsigned long long ull;
typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> ordered_set;

#define _ inline
#define f first
#define sec second
#define pb push_back
#define ins insert
#define ers erase
#define mk make_pair
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define rsz resize
//#define int ll
#define forn(i, n) for (register int i(1); i <= n; i++)
#define fore(i, n) for (register int i(0); i < n; i++)
#define endl "\n"

#define right rightt
#define left leftt
#define prev prevv
#define next nextt

mt19937 Random(time(0));

const int INF = 1e9 + 10;
const int MOD = 1e9 + 7;
const int P = 239017;
const long long BINF = 1e18 + 10;

const int N = 1e5 + 10;
ll pref[N];
int arr[N];

_ void source() {
    int n, k;
    cin >> n >> k;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
        pref[i] = pref[i - 1] + arr[i];
    }
    map<ll, int> cnt;
    ll ans = 0, rlim = 1e14, llim = -1e14;
    //cout << lim << endl;
    for (int i = 1; i <= n; i++) {
        ll cur = 1;
        if (k == 1) {
            if (pref[i] == cur) {
                ans++;
            }
            ans += cnt[pref[i] - cur];
        }
        else if (k == -1) {
            for (int j = 0; j < 2; j++) {
                if (pref[i] == cur) {
                    ans++;
                }
                ans += cnt[pref[i] - cur];
                cur *= k;
            }
        }
        else {
            for (int j = 0; j <= 64; j++) {
                if (cur > rlim || cur < llim) break;
                if (pref[i] == cur) {
                    ans++;
                }
                ans += cnt[pref[i] - cur];
                cur *= k;
            }
        }
        cnt[pref[i]]++;
    }
    cout << ans;
}

signed main() {
#ifdef EXTRATERRESTRIAL_
    freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
#else
    //freopen(TASK".in", "r", stdin);
    //freopen(TASK".out", "w", stdout);
#endif
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    source();
    return 0;
}