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
const int MAXN = 1e6 + 10;
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

int n, sum;
string s, ans;
vector<string> arr;
bool used[30], need[30], uu[30];
vector<int> prev[30], next[30];
_ void source()
{
    cin >> n;
    forn(i, n)
    {
        cin >> s;
        forn(j, 26)
        {
            used[j] = false;
        }
        for (int j = 0; j < s.size(); j++)
        {
            if (used[s[j] - 'a' + 1])
            {
                cout << "NO";
                exit(0);
            }
            used[s[j] - 'a' + 1] = true;
            if (j)
            {
                if (prev[s[j] - 'a' + 1].empty())
                {
                    prev[s[j] - 'a' + 1].pb(s[j - 1] - 'a' + 1);
                }
                else if (prev[s[j] - 'a' + 1][0] != s[j - 1] - 'a' + 1)
                {
                    cout << "NO";
                    exit(0);
                }
                if (next[s[j - 1] - 'a' + 1].empty())
                {
                    next[s[j - 1] - 'a' + 1].pb(s[j] - 'a' + 1);
                }
                else if (next[s[j - 1] - 'a' + 1][0] != s[j] - 'a' + 1)
                {
                    cout << "NO";
                    exit(0);
                }
            }
            need[s[j] - 'a' + 1] = true;
        }
    }
    forn(i, 26)
    {
        if (need[i])
        {
            sum++;
        }
    }
    forn(i, 26)
    {
        if (need[i] && prev[i].empty() && !uu[i])
        {
            int cur = i;
            uu[cur] = true;
            string now;
            now += char('a' + i - 1);
            while (!next[cur].empty())
            {
                cur = next[cur][0];
                if (uu[cur])
                {
                    cout << "NO";
                    exit(0);
                }
                uu[cur] = true;
                now += char('a' + cur - 1);
            }
            arr.pb(now);
        }
    }
    sort(all(arr));
    for (auto it : arr)
    {
        ans += it;
    }
    if (ans.size() == sum)
    {
        cout << ans;
    }
    else
    {
        cout << "NO";
    }
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