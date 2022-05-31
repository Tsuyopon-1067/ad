#include <stdio.h>
#include <limits.h>  // ファイルの先頭に追加
#include <string.h>

int a[20];
int a1[] = {0, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000};
int a2[] = {4096, 512, 16, 1};
int a3[] = {1, 1, 2, 3, 5, 8};

int search(int key) {
    int res = -1;
    int size = sizeof(a) / sizeof(a[0]);
    for (int i = 0; i < size; ++i) {
        if (a[i] == key) {
            res = i;
        }
    }
    return res;
}

void test() {
    int i;
    int r;
    int c;
    int W_NUM = 2;
    int wList[W_NUM]; // あるはずのないものリスト
    wList[0] = INT_MAX; // 2147483647  a[] に入っていないと仮定
    wList[1] = INT_MIN; // -2147483648 a[] に入っていないと仮定

    int j = 0;
    for (j = 0; j < 3; ++j) {
        int size;
        switch (j) {
            case 0:
                memcpy(a, a1, sizeof(a1));
                size = sizeof(a1) / sizeof(a[0]);
                break;
            case 1:
                memcpy(a, a2, sizeof(a2));
                size = sizeof(a2) / sizeof(a[0]);
                break;
            case 2:
                memcpy(a, a3, sizeof(a3));
                size = sizeof(a3) / sizeof(a[0]);
                break;
        }
        c = 0;

        printf("Starting test%d.\n", j+1);
        printf("a = [");
        for (i = 0; i < size; i++) {
            printf("%d", a[i]);
            if (i+1 < size) printf(", ");
        }
        printf("]\n");

        // あるはずのものをテスト
        for (i = 0; i < size; i++) {
            r = search(a[i]);
            if (r == -1) {
                printf("%d is not found\n", a[i]);
            } else {
                printf("%d is found\n", a[i]);
                c++;
            }
        }
        printf("Correct success: %d/%d\n", c, size);

        c = 0;
        // あるはずのないものをテスト
        for (i = 0; i < W_NUM; i++) {
            r = search(wList[i]);
            if (r == -1) {
                printf("%d is not found\n", wList[i]);
                c++;
            } else {
                printf("%d is found\n", wList[i]);
            }
        }
        printf("Correct reject: %d/%d\n", c, W_NUM);
        if (j+1 < 3) printf("\n");
    }

    return;
}

int main(void) {
    test();
    return 0;
}
