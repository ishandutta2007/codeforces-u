#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

//#pragma GCC optimize ("Ofast,no-stack-protector")
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

int getsum() {return 0;}
int getmax() {return -INF;}
int getmin() {return INF;}
template<typename Arg, typename... Args>
int getsum(Arg first, Args... rest) {
    return first + getsum(rest...);
}
template<typename Arg, typename... Args>
int getmax(Arg first, Args... rest) {
    return max(first, getmax(rest...));
}
template<typename Arg, typename... Args>
int getmin(Arg first, Args... rest) {
    return min(first, getmin(rest...));
}

void parse(string &s) {
    for (int i = 0; i < s.size(); i++) {
        if (s[i] >= 'A' && s[i] <= 'Z') {
            s[i] = char(s[i] - 'A' + 'a');
        }
        if (s[i] == 'o') {
            s[i] = '0';
        }
        if (s[i] == 'l' || s[i] == 'i') {
            s[i] = '1';
        }
    }
}

_ void source() {
    string s;
    cin >> s;
    parse(s);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        string cur;
        cin >> cur;
        parse(cur);
        if (s == cur) {
            cout << "No";
            exit(0);
        }
    }
    cout << "Yes";
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