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
//#define int ll
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
const int MOD = 998244353;
const int MAXN = 1e6 + 10;
const int LOG = 20;
const int P = 239017;
const long long BINF = 1e18 + 10;
const long long LINF = LLONG_MAX;
const long double EPS = 1e-2;
const long double PI = acos(-1);

_ void error()
{
    cout << -1;
    exit(0);
}

#define TASK "printing"

int a, b, c, A[5010][5010], C[5010][5010];

int get(int a, int b)
{
    if (a > b)
    {
        swap(a, b);
    }
    ll res = 1;
    forn(i, a)
    {
        res = (res + ll(A[a][i]) * ll(C[b][i])) % MOD;
    }
    return res % MOD;
}

void source()
{
    forn(i, 5000)
    {
        A[i][1] = i;
        for (int j = 2; j <= i; j++)
        {
            A[i][j] = (ll(A[i][j - 1]) * ll(i - j + 1)) % MOD;
        }
    }
    forn(i, 5000)
    {
        C[i][1] = i;
        C[i][2] = (i * (i - 1) / 2) % MOD;
    }
    for (int i = 3; i <= 5000; i++)
    {
        for (int j = 3; j <= i; j++)
        {
            C[i][j] = (ll(C[i - 1][j]) + ll(C[i - 1][j - 1])) % MOD;
        }
    }
    cin >> a >> b >> c;
    cout << (((ll(get(a, b)) * ll(get(b, c))) % MOD) * ll(get(a, c))) % MOD;
}

signed main()
{
#ifdef EXTRATERRESTRIAL_
    //freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
#else
    //freopen(TASK".in", "r", stdin);
    //freopen(TASK".out", "w", stdout);
#endif
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    srand(time(0));
    source();
    return 0;
}