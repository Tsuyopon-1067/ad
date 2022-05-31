#include <stdio.h>

long int max(long int a, long int b) {
    if (a < b) return b;
    else return a;
}
int main() {
    int n, W;
    scanf("%d %d", &n, &W);
    int w[101];
    int v[101];
    for(int i = 0; i < n; i++) {
    scanf("%d %d", &w[i], &v[i]);
    }

    long int dp[101][100001];
    for (int i = 0; i <= n; ++i) {
        for (int j = 0; j <= W; ++j) {
            dp[i][j] = -1;
        }
    }
    dp[0][0] = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= W; ++j) {
            if(dp[i][j] == -1) {
                continue;
            }
            dp[i+1][j] = max(dp[i+1][j], dp[i][j]);
            if(j + w[i] <= W) {
                dp[i+1][j+w[i]] = max(dp[i+1][j+w[i]], dp[i][j] + v[i]);
            }
        }
    }
    long int ans = 0;
    for (int i = 0; i <= W; ++i) ans = max(ans, dp[n][i]);
    printf("%lld\n", ans);
    return 0;
}
