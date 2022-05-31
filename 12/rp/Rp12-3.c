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
    int num = -1;
    while (!feof(fp) && size < SIZE) { // ファイル終端でない かつ 10万以下
        fscanf(fp, "%d %d %d", &num, &value[size], &key[size]);
        //if (size < 30) printf("%d %d %d\n", num, value[size], key[size]);
        ++size;
    }
    --size; // EOF分1つ余分にカウントしてるので戻す
    fclose(fp); // 読み込み終わったらcloseする(これがないと他ソフトから編集できない)
}

int main(int argSize, char** args) {
    FILE *fp; // FILE型構造体
    char* file = args[1];
    readFile(fp, file);

    int beforeKey = -1;
    int beforeValue = -1;
    bool isStable = true;
    for (int i = 0; i < size; ++i) {
        //printf("%d | %d %d | %d %d\n", i, value[i], key[i], beforeValue, beforeKey);
        if (beforeValue < value[i]) {
            beforeValue = value[i];
            beforeKey = -1;
        } else {
            if (beforeKey < key[i]) {
                beforeKey = key[i];
            } else {
                isStable = false;
                break;
            }
        }
    }

    if (isStable) {
        fprintf(stderr, "This is stable.\n");
    } else {
        fprintf(stderr, "This is not stable.\n");
    }
}
