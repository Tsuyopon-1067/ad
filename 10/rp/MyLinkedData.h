#include <stdio.h>
    
#ifndef MYLINKEDDATA_H
#define MYLINKEDDATA_H
    
typedef struct _myLinkedData {
    int id;
    int data;
    struct _myLinkedData *next;
} MyLinkedData;
    
MyLinkedData *newMyLinkedData(int);
char *getStringMyLinkedData(MyLinkedData *);
    
#endif