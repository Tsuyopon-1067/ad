#include <stdio.h>
int main() {
    int n, x;
    scanf("%d %d", &n, &x);
    printf("%c\n", 'A' + (x-1)/n);
    return 0;
}
