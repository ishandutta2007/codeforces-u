#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

#pragma GCC optimize ("Ofast,no-stack-protector")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
#pragma GCC target("avx,tune=native")

using namespace std;
using namespace __gnu_pbds;

typedef long double ld;
typedef long long ll;
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

#define right rightt
#define left leftt
#define prev prevv
#define next nextt

mt19937 Random(time(0));

const int INF = 1e9 + 10;
const int MOD = 1e9 + 7;
const int P = 239017;
const long long BINF = 1e18 + 10;

vector<int> solve(vector<int> arr, int step) {
    if (arr.empty()) {
        return {};
    }
    sort(all(arr));
    arr.rsz(unique(all(arr)) - arr.begin());
    if (!arr[0]) {
        arr.ers(arr.begin());
    }
    if (arr.empty()) return {};
    if (arr.size() == 1 && arr[0] == 1) {
        return {1 << step};
    }
    if (arr.size() == 1 && arr[0] == -1) {
        return {-(1 << step)};
    }
    bool t = true;
    for (auto it : arr) {
        if (it & 1) {
            t = false;
            break;
        }
    }
    if (t) {
        for (auto &it : arr) {
            it >>= 1;
        }
        return solve(arr, step + 1);
    }
    vector<int> arr1, arr2, res1, res2;
    for (auto it : arr) {
        if (it & 1) {
            arr1.pb((it + 1) >> 1);
            if ((it - 1) >> 1) arr2.pb((it - 1) >> 1);
        }
        else {
            if (it >> 1) {
                arr1.pb(it >> 1);
                arr2.pb(it >> 1);
            }
        }
    }
    res1 = solve(arr1, step + 1);
    res2 = solve(arr2, step + 1);
    res1.pb(-(1 << step));
    res2.pb(1 << step);
    if (res1.size() < res2.size()) {
        return res1;
    }
    return res2;
}

_ void source() {
    int n;
    cin >> n;
    vector<int> arr;
    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;
        if (a != 0) {
            arr.pb(a);
        }
    }
    vector<int> ans = solve(arr, 0);
    cout << ans.size() << endl;
    sort(all(ans));
    for (auto it : ans) {
        cout << it << ' ';
    }
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