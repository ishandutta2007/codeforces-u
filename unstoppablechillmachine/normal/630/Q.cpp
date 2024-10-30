#include <bits/stdc++.h>
#include <random>
#include <unordered_map>

using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef vector <int> vi;
typedef vector <ll> vll;
typedef vector <ull> vull;
typedef vector <vll> vvll;
typedef long double ld;
typedef vector <ld> vld;
typedef pair <int, int> pii;
typedef pair <ll, ll> pll;
typedef vector <pii> vpii;
typedef vector <pll> vpll;
typedef vector <vpii> vvpii;
typedef vector <bool> vbool;

#define forn(i, x, y) for (int i = x; i < y; ++i)
#define rforn(i, x, y) for (int i = x; i >= y; --i)
#define Vec vector
#define add push_back
#define del pop_back
#define ins insert
#define ers erase
#define F first
#define S second
#define _ inline
#define sqr(x) ((x) * (x))
#define pw(x) (1LL << (x))
#define min(a, b) ((a < b) ? (a) : (b))
#define max(a, b) ((a > b) ? (a) : (b))
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define rev(x) reverse(x)
#define sz(x) (int)(x).size()
#define rsz(x) resize(x)
#define cpy(x, T, c) copy(all(x), ostream_iterator <T>(cout, c))
#define mem(x, val) memset(x, val, sizeof(x))
_ ll gcd(ll a, ll b) { while (b) { a %= b, swap(a, b); } return a; }
_ ll lcm(ll a, ll b) { return a * b / max(1, gcd(a, b)); }
#define TASK ""

const ll INF = (ll)1e9 + 2017;
const ll MOD = ll(1e9) + 7;
const ld EPS = 1e-10;
const ld PI = acos(-1.0);
mt19937 rnd((int)time(0));

template <class T1, class T2>
void source(T1 &cin, T2 &cout)
{
    ld a, b, c;
    cin >> a >> b >> c;

    ld s1 = sqrt(3) / 4.0 * a * a;
    ld l1 = a / sqrt(3);
    ld h1 = sqrt(sqr(a) - sqr(l1));
    ld v1 = s1 * h1 / 3.0;

    ld s2 = b * b;
    ld l2 = b * sqrt(2) / 2.0;
    ld h2 = sqrt(sqr(b) - sqr(l2));
    ld v2 = s2 * h2 / 3.0;

    ld s3 = c * c / 4.0 * sqrt(25 + 10.0 * sqrt(5));
    ld l3 = sqrt(10) * sqrt(5.0 + sqrt(5)) / 10.0 * c;
    ld h3 = sqrt(sqr(c) - sqr(l3));
    ld v3 = s3 * h3 / 3.0;

    cout << fixed << setprecision(100);
    cout << v1 + v2 + v3;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
#ifdef _F1A4X_
    ifstream cin("input.txt");
    //ofstream cout("output.txt");
#else
    //ifstream cin(TASK".in");
    //ofstream cout(TASK".out");
#endif
    source(cin, cout);
#ifdef _F1A4X_
    cin.close();
    cout << endl << endl << "\t" << fixed << setprecision(6) << (ld)clock() / CLOCKS_PER_SEC << " sec" << endl;
    system("read -p \"Press any key to continue ...\" 1");
#endif
}