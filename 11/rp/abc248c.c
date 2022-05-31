#include <stdio.h>

int main() {
    int n, m, k;
    scanf("%d %d %d", &n, &m, &k);
    int dp[51][2501];
    for (int i = 0; i <= 50; ++i) {
        for (int j = 0; j <= 2501; ++j) {
            dp[i][j] = 0;
        }
    }
    for (int i = 1; i <= m; ++i) {
        dp[1][i] = 1;
    }

    for (int i = 1; i < n; ++i) {
        for (int j = 1; j <= k; ++j) {
            for (int l = 1; l <= m; ++l) {
                int jj = j + l;
                if (jj <= k) {
                    dp[i+1][jj] = (dp[i+1][jj]+dp[i][j]) % 998244353;
                }
            }
        }
    }

    int ans = 0;
    for (int j = 1; j <= k; ++j) {
        ans += dp[n][j];
        ans %= 998244353;
    }
    printf("%d\n", ans);
    return 0;
}