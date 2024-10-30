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
#define fro for
#define itn int
#define forn(i, n) for (int i(1); i <= n; i++)
#define fore(i, n) for (int i(0); i < n; i++)
#define chcpy(Vec) copy(Vec.begin(), Vec.end(),ostream_iterator<char>(cout))
#define intcpy(Vec) copy(Vec.begin(), Vec.end(),ostream_iterator<int>(cout, " "))

const int INF = 1e9 + 10;
const ll MOD = 1e9 + 7;
const int MAXN = 1e6 + 10;
const int SMAXN = 1e3 + 10;
const int P = 259;
const ll BINF = 1e18 + 10;
const ld EPS = 1e-10;
const ld PI = acos(-1);

ll sum, ans, n, h, k, cnt, st[100], arr[100], a;
vll Vec;
bool used[MAXN];

ll f(ll res)
{
    int cnt = 0;
    while(res)
    {
        if (res % 2 == 0) cnt++;
        res /= 2;
    }
    return cnt;
}
int main()
{
    //freopen("input.txt","r",stdin);
    //freopen("output.txt","w",stdout);
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    srand(time(0));
    forn(i, 64)
    {
        a = i;
        while(a * 2 < 64) a *= 2;
        arr[i] = f(a);
    }
    arr[0] = 6;
    ll a = 1;
    fore(i, 11)
    {
        st[i] = a;
        a = (a * 3) % MOD;
    }

    ll res = 0;
    ll ans = 1;
    string s;
    cin >> s;
    fore(i, s.size())
    {
        if (s[i] >= '0' && s[i] <= '9')
        {
            res = s[i] - '0';
        }
        else if (s[i] >= 'A' && s[i] <= 'Z')
        {
            res = s[i] - 'A' + 10;
        }
        else if (s[i] >= 'a' && s[i] <= 'z')
        {
            res = s[i] - 'a' + 36;
        }
        else if (s[i] == '-') res = 62;
        else res = 63;
        ans = (ans * st[arr[res]]) % MOD;
    }
    cout << ans;
    return 0;
}