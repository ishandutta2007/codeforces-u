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

const ll INF = (ll)1e18 + 5;
const int N = 200200;
int n;
ll a[N];

int main()
{
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	scanf("%d", &n);
	int l = 0, r = n + 1;
	a[l] = 0;
	a[r] = INF;
	for (int i = 0; i < n / 2; i++) {
		ll z;
		scanf("%lld", &z);
		a[l + 1] = max(a[l], z - a[r]);
		l++;
		r--;
		a[r] = z - a[l];
	}
	for (int i = 1; i <= n; i++)
		printf("%lld ", a[i]);
	printf("\n");

	return 0;
}