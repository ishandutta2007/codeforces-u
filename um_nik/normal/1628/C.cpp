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
#include <array>
using namespace std;

#ifdef LOCAL
	#define eprintf(...) {fprintf(stderr, __VA_ARGS__);fflush(stderr);}
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

const int N = 1010;
int n;
int a[N][N];

int solve(int n, int X, int Y) {
	if (n <= 0) return 0;
	int ans = solve(n - 4, X + 2, Y + 2);
	for (int i = 0; i < 4 * (n - 1); i += 4) {
		if (i < n - 1) {
			int x = i, y = 0;
			ans ^= a[X + x][Y + y];
		} else if (i < 2 * (n - 1)) {
			int x = n - 1, y = i - (n - 1);
			ans ^= a[X + x][Y + y];
		} else if (i < 3 * (n - 1)) {
			int x = 3 * (n - 1) - i, y = n - 1;
			ans ^= a[X + x][Y + y];
		} else {
			int x = 0, y = 4 * (n - 1) - i;
			ans ^= a[X + x][Y + y];
		}
	}
	return ans;
}
void solve() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			scanf("%d", &a[i][j]);
	int ans = solve(n, 0, 0);
	for (int x = 0; x < n / 2; x++)
		for (int y = 0; y < n; y++)
			swap(a[x][y], a[n - 1 - x][y]);
	ans ^= solve(n, 0, 0);
	printf("%d\n", ans);
}

int main()
{
	startTime = clock();
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	int t;
	scanf("%d", &t);
	while(t--) solve();

	return 0;
}