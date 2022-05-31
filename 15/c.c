#include <stdio.h>

int max(int a, int b) {
    if (a < b) return b;
    else return a;
}
int main() {
    int n;
    scanf("%d", &n);
    int a[100000];
    int b[100000];
    int c[100000];
    for(int i = 0; i < n; i++) {
        scanf("%d %d %d", &a[i], &b[i], &c[i]);
    }

    int dp[100001][3];
    dp[0][0] = a[0];
    dp[0][1] = b[0];
    dp[0][2] = c[0];
    for (int i = 1; i < n; ++i) {
        dp[i][0] = max(dp[i-1][1] + a[i], dp[i-1][2] + a[i]);
        dp[i][1] = max(dp[i-1][2] + b[i], dp[i-1][0] + b[i]);
        dp[i][2] = max(dp[i-1][0] + c[i], dp[i-1][1] + c[i]);
    }

    int ans = 0;
    for (int i = 0; i < 3; ++i) {
        ans = max(ans, dp[n-1][i]);
    }
    printf("%d\n", ans);
    return 0;
}
