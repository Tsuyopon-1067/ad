#include <stdio.h>
#include <stdbool.h>
#include "MyStack.h"

int isEmptyCount = 0;
int isEmptyCorrectCount = 0;
int isFullCount = 0;
int isFullCorrectCount = 0;
int pushCount = 0;
int pushCorrectCount = 0;
int popCount = 0;
int popCorrectCount = 0;


void resetCount()
{
    isEmptyCount = 0;
    isEmptyCorrectCount = 0;
    isFullCount = 0;
    isFullCorrectCount = 0;
    pushCount = 0;
    pushCorrectCount = 0;
    popCount = 0;
    popCorrectCount = 0;
}

bool test(char *label, bool result, bool estimated)
{
    printf("%s : result[%s] estimated[%s]\n", label, (result ? "True" : "False"), (estimated ? "True" : "False"));
    if (result == estimated)
    {
        printf("success.\n");
        return true;
    }
    else
    {
        printf("failure.\n");
        return false;
    }
}

void isEmptyTest(bool estimated)
{ //
    isEmptyCount++;
    if (test("isEmpty", isEmpty(), estimated))
    {
        isEmptyCorrectCount++;
    }
}

void isFullTest(bool estimated)
{
    isFullCount++;
    if (test(" isFull", isFull(), estimated))
    {
        isFullCorrectCount++;
    }
}

void pushTest(int data, bool estimated)
{
    pushCount++;
   // char* sender = sprintf("   push(%d)", data);
    char sender[128];
    sprintf(sender, "   push(%d)", data);
    if (test(sender, push(data), estimated))
    {
        pushCorrectCount++;
    }
}

void popTest(int estimated)
{
    popCount++;
    if (test("    pop", pop(), estimated))
    {
        popCorrectCount++;
    }
}

void printTestResult(char *label, int count, int total)
{ // 項目ごと
    printf("%s : %d / %d\n", label, count, total);
    if (count == total)
        printf("OK");
    else
        printf("NG");
}

void printAllTestResult()
{
    printTestResult("isEmpty", isEmptyCorrectCount, isEmptyCount);
    printTestResult("   push", pushCorrectCount, pushCount);
    printTestResult("    pop", popCorrectCount, popCount);
}

int main()
{
    MyStack1(3);
    isEmptyTest(true);    // stack[] is empty
    pushTest(10, true);   // stack[10]
    pushTest(5, true);    // stack[10,5]
    popTest(5);           // stack[10]
    isEmptyTest(false);   // stack[10] is not empty
    pushTest(1, true);    // stack[10,1]
    pushTest(6, true);    // stack[10,1,6]
    isFullTest(true);     // stack[10,1,6] is full
    pushTest(5, false);   // stack[10,1,6] because stack size is 3
    popTest(6);           // stack[10,1]
    popTest(1);           // stack[10]
    isEmptyTest(false);   // stack[10] is not isEmpty
    popTest(10);          // stack[]
    popTest(-2147483648); // pop returns INT_MIN if stack is empty

    printAllTestResult();
    return 0;
}
