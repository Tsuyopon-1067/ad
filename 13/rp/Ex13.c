#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define SIZE 100001

int value[SIZE]; // random
int key[SIZE]; // 1, 2, 3...
int size = 0;
int compareCount = 0;
int swapCount = 0;

void readFile(FILE *fp, char* fileName) {
    fp = fopen(fileName, "r"); // r:読み込みモード
    if(fp == NULL) { // 読み込み失敗してたらfpはNULL
        fprintf(stderr, "Can't open %s.\n", fileName);
    } else {
        fprintf(stderr, "Open %s.\n", fileName);
    }

    size = 0;
    while (!feof(fp) && size < SIZE) { // ファイル終端でない かつ 10万以下
        fscanf(fp, "%d %d", &value[size], &key[size]);
        ++size;
    }
    --size; // EOF分1つ余分にカウントしてるので戻す
    fclose(fp); // 読み込み終わったらcloseする(これがないと他ソフトから編集できない)
}
void swap(int x, int y) {
    int tmpV = value[x];
    value[x] = value[y];
    value[y] = tmpV;
    int tmpK = key[x];
    key[x] = key[y];
    key[y] = tmpK;
    ++swapCount;
}

int compare(int x, int y) {
    ++compareCount;
    return value[x] - value[y]; // a[x]>a[y] -> +
}

void bubbleSort() {
    for (int i = 0; i < size-1; ++i) {
        for (int j = size-1; j > i; --j) {
            if (compare(j-1, j) > 0) {
                swap(j-1, j);
            }
        }
    }
}

void insertionSort() {
    for (int i = 1; i < size; ++i) {
        int j = i;
        while (j >= 1 && compare(j-1, j) > 0) {
            swap(j, j-1);
            --j;
        }
    }
}
void selectionSort() {
    for (int i = 0; i < size; ++i) {
        int minIdx = i;
        for (int j = i; j < size; ++j) {
            if (compare(j, minIdx) < 0) {
                minIdx = j;
            }
        }
        swap(i, minIdx);
    }
}
int partition(int l, int r) {
    int i = l-1;
    int j = r;
    int pivot = r;

    while (true) {
        while (compare(++i, pivot) < 0);
        while (i < --j && compare(pivot, j) < 0);
        if (i >= j) {
            break;
        }
        swap(i, j);
    }
    swap(i, pivot);
    return i;
}
int countq = 0;
void quickSortSub(int l, int r) {
    int v; // 境界線
    if (l >= r) {
        return;
    }
    v = partition(l, r);

    quickSortSub(l, v-1);
    quickSortSub(v+1, r);
}
void quickSort() {
    quickSortSub(0, size-1);
}

void showArray() {
     for (int i = 0; i < size; ++i) {
        printf("%d\t%d\t%d\n", i, value[i], key[i]);
    }
}

int main(int argSize, char** args) {
    FILE *fp; // FILE型構造体
    char* file = args[2];
    char sortType = args[1][0];
    readFile(fp, file);

    switch (sortType) {
        case 'b':
            fprintf(stderr, "BubbleSort is selected.\n");
            bubbleSort();
            break;
        case 'i':
            fprintf(stderr, "InsertionSort is selected.\n");
            insertionSort();
            break;
        case 's':
            fprintf(stderr, "SelectionSort is selected.\n");
            selectionSort();
            break;
        case 'q':
            fprintf(stderr, "QuickSort is selected.\n");
            quickSort();
            break;
    }

    showArray();

    fprintf(stderr, "compareCount: %d\n", compareCount);
    fprintf(stderr, "   swapCount: %d\n", swapCount);
}
