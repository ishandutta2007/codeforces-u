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

const int N = 1e6 + 10;
const int M = 1e5 + 10;
const int SZ = (int)sqrt(N) * 4.75;

struct query {
  int l, r, num;
};

bool cmp(query a, query b) {
  return a.l / SZ < b.l / SZ || (a.l / SZ == b.l / SZ && a.r < b.r);
}

string s;
int open[2 * N], close[2 * N], ans[M], res, par[N], used[N], timer, clo, cro, clc, crc;

_ void add_last(int pos) {
  if (s[pos] == '(') {
    open[++cro] = pos;
  } 
  else {
    if (clo > cro) {
      close[++crc] = pos;
    }
    else {
      int ppos = open[cro--];
      par[pos] = ppos;
      par[ppos] = pos;
      used[pos] = used[ppos] = timer;
      res++;
    }
  }
}

_ void add_first(int pos) {
  if (s[pos] == ')') {
    close[--clc] = pos;
  }
  else {
    if (clc > crc) {
      open[--clo] = pos;
    }
    else {
      int ppos = close[clc++];
      par[pos] = ppos;
      par[ppos] = pos;
      used[pos] = used[ppos] = timer;
      res++;
    }
  }
}

_ int get_par(int pos) {
  if (used[pos] < timer) {
    return -1;
  }
  return par[pos];
}

_ void del_first(int pos) {
  if (s[pos] == ')') {
    clc++;
  }
  else {
    int ppos = get_par(pos);
    if (ppos == -1) {
      clo++;
    }
    else {
      par[pos] = par[ppos] = -1;
      res--;
      close[--clc] = ppos;
    }
  }
}

void source() {
  cin >> s;
  int m;
  cin >> m;
  vector<query> q(m);
  for (int i = 0; i < m; i++) {
    cin >> q[i].l >> q[i].r;
    q[i].l--;
    q[i].r--;
    q[i].num = i;
  }
  sort(all(q), cmp);
  pair<int, int> cur;
  for (int i = 0; i < m; i++) {
    if (!i || q[i].l / SZ != q[i - 1].l / SZ) {
      res = 0;
      timer++;
      clo = 1e6, cro = clo - 1;
      clc = 1e6, crc = clc - 1;
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
    writeInt(ans[i] * 2, '\n');
  }
}

signed main() {
#ifdef extraterrestrial_
  assert(freopen("input.txt", "r", stdin));
  //assert(freopen("output.txt", "w", stdout));
#else
  #define TASK ""
  //assert(freopen(TASK".in", "r", stdin));
  //assert(freopen(TASK".out", "w", stdout));
#endif
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  source();
#ifdef extraterrestrial_
  writeWord("\n\t");
  writeDouble((double)clock() / (double)CLOCKS_PER_SEC, 10);
  writeWord(" seconds\n\n");
#endif
  return 0;
}