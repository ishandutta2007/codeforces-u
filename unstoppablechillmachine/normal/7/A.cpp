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
#define int ll
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
const int MAXN = 8.2e6 + 10;
const int LOG = 20;
const int P = 239017;
const long long BINF = 1e18 + 10;
const long long LINF = LLONG_MAX;
const long double EPS = 1e-9;
const long double PI = acos(-1);

_ void error()
{
    cout << -1;
    exit(0);
}

#define TASK "printing"

char arr[10][10];
int ans;
int cnt[10], cnt2[10];

_ void source()
{
    forn(i, 8)
    {
        forn(j, 8)
        {
            cin >> arr[i][j];
            if (arr[i][j] == 'B')
            {
                cnt[i]++;
            }
        }
        if (cnt[i] == 8)
        {
            ans++;
        }
    }
    if (ans == 8)
    {
        cout << ans;
        exit(0);
    }
    forn(j, 8)
    {
        forn(i, 8)
        {
            if (arr[i][j] == 'B')
            {
                cnt2[j]++;
            }
        }
        if (cnt2[j] == 8)
        {
            ans++;
        }
    }
    cout << ans;
}

signed main()
{
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
    srand(time(0));
    source();
    return 0;
}