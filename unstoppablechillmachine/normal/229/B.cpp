#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

#pragma GCC optimize("O3")
//#pragma GCC optimize ("Ofast,no-stack-protector,fast-math")
//#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
//#pragma GCC target("avx,tune=native")

using namespace std;
using namespace __gnu_pbds;

//const int MAX_MEM = 1e8; int mpos = 0; char mem[MAX_MEM]; inline void * operator new ( size_t n ) { char *res = mem + mpos; mpos += n; assert(mpos <= MAX_MEM); return (void *)res; } inline void operator delete ( void * ) { }

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

_ void source() {
    int n, m;
    cin >> n >> m;
    vector<vector<pair<int, int> > > g(n + 1);
    for (int i = 1; i <= m; i++) {
        int u, v, cost;
        cin >> u >> v >> cost;
        g[u].pb({v, cost});
        g[v].pb({u, cost});
    }
    vector<vector<int> > arr(n + 1), next(n + 1);
    for (int i = 1; i <= n; i++) {
        int cnt;
        cin >> cnt;
        if (!cnt) {
            continue;
        }
        for (int j = 0; j < cnt; j++) {
            int x;
            cin >> x;
            arr[i].pb(x);
        }
        sort(all(arr[i]));
        next[i].rsz(cnt);
        next[i][cnt - 1] = arr[i][cnt - 1] + 1;
        for (int j = cnt - 2; j >= 0; j--) {
            if (arr[i][j] + 1 == arr[i][j + 1]) {
                next[i][j] = next[i][j + 1];
            }
            else {
                next[i][j] = arr[i][j] + 1;
            }
        }
    }
    vector<ll> dist(n + 1, BINF);
    dist[1] = 0;
    set<pair<ll, int> > st;
    for (int i = 1; i <= n; i++) {
        st.ins({dist[i], i});
    }
    while (!st.empty()) {
        int v = st.begin()->sec;
        st.ers(st.begin());
        if (find(all(arr[v]), dist[v]) != arr[v].end() && v != n) {
            dist[v] = next[v][lower_bound(all(arr[v]), dist[v]) - arr[v].begin()];
        }
        for (auto u : g[v]) {
            int to = u.f, len = u.sec;
            if (dist[v] + len < dist[to]) {
                st.ers({dist[to], to});
                dist[to] = dist[v] + len;
                st.ins({dist[to], to});
            }
        }
    }
    cout << (dist[n] >= BINF ? -1 : dist[n]);
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