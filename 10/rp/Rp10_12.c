#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MyLinkedList.h"

char *getStringArray(int list[], int size) {
    char buf[128] = "";
    char mes[256] = "";
    char *res;

    int i;
    for (i=0; i<size; i++) {
        snprintf(buf, 128, "%d", list[i]);
        if (i==0) strcat(mes, "[");
        strcat(mes, buf);
        if (i!=size-1) strcat(mes, ", ");
        else           strcat(mes, "]");
    }
    res = (char *)malloc(sizeof(char)*(strlen(mes)+1));
    strncpy(res, mes, strlen(mes)+1);
    return res;
}

int testOneData(MyLinkedList *mll, int cr) { // 要素単体テスト
    MyLinkedData *r;
    r = search(mll, cr);
    if (r != NULL && r->data == cr) {
        printf("  search success: %d\n", cr);
        return 1;
    } else {
        printf("  search failure: %d\n", cr);
        return 0;
    }
}

int test(MyLinkedList *mll, int cr[], int size) { // 全データテスト
    int count=0;
    int i;
    for (i=0; i<size; i++) {
        if (testOneData(mll, cr[i])) count++;
    }
    printf("success: %d/%d ", count, size);
    if (count == size) return 1;
    else               return 0;
}

void testAll(int cr[], int size) {
    printf("=====\ntest %s\n", getStringArray(cr, size));
    MyLinkedList *list = newMyLinkedList();
    addAll(list, cr, size);
    if (test(list, cr, size)) printf("OK\n");
    else                      printf("NG\n");
}

int main(void) {
    int a[] = {0, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000};
    int b[] = {4096, 512, 16, 1};
    int c[] = {1, 1, 2, 3, 5, 8}; // ex1 step1.2 で使ったものをコピー

    testAll(a, 11);
    testAll(b, 4);
    testAll(c, 6);

    return 0;
}
