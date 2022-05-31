#include <bits/stdc++.h>
using namespace std;
using i64 = int64_t;

string S;
int p = 0;

i64 expr();
i64 term();
i64 factor();

// <factor> := [0-9]+ | '(' <expr> ')'
i64 factor() {
  if (isdigit(S[p])) {
    i64 ret = 0;
    while (isdigit(S[p])) {
      ret = ret * 10 + (S[p] - '0');
      ++p;
    }
    return ret;
  }

  ++p; // skip '('
  i64 ret = expr();
  ++p; // skip ')'
  return ret;
}

// <term> := <factor> (('*' | '/') <term>)*
i64 term() {
  i64 ret = factor();

  while (S[p] == '*' || S[p] == '/') {
    if (S[p] == '*') {
      ++p;
      ret *= factor();
    } else if (S[p] == '/') {
      ++p;
      ret /= factor();
    }
  }
  return ret;
}

// <expr> := <term> (('+' | '-') <term>)*
i64 expr() {
  i64 ret = term();

  while (S[p] == '+' || S[p] == '-') {
    if (S[p] == '+') {
      ++p; // skip '+'
      ret += term();
    } else if (S[p] == '-') {
      ++p; // skip '-'
      ret -= term();
    }
  }
  return ret;
}

int main() {
  int N;
  cin >> N;
  for (int i = 0; i < N; ++i) {
    cin >> S;
    p = 0;

    cout << expr() << endl;
  }
}
