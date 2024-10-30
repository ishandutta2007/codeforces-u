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
const long long BINF = 1e18 + 10;
const long long LINF = 1e18 + 10;
const long double EPS = 1e-10;
const long double PI = acos(-1);

_ void error()
{
    cout << -1;
    exit(0);
}

#define TASK ""
string s;
vi a(3), b(3);
_ void source()
{
    cin >> s;
    a[0] = s[0] - '0'; a[1] = s[1] - '0'; a[2] = s[2] - '0';
    b[0] = s[3] - '0', b[1] = s[4] - '0', b[2] = s[5] - '0';
    if (a[0] + a[1] + a[2] == b[0] + b[1] + b[2])
    {
        cout << 0;
        exit(0);
    }
    sort(all(a));
    sort(all(b));
    if (a[0] + a[1] + a[2] < b[0] + b[1] + b[2])
    {
        swap(a, b);
    }
    if (a[0] + a[1] + a[2] - b[0] - b[1] - b[2] <= a[2] || a[0] + a[1] + a[2] - b[0] - b[1] - b[2] <= 9 - b[0])
    {
        cout << 1;
        exit(0);
    }
    else
    {
        if (a[0] + a[1] + a[2] - b[0] - b[1] - b[2] <= a[1] + a[2] || a[0] + a[1] + a[2] - b[0] - b[1] - b[2] <= 9 - b[0] + 9 - b[1] || a[0] + a[1] + a[2] - b[0] - b[1] - b[2] <= a[2] + 9 - b[0])
        {
            cout << 2;
            exit(0);
        }
    }
    cout << 3;
}

int main()
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