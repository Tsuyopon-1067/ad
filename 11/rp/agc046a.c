#include <stdio.h>

int gcm(int a, int b) {
    if (a % b == 0) {
        return b;
    }
    return gcm(b, a%b);
}
int lcm(int a, int b) {
    return a / gcm(a, b) * b;
}
int main() {
    int x;
    scanf("%d", &x);
    int degree = lcm(360, x);
    printf("%d\n", degree / x);
    return 0;
}