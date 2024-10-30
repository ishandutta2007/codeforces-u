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

ll n;
ll DX[] = {-1, -2, -1, 1, 2, 1};
ll DY[] = {-2, 0, 2, 2, 0, -2};

int main()
{
	startTime = clock();
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);

	scanf("%lld", &n);
	if (n == 0) {
		printf("0 0\n");
		return 0;
	}
	ll l = 0;
	ll side = 1;
	while(true) {
		ll r = l + 6 * side;
		if (n <= r) {
			ll x = 2 * side, y = 0;
			for (int i = 0; i < 6; i++) {
				ll z = min(r - n, side);
				r -= z;
				x += DX[i] * z;
				y += DY[i] * z;
			}
			printf("%lld %lld\n", x, y);
			return 0;
		}
		l = r;
		side++;
	}

	return 0;
}