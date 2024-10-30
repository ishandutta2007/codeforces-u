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

const int N = 300300;
int n, k;
ll a[N];
ll ans;

int main()
{
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	scanf("%d%d", &n, &k);
	k--;
	for (int i = 0; i < n; i++)
		scanf("%lld", &a[i]);
	for (int i = n - 1; i >= 0; i--)
		a[i] += a[i + 1];
	ans += a[0];
	for (int i = 1; i < n; i++)
		a[i - 1] = a[i];
	sort(a, a + n - 1);
	reverse(a, a + n - 1);
	for (int i = 0; i < k; i++)
		ans += a[i];
	printf("%lld\n", ans);

	return 0;
}