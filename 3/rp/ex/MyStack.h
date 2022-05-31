#include <stdlib.h> // メモリ確保
#include <stdbool.h>
const int STACKSIZE = 30;
const int POPERROR = -2147483648;

int sp = 0;
int *stack;
int stack_length = STACKSIZE;

void MyStack1(int size) {
    int *tmp = (int*)realloc(stack, sizeof(int) * size);
    stack = tmp;
    stack_length = size;
}
void MyStack() {
    MyStack1(STACKSIZE);
}
bool isEmpty() { 
    if (sp == 0) {
        return true;
    }
    return false;
}

bool isFull() { 
    if (sp == stack_length) { 
        return true;
    }
    return false;
}
int pop() {
    if (isEmpty()) {
        return POPERROR;
    }
    sp--;
    return stack[sp];
}
bool push(int data) {
    if (isFull()) {
        return false;
    }
    stack[sp] = data;
    sp++;
    return true;
}

