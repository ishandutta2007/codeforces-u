// yes, *of course* it can be proven without magic.
// (although it may still be helpful to figure out how to derive the solution with "magic" only and automatically)

#if not LOCAL
#define NDEBUG 1
#endif
#include<bits/stdc++.h>

// { ==== Begin library read ====

template<class T> T read(){
	T result; std::cin>>result; return result;
}

// } ==== End library read ====

// { ==== Begin library range ====

template<class T>
struct int_iter{
	//using iterator_category = std::random_access_iterator_tag;
	using iterator_category = std::random_access_iterator_tag;
	using value_type = T;
	//using difference_type = std::ptrdiff_t;
	using difference_type = T;
	using pointer = T*;
	using reference = T&;

	T x;
	constexpr int_iter(T x):x{x}{}
	T operator*()const{return x;}
	int_iter& operator++(){ ++x; return *this; }
	int_iter& operator--(){ --x; return *this; }

#define operation(op) \
	int_iter& operator op##=(difference_type y){ x op##= y; return *this; } \
	int_iter friend operator op(int_iter x, difference_type y){ return x op##= y; }
	operation(+) operation(-)
#undef operation
	int_iter friend operator+(difference_type y, int_iter x){ return x += y; }

#define operation(op) \
	auto operator op(int_iter const& other) const{return x op other.x;}
	operation(-) operation(<) operation(>) operation(<=) operation(>=) operation(==) operation(!=)
#undef operation

	T operator[](difference_type y)const{ return x+y; }
};

struct unreachable_iter{
	template<class T, class U,
		class=std::enable_if<
			std::is_same<T, unreachable_iter>::value xor std::is_same<U, unreachable_iter>::value, int>
	> friend bool operator!=(T const& first, U const& sec){ return true; }
};

template<class T> auto range(T first, T last);


template<class A, class B=A>
struct Range{
	A first; B last;
	constexpr Range(A first, B last):first{std::move(first)},last{std::move(last)}{}

	/*
	template<class Container> Range(Container&& container):
		first(std::begin(std::forward<Container>(container))), last(std::end(std::forward<Container>(container))){}
		*/

	// make sure container is not a temporary (TODO?)
	template<class Container> Range(Container& container):
		first(std::begin(container)), last(std::end(container)){}

	constexpr A const& begin() const{return first;}
	constexpr B const& end() const{return last;}
	constexpr auto size() const{ return last-first; }

	Range take(std::size_t len)const{
		return Range{first, std::next(first, len)};
	}
	Range drop(std::size_t len)const{
		return Range{std::next(first, len), last};
	}

	Range const& sort    () const{ std::sort(first, last); return *this; }
	template<class F> Range const& sort_by (F const& compare) const{ std::sort(first, last, compare); return *this; }
	template<class F> Range const& sort_key(F const& key) const{
		std::sort(first, last, [&](auto const& first, auto const& sec){
			return key(first)<key(sec);
		});
		return *this;
	}

	template<class T> Range const& shuffle(T&& engine) const{ std::shuffle(first, last, engine); return *this; }
	Range& unique() { last=std::unique(first, last); return *this; }
	//Range const& reverse() const{ std::reverse(first, last); return *this; }
	auto reverse() const{
		return range(
				std::make_reverse_iterator(last),
				std::make_reverse_iterator(first)
				);
		/*
		return Range<
				typename std::decay<decltype(std::make_reverse_iterator(last))>::type,
				typename std::decay<decltype(std::make_reverse_iterator(first))>::type
		>{
				std::make_reverse_iterator(last),
				std::make_reverse_iterator(first)
		};
		*/
	}

	auto const& assign(Range other) const{
		assert(size()==other.size());
		std::copy(other.first, other.last, first);
		return *this;
	}
	auto const& copy_to(A const& iterator) const{
		std::copy(first, last, iterator);
		return *this;
	}
	auto const& copy_to(Range const& range) const{
		std::copy(first, last, range.first);
		return *this;
	}

	template<class F> auto any  (F const& function) const{ return std::any_of(first, last, function); }
	template<class F> auto all  (F const& function) const{ return std::all_of(first, last, function); }
	template<class F> auto none (F const& function) const{ return std::none_of(first, last, function); }
	template<class F> auto count(F const& function) const{ return std::count_if(first, last, function); }
	template<class F> auto find (F const& function) const{ return std::find_if(first, last, function); }
};


template <typename T>
auto dereferencable(const T& x) -> decltype(void(*x), std::true_type());

std::false_type dereferencable(...);

template<class T> auto range(T first, T last){
	if constexpr(std::is_integral<T>::value){
		assert(first<=last);  // otherwise won't work properly
		return Range<int_iter<T>>{first, last};
	}else{
		static_assert(decltype(dereferencable(first))::value, "value type is neither integral nor dereferencable");
		return Range<T>{first, last};
	}
}

template<class T> auto range(T&& value){
	using T1=typename std::decay<T>::type;
	if constexpr(std::is_integral<T1>::value)
		return Range<int_iter<T1>>{{{}}, std::forward<T>(value)};
	else
		return Range<typename std::decay<decltype(value.begin())>::type>{std::forward<T>(value)};
}


//template<class Container> auto range(Container container){
//	return Range{ std::begin(container), std::end(container) };
//}

template<class T, class=std::enable_if<std::is_integral<T>::value, int>>
auto infinite_range(T first){return Range<int_iter<T>, unreachable_iter>{first, {}};}

template<class T> T&& identity(T&& x){ return x; }

// } ==== End library range ====

// { ==== Begin library modular ====


// { ==== Begin library modmultiply64 ====

// identical to kactl's ModMulLL

int64_t modmultiply64(int64_t a, int64_t b, int64_t mod){
	uint64_t result=
		(uint64_t)a*(uint64_t)b-
		uint64_t((long double)a*b/mod)*(uint64_t)mod;
	result+=mod;
	if(result>=uint64_t(mod)){
		result-=mod;
		if(result>=uint64_t(mod)) result-=mod;
	}
	return int64_t(result);
}

// } ==== End library modmultiply64 ====

// { ==== Begin library extended_gcd ====

/**
 * input: (x, y)
 *
 * return (a, ax) such that a=ax*x+ay*y for some ay
 */
template<class T> struct extended_gcd_result { T a, ax; };
template<class T> extended_gcd_result<T> extended_gcd(T x, T y){
	static_assert(std::is_signed<T>::value, ""); // otherwise ax will fail...
	T a=x,b=y, ax=1,bx=0;
	while(b!=0){
		T q=a/b, t=a%b;
		a=b; b=t;
		t=T(ax-bx*q); ax=bx; bx=t;
	}
	
	return {a, ax};
}

// } ==== End library extended_gcd ====

// { ==== Begin library codecrunch_patch ====

#if __GNUC__<=4  // oops, codecrunch version is too old
#define IFCONSTEXPR__ if
#else
#define IFCONSTEXPR__ if constexpr
#endif

#if __cplusplus<=201199
#define CONSTEXPRIASSIGN__
#else
#define CONSTEXPRIASSIGN__ constexpr
#endif

// } ==== End library codecrunch_patch ====
template<class F, bool comparable=false> struct Modular_ {
	static auto MOD()
#define MOD (F::get())
	->decltype(MOD){ return MOD; }
	using T=typename std::decay<decltype(MOD)>::type;

	T val;
	explicit operator T() const { return val; }
	Modular_():val(){
		#if hasdebugprint
		assert(gdbgetstr(*this));
		#endif
	}
	#if hasdebugprint
	DEFINEGETSTR(Modular_)
	#endif

	template<class U> static auto check_can_mod_mod()->decltype(std::declval<U>()%MOD, void());
	template<class U
		, class=decltype(check_can_mod_mod<U>()) // sfinae
		> Modular_(U v)
		{
			IFCONSTEXPR__ (std::is_unsigned<T>::value){
				assert(v>=0);
			}

			/*
			val=T(v%MOD);
			if(val<0)val+=MOD;
			*/
			val=T(v%MOD);
			if(val<0)val+=MOD;
		}

	template<class U> static Modular_ raw(U value){ // for micro-optimizations
		assert(0<=value); assert(value<MOD);
		Modular_ result; result.val=value; return result;
	}

#define COMPAREOP(OP) \
	template <bool _=comparable> \
	[[nodiscard]] typename std::enable_if<_, bool>::type operator OP(Modular_ b) const { return val OP b.val; }
	COMPAREOP(==) COMPAREOP(!=) COMPAREOP(<) COMPAREOP(>) COMPAREOP(<=) COMPAREOP(>=)
#undef COMPAREOP

	[[nodiscard]] Modular_ operator+() const{ return *this; }
	[[nodiscard]] friend Modular_ operator-(Modular_ x) { x.val=x.val?MOD-x.val:0; return x; }
	Modular_& operator+=(Modular_ m) { if ((val += m.val) >= MOD) val -= MOD; return *this; }
	Modular_& operator-=(Modular_ m) {
		if(std::is_signed<T>::value){
			if ((val -= m.val) < 0) val += MOD;
		}else{
			if (val < m.val) val+=MOD; 
			val-=m.val;
		}
		return *this;
	}
	Modular_& operator*=(Modular_ m) {
		//if(__builtin_constant_p(m.val)){
		//	switch(m.val){
		//		case 1: return *this;
		//		case 2: return *this+=*this;
		//		case 3: return *this+=*this+*this;
		//		case 0: return *this=0;
		//		case -1: return *this=-*this;
		//	}
		//}else if(__builtin_constant_p(val)){
		//	return *this=m**this;
		//}


		IFCONSTEXPR__(std::is_same<T, int>::value){
			val = int((int64_t)val*m.val%MOD);
		}else IFCONSTEXPR__(std::is_same<T, int64_t>::value){
			val=modmultiply64(val, m.val, MOD);
		}else{
			// TODO?
			val*=m.val;
			val%=MOD;
			//assert(false);
		}
		return *this;
	}
	template<class U>
	[[nodiscard]] Modular_ pow(U p) const {
		IFCONSTEXPR__(std::is_signed<U>::value) assert(p>=0);
		Modular_ a=*this;
		Modular_ ans = 1; for (; p; p >>= 1, a *= a) if (p&1) ans *= a;
		return ans;
	}
	[[nodiscard]] Modular_ inv() const {
		auto tmp=extended_gcd(val, T(MOD));
		assert(tmp.a==1);
		if(tmp.ax<0)tmp.ax+=MOD;
		return raw(tmp.ax);
	}
	Modular_& operator/=(Modular_ m) { return (*this) *= m.inv(); }
	friend std::ostream& operator<<(std::ostream& stream, Modular_ m) {
		// debug only
		if(m.val>MOD/2){
			return stream<<'-'<<MOD-m.val<<'_';
		}else{
			return stream<<m.val<<'_';
		}
	}

	

#define ARITHBINARYOP(OP) [[nodiscard]] friend Modular_ operator OP(Modular_ a, Modular_ b) { return a OP##= b; }
	ARITHBINARYOP(+) ARITHBINARYOP(-) ARITHBINARYOP(*) ARITHBINARYOP(/)
#undef ARITHBINARYOP

#undef MOD
};

template<class T, T x> struct TGetter{ static T get(){ return x; } };
template<int MOD> using Modular=Modular_<TGetter<int, MOD>>;
template<int64_t MOD> using Modular64=Modular_<TGetter<int64_t, MOD>>;
template<int MOD> using ModularComparable=Modular_<TGetter<int, MOD>, true>;
template<int64_t MOD> using ModularComparable64=Modular_<TGetter<int64_t, MOD>, true>;
template<int const& MOD> using ModularP=Modular_<TGetter<int const&, MOD>>; // warning: must use global variable (with linkage)
template<int64_t const& MOD> using ModularP64=Modular_<TGetter<int64_t const&, MOD>>; // warning: must use global variable (with linkage)

// } ==== End library modular ====

// { ==== Begin library debug ====

// rarely useful, usually gdb print is sufficient

// { ==== Begin library IntegerSequenceMock.h ====

#if __cplusplus >= 201400

using std::integer_sequence;
using std::index_sequence;
using std::make_integer_sequence;
using std::make_index_sequence;

#else

template<class T, T... values>
struct integer_sequence{
	friend std::ostream& operator<<(std::ostream& stream, integer_sequence const&){
		std::initializer_list<T> data{values...};
		for(auto item: data) stream<<item<<' ';
		return stream;
	}
};

template<std::size_t... values> using index_sequence=integer_sequence<std::size_t, values...>;

template<class T, T first, T... rest>
integer_sequence<T, first, rest...> integer_sequence_concat_helper(
	integer_sequence<T, rest...>
	);

template<class T, bool first_ge_last, T first, T last>
struct make_integer_sequence_range_{};

template<class T, T first, T last>
struct make_integer_sequence_range_<T, true, first, last>{
	using type=integer_sequence<T>;
};

template<class T, T first, T last>
struct make_integer_sequence_range_<T, false, first, last>{
	using type=
		decltype(integer_sequence_concat_helper<T, first>(typename make_integer_sequence_range_<T, first+1>=last, first+1, last>::type{}));
};

template<class T, T first, T last> using make_integer_sequence_range=typename make_integer_sequence_range_<T, first>=last, first, last>::type;
template<std::size_t first, std::size_t last> using make_index_sequence_range=typename make_integer_sequence_range_<std::size_t, first>=last, first, last>::type;

template<class T, T last> using make_integer_sequence=make_integer_sequence_range<T, 0, last>;
template<std::size_t last> using make_index_sequence=make_integer_sequence<std::size_t, last>;

#endif

// } ==== End library IntegerSequenceMock.h ====
#ifndef LOCALPRETTY

#if LOCAL
#define LOCALPRETTY 1
#else
#define LOCALPRETTY 0
#endif

#endif

#if LOCALPRETTY
#define ifpretty(a, b) a
#else
#define ifpretty(a, b) b
#endif

#define prettyseparator ifpretty(", ", ' ')
#define prettybracket(x) ifpretty(stream<<x;,)


template<class T> std::ostream& operator<<(std::ostream& stream, std::vector<T> const& data){
	prettybracket('[')
	if(not data.empty()){
		stream<<data[0];
		for(auto iter=std::next(data.begin()); iter!=data.end(); ++iter)
			stream<<prettyseparator<<*iter;
	}
	prettybracket(']')
	return stream;
}

template<class T, std::size_t N> std::ostream& operator<<(std::ostream& stream, std::array<T, N> const& data){
	prettybracket('[')
	if(not data.empty()){
		stream<<data[0];
		for(auto iter=std::next(data.begin()); iter!=data.end(); ++iter)
			stream<<prettyseparator<<*iter;
	}
	prettybracket(']')
	return stream;
}

template<class A, class B> std::ostream& operator<<(std::ostream& stream, std::pair<A, B> const& data){
	prettybracket('(')
	return stream<<data.first<<prettyseparator<<data.second ifpretty(<<')',);
}

template<class... Types, std::size_t... Is>
void tuple_print_helper(std::ostream& stream, std::tuple<Types...> const& data,
		index_sequence<Is...>
		){
	std::initializer_list<int>({
		(
		 stream<<prettyseparator<<std::get<Is+1>(data)
		 , 0)...
	});
}

template<class... Types> std::ostream& operator<<(std::ostream& stream, std::tuple<Types...> const& data){
	if(sizeof...(Types)==0){
		prettybracket("()")
	}else{
		prettybracket('(')
		stream<<std::get<0>(data);
		tuple_print_helper(stream, data, make_index_sequence<sizeof...(Types)-1>{});
		prettybracket(')')
	}
	return stream;
}

#undef LOCALPRETTY
#undef ifpretty
#undef prettyseparator
#undef prettybracket

#define debug1(x) assert(std::cerr<<#x " = "<<(x));

#define ifstartwithparen(x...) evalMA(getthird emptyM()(parentocomma x, getfirst, getsecond))
#define evalMA(x...) x
#define evalM(x...) x
#define evalM2(x...) evalM(evalM(evalM(evalM(x))))
#define evalM3(x...) evalM2(evalM2(evalM2(evalM2(x))))
#define evalM4(x...) evalM3(evalM3(evalM3(evalM3(x))))
#define evalME(x...) evalM4(evalM4(evalM4(evalM4(x))))
#define emptyM(...)
#define parentocomma(...) ,
#define getfirst(x, ...) x
#define getsecond(x, y, ...) y
#define getfirstA(x, ...) x
#define getsecondA(x, y, ...) y
#define getthird(x, y, z, ...) z
#define ifempty1(x) ifstartwithparen(x())(ifstartwithparen(x)(getsecondA, getfirstA), getsecondA)
#define ifempty(x, y...) ifempty1(x)

#define debuginnerdelay() debuginner
#define debuginner(x, y...) debug1(x) ifempty(y)( assert(std::cerr<<'\n'); , assert(std::cerr<<", "); debuginnerdelay emptyM emptyM()()()(y) )
#define debug(x...) evalME(debuginner(x))

/*
actually Boost already have such a macro, why don't I take it

correctly return true:
	ifempty()(true, false)

correctly return false:
	ifempty(abc)(true, false)
	ifempty(abc, def)(true, false)
	ifempty(())(true, false)

incorrectly return true:
	ifempty(, abc)(true, false)

error out: ignore
*/




#define hasdebugprint 1

std::string result_gdbgetstr;

void function_take_voidp(void const*){}

#define DEFINEGETSTR(T) \
friend char const* gdbgetstr(T const& data){ \
	std::stringstream stream; \
	stream<<data; \
	result_gdbgetstr=std::move(stream).str(); \
	return result_gdbgetstr.c_str(); \
}

//void can_gdbgetstr() const{ gdbgetstr(*this); }



/*usage example

======== add in constructor: (to instantiate the function)

#if hasdebugprint
assert(gdbgetstr(*this));
#endif

======== add in method body:

#if hasdebugprint
DEFINEGETSTR(Modular_)
#endif

======== make it `friend` to avoid the `<...>` part creeping into the name

*/

// } ==== End library debug ====

// { ==== Begin library ModularComb.h ====
/*
 * Usage:

#include"ModularComb.h"

using modular=Modular<1000000007>;
auto const comb=makeComb<modular>(1000);

comb(5, 3) // -> 10

*/


// { ==== Begin library ModularInverseFact.h ====


// { ==== Begin library ModularInverse.h ====

template<class modular>
std::vector<modular> inverseF(int limit){
	std::vector<modular> inverse(limit);
	inverse[1]=1;
	for(unsigned i=2;i<inverse.size();++i){
		if constexpr(std::is_floating_point<modular>::value){
			// this case only happen in local debugging
			inverse[i]=1/modular(i);
		}else{
			assert(modular::MOD()%i!=0    or not "Mod is not prime, i is not invertible mod mod");
			inverse[i]=inverse[modular::MOD()%i]*modular::raw(modular::MOD()-modular::MOD()/i);
		}
	}
	return inverse;
}

// } ==== End library ModularInverse.h ====
template<class modular>
std::vector<modular> inverseFactF(int limit, std::vector<modular> inverse={}){
	if(inverse.empty()) inverse=inverseF<modular>(limit);
	else assert((int)inverse.size()==limit);

	inverse[0]=1;
	for(int index=3; index<(int)inverse.size(); ++index)
		inverse[index]*=inverse[index-1];
	return inverse;
}


// } ==== End library ModularInverseFact.h ====

// { ==== Begin library ModularFact.h ====


template<class modular>
std::vector<modular> factF(int limit){
	std::vector<modular> fact(limit);
	fact[0]=1;
	for(int i=1; i<(int)fact.size(); ++i)
		fact[i]=fact[i-1]*i;
	return fact;
}

// } ==== End library ModularFact.h ====
template<class modular, bool tolerant> struct Comb_internalStructure{
	std::vector<modular> fact, inverseFact;
	modular operator()(int a, int b)const{
		if(tolerant and (b<0 or b>a)) return 0;
		return fact[a]*inverseFact[b]*inverseFact[a-b];
	}
	modular inverseComb(int a, int b)const{
		if(b<0 or b>a) { assert(false); __builtin_unreachable(); }
		return inverseFact[a]*fact[b]*fact[a-b];
	}
	modular walk(int a, int b) const{
		return (*this)(a+b, b);
	}
};

template<class modular, bool tolerant=false>
Comb_internalStructure<modular, tolerant> makeComb(int limit){
	Comb_internalStructure<modular, tolerant> result;
	result.fact=factF<modular>(limit);
	result.inverseFact=inverseFactF<modular>(limit);
	return result;
}

template<class modular>
Comb_internalStructure<modular, true> makeTolerantComb(int limit){ return makeComb<modular, true>(limit); }

// } ==== End library ModularComb.h ====
#if 0
using modular=double;
#else
using modular=Modular<998244353>;
#endif
auto comb=makeTolerantComb<modular>(int(2e6+1));

void up();
int main(){
	std::ios::sync_with_stdio(0);std::cin.tie(0);
	int numTest; std::cin>>numTest;
	while(numTest--) up();
}

void up(){
	int const number=read<int>();
	//std::vector<int> a(number);
	std::vector<int> count(number);
	//for(auto& it: a) {

	for(int _=number; _--;){
		auto const it=read<int>()-1;
		count[it]++;
	}

	count.erase(std::remove(begin(count), end(count), 0), end(count));
	
	if(count.size()==1){
		std::cout<<"1\n";
		return;
	}

	std::vector<int> countGcd(number+1); // this could be more efficient...
	for(int const i: range(0, number)){
		countGcd[std::__gcd(i, number)]++;
	}

	assert(count.size()>=2);
	std::sort(begin(count), end(count));
	std::vector<std::pair<int, int>> countDeduplicated; // list of {value, number of elements}
	int last=0;
	for(int const index: range(1, int(count.size())+1)){
		if(index==int(count.size()) or count[index]!=count[index-1]){
			countDeduplicated.push_back({count[index-1], index-last});
			last=index;
		}
	}
	
	auto gcdAll=std::accumulate(begin(count), end(count), 0, [&](auto const& first, auto const& sec){
		return std::__gcd(first, sec);
	});
	assert(number%gcdAll==0);

	int64_t sumSquaredCount_ {};
	for(auto c: count) sumSquaredCount_+=int64_t(c)*c;
	modular sumSquaredCount=sumSquaredCount_;

	auto const denomPart=[&]  (int d)->modular{
		// sum of 1 over all x that remains the same under shift by d
		// that is, sum of 1 over all x with length number/r and count=count[...]/r
		auto const r=number/d;
		modular tmp=comb.fact[d];
		/*
		for(auto c: count){
			assert(c%r==0);
			c/=r;
			tmp*=comb.inverseFact[c];
		}
		*/
		for(auto [c, repetition]: countDeduplicated){
			assert(c%r==0);
			c/=r;
			tmp*=comb.inverseFact[c].pow(repetition);
		}
		return tmp;
	};
	auto const numerPart=[&](int d)->modular{
		assert(d!=1); // because count.size()>=2

		auto const r=number/d;
		// sum of numBlocks[x] over all x that remains the same under shift by d
		// that is, sum of numBlocks[x] over all x with length number/r and count=count[...]/r
		// or denomPart(d)  E(numBlocks[x])
		modular tmp = sumSquaredCount/r/r-d;
		return denomPart(d)*number*(1-tmp/d/(d-1));
	};

	modular numer {}, denom {};
	for(int const d: range(1, number+1)) if(countGcd[d]!=0 and gcdAll%(number/d)==0){
		numer+=numerPart(d)*countGcd[d];
		denom+=denomPart(d)*countGcd[d];
	}
	
	std::cout<<int(numer/denom)<<'\n';
}
// time-limit: 2000
// problem-url: https://codeforces.com/contest/1630/problem/E