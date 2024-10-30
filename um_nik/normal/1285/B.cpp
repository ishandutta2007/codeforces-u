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
#include <queue>
#include <bitset>
using namespace std;

#ifdef LOCAL
	#define eprintf(...) fprintf(stderr, __VA_ARGS__)
#else
	#define eprintf(...) 42
#endif

typedef long long ll;
typedef pair<int, int> pii;
typedef pair<ll, int> pli;
typedef pair<ll, ll> pll;
typedef long double ld;
#define mp make_pair
mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());

const int N = 100100;
int n;
ll a[N];

bool solve() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		scanf("%lld", &a[i]);
	ll all = 0;
	for (int i = 0; i < n; i++)
		all += a[i];
	ll sum = 0;
	for (int i = 0; i < n - 1; i++) {
		sum += a[i];
		if (sum >= all) return false;
	}
	sum = 0;
	for (int i = n - 1; i > 0; i--) {
		sum += a[i];
		if (sum >= all) return false;
	}
	return true;
}

int main()
{
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	int t;
	scanf("%d", &t);
	while(t--) {
		if (solve())
			printf("YES\n");
		else
			printf("NO\n");
	}

	return 0;
}