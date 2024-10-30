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

const int N = 30303;
bool p[N];
int pr[N];
int m;

void solve() {
	int d;
	scanf("%d", &d);
	ll p = *lower_bound(pr, pr + m, d + 1);
	ll q = *lower_bound(pr, pr + m, p + d);
	printf("%lld\n", min(p * p * p, p * q));
}

int main()
{
	startTime = clock();
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	for (int i = 2; i < N; i++)
		p[i] = 1;
	for (int x = 2; x < N; x++) {
		if (!p[x]) continue;
		pr[m++] = x;
		for (int y = 2 * x; y < N; y += x)
			p[y] = 0;
	}

	int t;
	scanf("%d", &t);
	while(t--) solve();

	return 0;
}