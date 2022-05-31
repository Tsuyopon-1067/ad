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

int main(void) {
    int i;
    int a[] = {0, 1, 4, 9, 16, 25};
    MyLinkedList *list = newMyLinkedList();
    printf("%s\n", getStringArray(a, 6));        // (1)
    addAll(list, a, 6);
    printf("%s\n", getStringMyLinkedList(list)); // (2)

    return 0;
}
