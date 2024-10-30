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
mt19937_64 rng(chrono::steady_clock::now().time_since_epoch().count());
ll myRand(ll B) {
	return (ull)rng() % B;
}

#define pb push_back
#define mp make_pair
#define all(x) (x).begin(),(x).end()
#define fi first
#define se second

clock_t startTime;
double getCurrentTime() {
	return (double)(clock() - startTime) / CLOCKS_PER_SEC;
}

int gcd(int x, int y) {
	return (y == 0 ? x : gcd(y, x % y));
}

const int N = 2020;
int a[N];
int n;
int m;

int main()
{
	startTime = clock();
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	int t;
	scanf("%d", &t);
	while(t--) {
		scanf("%d", &n);
		int ans = 0;
		m = 0;
		for (int i = 0; i < n; i++) {
			int x;
			scanf("%d", &x);
			if (x & 1) a[m++] = x;
		}
		ans += (n - m) * (n - m - 1) / 2;
		ans += (n - m) * m;
		for (int i = 0; i < m; i++)
			for (int j = i + 1; j < m; j++)
				if (gcd(a[i], a[j]) > 1)
					ans++;
		printf("%d\n", ans);
	}

	return 0;
}