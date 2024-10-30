#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

//#pragma GCC optimize("O3")
//#pragma GCC optimize ("Ofast,no-stack-protector,fast-math")
//#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
//#pragma GCC target("avx,tune=native")

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

const int N = 1e5 + 10;
int arr[N], t[4 * N];
void build(int v, int l, int r) {
    if (l == r) {
        t[v] = arr[l];
        return;
    }
    int mid = (l + r) / 2;
    build(2 * v, l, mid);
    build(2 * v + 1, mid + 1, r);
    t[v] = min(t[2 * v], t[2 * v + 1]);
}

int get(int v, int l, int r, int a, int b, int val) {
    if (l > b || r < a) {
        return INF;
    }
    if (l == r) {
        return (t[v] < val ? l : INF);
    }
    int mid = (l + r) / 2;
    if (t[2 * v + 1] < val) {
        return get(2 * v + 1, mid + 1, r, a, b, val);
    }
    else {
        return get(2 * v, l, mid, a, b, val);
    }
}

_ void source() {
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    build(1, 1, n);
    for (int i = 1; i <= n; i++) {
        int pos = get(1, 1, n, i + 1, n, arr[i]);
        cout << (pos == INF ? -1 : pos - i - 1) << ' ';
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