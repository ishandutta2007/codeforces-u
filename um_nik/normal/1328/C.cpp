#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <cmath>
#include <vector>
#include <set>
#include <map>
#include <unordered_set>
#include <unordered_map>
#include <queue>
#include <ctime>
#include <cassert>
#include <complex>
#include <string>
#include <cstring>
#include <chrono>
#include <random>
#include <bitset>
using namespace std;

#ifdef LOCAL
	#define eprintf(...) fprintf(stderr, __VA_ARGS__);fflush(stderr);
#else
	#define eprintf(...) 42
#endif

using ll = long long;
using ld = long double;
using uint = unsigned int;
using ull = unsigned long long;
template<typename T>
using pair2 = pair<T, T>;
using pii = pair<int, int>;
using pli = pair<ll, int>;
using pll = pair<ll, ll>;
mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
 
#define pb push_back
#define mp make_pair
#define all(x) (x).begin(),(x).end()
#define fi first
#define se second

double startTime;
double getCurrentTime() {
	return ((double)clock() - startTime) / CLOCKS_PER_SEC;
}

const int N = 100100;
char a[N], b[N], c[N];
int n;

void solve() {
	scanf("%d", &n);
	scanf("%s", c);
	a[n] = b[n] = '\0';
	int p = 0;
	while(p < n) {
		if (c[p] == '0') {
			a[p] = b[p] = '0';
			p++;
		} else if (c[p] == '1') {
			a[p] = '0';
			b[p] = '1';
			p++;
			break;
		} else if (c[p] == '2') {
			a[p] = b[p] = '1';
			p++;
		} else throw;
	}
	while(p < n) {
		a[p] = c[p];
		b[p] = '0';
		p++;
	}
	printf("%s\n%s\n", a, b);
}

int main()
{
	startTime = (double)clock();
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	int t;
	scanf("%d", &t);
	while(t--) solve();


	return 0;
}