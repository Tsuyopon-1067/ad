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
    int n, k;
    scanf("%d %d", &n, &k);
    int h[100000];
    for (int i = 0; i < n; i++) {
        scanf("%d", &h[i]);
    }

    int dp[100001];
    for (int i = 0; i < 100001; i++) {
        dp[i] = 2000000000;
    }
    dp[0] = 0;
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j <= k; ++j) {
            if(i - j >= 0) {
                dp[i] = min(dp[i], dp[i-j] + abs(h[i] - h[i-j]));
            }
        }
    }
    printf("%d\n", dp[n-1]);
    
    return 0;
}
