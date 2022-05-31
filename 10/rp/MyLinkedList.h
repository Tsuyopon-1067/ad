#include "MyLinkedData.h"

#ifndef MYLINKEDLIST_H
#define MYLINKEDLIST_H

typedef struct _myLinkedList {
    MyLinkedData *head;
    MyLinkedData *tail;
} MyLinkedList;

MyLinkedList *newMyLinkedList();
void addAll(MyLinkedList *, int[], int);
void add(MyLinkedList *, int);
MyLinkedData *search(MyLinkedList *, int);
char *getStringMyLinkedList(MyLinkedList *);

#endif