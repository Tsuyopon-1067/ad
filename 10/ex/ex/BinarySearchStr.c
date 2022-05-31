#include <stdio.h>
#include <string.h>

#define LENGTH 256

#define NUM 7
char str[][LENGTH] = {"Friday", "Monday", "Saturday", "Sunday", "Thursday", "Tuesday", "Wednesday"};
//#define NUM 12
//char str[][LENGTH] = {"Apr.", "Aug.", "Dec.", "Feb.", "Jan.", "Jul.", "Jun.", "Mar.", "May", "Nov.", "Oct.", "Sep."};

int search(char key[]) {
    int l, m, r;
    l = 0;
    r = NUM;

    while (l <= r) {
        m = (l+r)/2;
        int result = strcmp(key, str[m]);
        if (result == 0) {
            return m;
        } else if (result < 0) {
            r = m -1;
        } else {
            l = m + 1;
        }
    }
    return -1;
}

int main(void) {
    char input[LENGTH];
    char s[LENGTH];
    int r;
    while (1) {
        printf("word: ");
        fgets(input, LENGTH-1, stdin);
        sscanf(input, "%s", s);
        if (strcmp(s, "-999999") == 0) break; // 終了 9が6個
        r = search(s);
        if (r == -1) {
            printf("%s is not found.\n", s);
        } else {
            printf("%s is found.\n", s);
        }
    }
    return 0;
}
