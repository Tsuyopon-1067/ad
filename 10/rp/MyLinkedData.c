#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MyLinkedData.h"

int NODEID = 0;

MyLinkedData *newMyLinkedData(int data) {
    MyLinkedData *new = (MyLinkedData *)malloc(sizeof(MyLinkedData));
    new->id   = NODEID;
    new->data = data;
    new->next = NULL;
    NODEID++;
    return new;
}

char *getStringMyLinkedData(MyLinkedData *mld) {
    char mes[256];
    char *res;
    if (mld->next == NULL) {
        sprintf(mes, "[%d]%d -> NULL", mld->id, mld->data);
    } else {
        sprintf(mes, "[%d]%d -> [%d]", mld->id, mld->data, mld->next->id);
    }
    res = (char *)malloc(sizeof(char)*(strlen(mes)+1));
    strncpy(res, mes, strlen(mes));
    return res;
}
