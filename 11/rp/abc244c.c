#include <stdio.h>
#include <stdbool.h>

int main() {
    int n;
    scanf("%d", &n);

    bool flag[2002];
    for (int i = 0; i <= 2001; ++i) {
        flag[i] = false;
    }

    for (int i = 0; i <= 2*n+1; ++i) {
        if (i % 2 == 0) {
            for (int i = 1; i <= 2*n+1; ++i) {
                if (flag[i] == false) {
                    flag[i] = true;
                    printf("%d\n", i);
                    break;
                }
            }
        } else {
            int input;
            scanf("%d", &input);
            flag[input] = true;
        }
        fflush(stdout);
    }

    return 0;
}