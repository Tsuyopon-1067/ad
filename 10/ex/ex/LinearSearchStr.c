#include <stdio.h>
#include <string.h>

#define LENGTH 256

#define NUM 7
char str[][LENGTH] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
//#define NUM 12
//char str[][LENGTH] = {"Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."};

int search(char key[]) {
    int res = -1;
    for (int i = 0; i < NUM; ++i) {
        if (strcmp(key, str[i]) == 0) {
            res = i;
        }
    }
    return res;
}

int main(void) {
    char input[LENGTH];
    char s[LENGTH];
    int r;
    while (1) {
        printf("index: ");
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
