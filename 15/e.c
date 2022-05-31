#include <stdio.h>
#define INF 2000000000

long int min(long int a, long int b) {
    if (a < b) return a;
    else return b;
}
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

    long int dp[101][1000001];
    for (int i = 0; i <= n; ++i) {
        for (int j = 0; j <= 1000000; ++j) {
            dp[i][j] = INF;
        }
    }
    dp[0][0] = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= 1000000; ++j) {
            if(dp[i][j] == INF) {
                continue;
            }
            int vidx = j + v[i];
            if (vidx > 1000000) continue;
            dp[i+1][j] = min(dp[i+1][j], dp[i][j]);
            dp[i+1][vidx] = min(dp[i+1][vidx], dp[i][j] + w[i]);
        }
    }
    
    long int ans = 0;
    for (int i = 0; i <= 1000000; ++i) {
        if (dp[n][i] <= W) {
            ans = max(ans, i);
        }
    }
    printf("%lld\n", ans);
    return 0;
}
