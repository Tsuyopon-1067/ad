#include <stdio.h>
int main() {
    int n;
    scanf("%d", &n);
    int a[100];
    int sum = 0;
    for (int i = 0; i < n; ++i) {
        scanf("%d", &a[i]);
        sum += a[i];
    }

    int ans = 0;
    for (int i = 0; i < n; ++i) {
        if (sum >= 4) {
            ++ans;
        }
        sum -= a[i];
    }

    printf("%d\n", ans);

    return 0;
}
