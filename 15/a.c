#include <stdio.h>

int abs(int a) {
    if (a < 0) a = -a;
    return a;
}
int min(int a, int b) {
    if (a < b) return a;
    else return b;
}
int main() {
    int n;
    scanf("%d", &n);
    int h[100000];
    for(int i = 0; i < n; i++) scanf("%d", &h[i]);
    
    const int INF = 100000000;
    int dp[100001];
    for (int i = 0; i < 100001; i++) {
        dp[i] = INF;
    }
    dp[0] = 0;
    for(int i = 1; i < n; i++) {
        dp[i] = dp[i-1] + abs(h[i] - h[i-1]);
        if(i >= 2) {
            dp[i] = min(dp[i], dp[i-2] + abs(h[i] - h[i-2]));
        }
    }
    printf("%d\n", dp[n-1]);
    
    return 0;
}
