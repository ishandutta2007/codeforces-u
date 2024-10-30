#include <bits/stdc++.h>

using namespace std;

typedef long double ld;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef vector<ll> vll;
typedef vector<ull> vull;
typedef vector<vll> vvll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef vector<pii> vpii;
typedef vector<pll> vpll;
typedef vector<ld> vld;
typedef vector<bool> vb;

#define _ inline
#define f first
#define sec second
#define pb push_back
#define ins insert
#define ers erase
#define mk make_pair
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define sz size
#define rsz resize
#define pw(x) (1 << (x))
#define sqr(x) ((x) * (x))
#define fro for
#define itn int
#define forn(i, n) for (register int i(1); i <= n; i++)
#define fore(i, n) for (register int i(0); i < n; i++)
#define chcpy(Vec) copy(Vec.begin(), Vec.end(), ostream_iterator<char>(cout))
#define intcpy(Vec) copy(Vec.begin(), Vec.end(), ostream_iterator<int>(cout, " "))
#define endl "\n"
#define right rightt
#define left leftt
#define prev prevv
#define next nextt

const int INF = 1e9 + 10;
const int MOD = 1e9 + 7;
const int MAXN = 1e6 + 10;
const int LOG = 20;
const long long BINF = 1e18 + 10;
const long long LINF = LLONG_MAX;
const long double EPS = 1e-9;
const long double PI = acos(-1);

_ void error()
{
    cout << -1;
    exit(0);
}

#define TASK ""

int tin[MAXN], tout[MAXN], timer;
bool used[MAXN];
vvi g, ans;
map<pii, int> f;

bool ancestor(int a, int b)
{
    if (!tin[b])
    {
        return false;
    }
    return (tin[a] <= tin[b] && tout[a] >= tout[b]);
}

void dfs(int v, int p)
{
    used[v] = true;
    timer++;
    tin[v] = timer;
    vi arr;
    for (auto u : g[v])
    {
        if (!used[u])
        {
            dfs(u, v);
        }
    }
    timer++;
    tout[v] = timer;
    for (auto u : g[v])
    {
        if (ancestor(v, u) && !f[mk(min(v, u), max(v, u))])
        {
            arr.pb(u);
            f[mk(min(v, u), max(v, u))] = 1;
        }
    }
    if (int(arr.size()) & 1)
    {
        if (p != -1)
        {
            f[mk(min(p, v), max(p, v))] = true;
            arr.pb(p);
        }
    }
    for (int i = 0; i + 1 < arr.size(); i += 2)
    {
        ans.pb(vi{arr[i], v, arr[i + 1]});
    }
}

int n, m, a, b;

_ void source()
{
    cin >> n >> m;
    g.rsz(n + 1);
    forn(i, m)
    {
        cin >> a >> b;
        g[a].pb(b);
        g[b].pb(a);
    }
    forn(i, n)
    {
        if (!used[i])
        {
            dfs(i, -1);
        }
    }
    cout << ans.size() << endl;
    fore(i, ans.size())
    {
        intcpy(ans[i]);
        cout << endl;
    }
}

int main()
{
#ifdef EXTRATERRESTRIAL_
    freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
#else
    //freopen(TASK".in", "r", stdin
    //freopen(TASK".out", "w", stdout);
#endif
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    srand(time(0));
    source();
    return 0;
}