//I_l_M_P
#include <bits/stdc++.h>

#ifdef FAST_ALLOCATOR_MEMORY
  int allocator_pos = 0;
  char allocator_memory[(int)FAST_ALLOCATOR_MEMORY];
  inline void * operator new ( size_t n ) {
    char *res = allocator_memory + allocator_pos;
    allocator_pos += n;
    assert(allocator_pos <= (int)FAST_ALLOCATOR_MEMORY);
    return (void *)res;
  }
  inline void operator delete ( void * ) noexcept { }
  //inline void * operator new [] ( size_t ) { assert(0); }
  //inline void operator delete [] ( void * ) { assert(0); }
#endif

template <class T = int> inline T readInt();
inline double readDouble();
inline int readUInt();
inline int readChar();
inline void readWord(char *s);
inline bool readLine(char *s);
inline bool isEof();
inline int getChar();
inline int peekChar();
inline bool seekEof();
inline void skipBlanks();

template <class T> inline void writeInt(T x, char end = 0, int len = -1);
inline void writeChar(int x);
inline void writeWord(const char *s);
inline void writeDouble(double x, int len = 0);
inline void flush();

static struct buffer_flusher_t {
  ~buffer_flusher_t() {
    flush();
  }
} buffer_flusher;

static const int buf_size = 4096;

static unsigned char buf[buf_size];
static int buf_len = 0, buf_pos = 0;

inline bool isEof() {
  if (buf_pos == buf_len) {
    buf_pos = 0, buf_len = fread(buf, 1, buf_size, stdin);
    if (buf_pos == buf_len)
      return 1;
  }
  return 0;
}

inline int getChar() {
  return isEof() ? -1 : buf[buf_pos++];
}

inline int peekChar() {
  return isEof() ? -1 : buf[buf_pos];
}

inline bool seekEof() {
  int c;
  while ((c = peekChar()) != -1 && c <= 32)
    buf_pos++;
  return c == -1;
}

inline void skipBlanks() {
  while (!isEof() && buf[buf_pos] <= 32U)
    buf_pos++;
}

inline int readChar() {
  int c = getChar();
  while (c != -1 && c <= 32)
    c = getChar();
  return c;
}

inline int readUInt() {
  int c = readChar(), x = 0;
  while ('0' <= c && c <= '9')
    x = x * 10 + c - '0', c = getChar();
  return x;
}

template <class T>
inline T readInt() {
  int s = 1, c = readChar();
  T x = 0;
  if (c == '-')
    s = -1, c = getChar();
  else if (c == '+')
    c = getChar();
  while ('0' <= c && c <= '9')
    x = x * 10 + c - '0', c = getChar();
  return s == 1 ? x : -x;
}

inline double readDouble() {
  int s = 1, c = readChar();
  double x = 0;
  if (c == '-')
    s = -1, c = getChar();
  while ('0' <= c && c <= '9')
    x = x * 10 + c - '0', c = getChar();
  if (c == '.') {
    c = getChar();
    double coef = 1;
    while ('0' <= c && c <= '9')
      x += (c - '0') * (coef *= 1e-1), c = getChar();
  }
  return s == 1 ? x : -x;
}

inline void readWord(char *s) {
  int c = readChar();
  while (c > 32)
    *s++ = c, c = getChar();
  *s = 0;
}

inline bool readLine(char *s) {
  int c = getChar();
  while (c != '\n' && c != -1)
    *s++ = c, c = getChar();
  *s = 0;
  return c != -1;
}

static int write_buf_pos = 0;
static char write_buf[buf_size];

inline void writeChar( int x ) {
  if (write_buf_pos == buf_size)
    fwrite(write_buf, 1, buf_size, stdout), write_buf_pos = 0;
  write_buf[write_buf_pos++] = x;
}

inline void flush() {
  if (write_buf_pos) {
    fwrite(write_buf, 1, write_buf_pos, stdout), write_buf_pos = 0;
    fflush(stdout);
  }
}

template <class T>
inline void writeInt(T x, char end, int output_len) {
  if (x < 0)
    writeChar('-'), x = -x;
  char s[24];
  int n = 0;
  while (x || !n)
    s[n++] = '0' + x % 10, x /= 10;
  while (n < output_len)
    s[n++] = '0';
  while (n--)
    writeChar(s[n]);
  if (end)
    writeChar(end);
}

inline void writeWord(const char *s) {
  while (*s)
    writeChar(*s++);
}

inline void writeDouble(double x, int output_len) {
  if (x < 0)
    writeChar('-'), x = -x;
  int t = (int)x;
  writeInt(t), x -= t;
  writeChar('.');
  for (int i = output_len - 1; i > 0; i--) {
    x *= 10;
    t = std::min(9, (int)x);
    writeChar('0' + t), x -= t;
  }
  x *= 10;
  t = std::min(9, (int)(x + 0.5));
  writeChar('0' + t);
}

#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4")

using namespace std;

typedef long double ld;
typedef long long ll;

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

#define right rightt
#define left leftt
#define prev prevv
#define next nextt

mt19937 Random(time(0));

const int INF = 1e9 + 10;
const int MOD = 1e9 + 7;
const int P = 239017;
const long long BINF = 1e18 + 10;

const int N = 1e5 + 10;
const int MX = 2e6 + 10;
const int SZ = sqrt(N);

struct query {
  int l, r, num;
};

bool cmp(query a, query b) {
  return a.l / SZ < b.l / SZ || (a.l / SZ == b.l / SZ && a.r < b.r);
}

vector<int> used1, used2;
int arr[N], pref[N], cnt1[MX], cnt2[MX], k;
ll ans[N], res;

void add_last(int pos) {
  int val1 = pos ? pref[pos - 1] : 0, val2 = pref[pos];
  cnt1[val1]++;
  cnt2[val2]++;
  used1.pb(val1);
  used2.pb(val2);
  res += cnt1[k ^ val2];
}

void add_first(int pos) {
  int val1 = pos ? pref[pos - 1] : 0, val2 = pref[pos];
  cnt1[val1]++;
  cnt2[val2]++;
  used1.pb(val1);
  used2.pb(val2);
  res += cnt2[k ^ val1];
}

void del_first(int pos) {
  int val1 = pos ? pref[pos - 1] : 0, val2 = pref[pos];
  res -= cnt2[k ^ val1];
  cnt1[val1]--;
  cnt2[val2]--;
}

void source() {
  int n = readInt(), m = readInt();
  k = readInt();
  for (int i = 0; i < n; i++) {
    arr[i] = readInt();
  }
  pref[0] = arr[0];
  for (int i = 1; i < n; i++) {
    pref[i] = pref[i - 1] ^ arr[i];
  }
  vector<query> q(m);
  for (int i = 0; i < m; i++) {
    q[i].l = readInt(), q[i].r = readInt();
    q[i].l--, q[i].r--;
    q[i].num = i;
  }
  sort(all(q), cmp);
  pair<int, int> cur;
  for (int i = 0; i < m; i++) {
    if (!i || q[i].l / SZ != q[i - 1].l / SZ) {
      for (auto it : used1) {
        cnt1[it] = 0;
      }
      for (auto it : used2) {
        cnt2[it] = 0;
      }
      used1.clear();
      used2.clear();
      res = 0;
      for (int j = q[i].l; j <= q[i].r; j++) {
        add_last(j);
      }
      cur = {q[i].l, q[i].r};
    }
    else {
      while (cur.sec < q[i].r) {
        cur.sec++;
        add_last(cur.sec);
      }
      while (cur.f > q[i].l) {
        cur.f--;
        add_first(cur.f);
      }
      while (cur.f < q[i].l) {
        del_first(cur.f);
        cur.f++;
      }
    }
    ans[q[i].num] = res;
  }
  for (int i = 0; i < m; i++) {
    writeInt<ll>(ans[i], '\n');
  }
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