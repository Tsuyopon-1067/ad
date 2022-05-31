#!/bin/bash
gcc Ex13.c -o Ex13.out
echo "complile complete."
case $1 in
    "b")
       echo "bubble sort."
        time ./Ex13.out b input_100.tsv > sortB.tsv
        time ./Ex13.out b input_1000.tsv > sortB.tsv
        time ./Ex13.out b input_10000.tsv > sortB.tsv
        time ./Ex13.out b input_20000.tsv > sortB.tsv
        time ./Ex13.out b input_30000.tsv > sortB.tsv
        time ./Ex13.out b input_40000.tsv > sortB.tsv
        time ./Ex13.out b input_50000.tsv > sortB.tsv
        ;;
    "i")
        echo "insertion sort."
        time ./Ex13.out i input_100.tsv > sortI.tsv
        time ./Ex13.out i input_1000.tsv > sortI.tsv
        time ./Ex13.out i input_10000.tsv > sortI.tsv
        time ./Ex13.out i input_20000.tsv > sortI.tsv
        time ./Ex13.out i input_30000.tsv > sortI.tsv
        time ./Ex13.out i input_40000.tsv > sortI.tsv
        time ./Ex13.out i input_50000.tsv > sortI.tsv
        ;;
    "s")
        echo "selection sort."
        time ./Ex13.out s input_100.tsv > sortS.tsv
        time ./Ex13.out s input_1000.tsv > sortS.tsv
        time ./Ex13.out s input_10000.tsv > sortS.tsv
        time ./Ex13.out s input_20000.tsv > sortS.tsv
        time ./Ex13.out s input_30000.tsv > sortS.tsv
        time ./Ex13.out s input_40000.tsv > sortS.tsv
        time ./Ex13.out s input_50000.tsv > sortS.tsv
        ;;
    "q")
        echo "quick sort."
        time ./Ex13.out q input_100.tsv > sortQ.tsv
        time ./Ex13.out q input_1000.tsv > sortQ.tsv
        time ./Ex13.out q input_10000.tsv > sortQ.tsv
        time ./Ex13.out q input_20000.tsv > sortQ.tsv
        time ./Ex13.out q input_30000.tsv > sortQ.tsv
        time ./Ex13.out q input_40000.tsv > sortQ.tsv
        time ./Ex13.out q input_50000.tsv > sortQ.tsv
        ;;
esac
