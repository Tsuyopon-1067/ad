#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MyLinkedList.h"

MyLinkedList *newMyLinkedList() {
    MyLinkedList *new = (MyLinkedList *)malloc(sizeof(MyLinkedList));
    new->head = newMyLinkedData(-99999999);
    new->tail = new->head;
    return new;
}

void addAll(MyLinkedList *mll, int list[], int size) {
    int i;
    for (i = 0; i < size; i++) {
        add(mll, list[i]);
    }
}

void add(MyLinkedList *mll, int data) {
    MyLinkedData *newData = newMyLinkedData(data);
    mll->tail->next = newData;
    mll->tail = newData;
}

MyLinkedData *search(MyLinkedList *mll, int data) {
    MyLinkedData *p;
    p = mll->head;
    while (p != NULL) {
        if (p->data == data) {
            return p;
        }
        p = p->next;
    }
    return NULL;
}

char *getStringMyLinkedList(MyLinkedList *mll) {
    char mes[256];
    char *res;
    if (mll->head->next == NULL) {
        strncpy(mes, "head: NULL", 255);
    } else {
        MyLinkedData *p = mll->head->next;
        while(p != NULL) {
            strcat(mes, getStringMyLinkedData(p));
            strcat(mes, "\n");
            p = p->next;
        }
    }
    res = (char *)malloc(sizeof(char)*(strlen(mes)+1));
    strncpy(res, mes, strlen(mes)+1);
    return res;
}
