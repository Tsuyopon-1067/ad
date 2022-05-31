#!/bin/bash
gcc Ex12.c -o Ex12.out
echo "complile complete."
case $1 in
    "b")
        echo "bubble sort."
        ./Ex12.out b input_aasc.tsv > sortB.tsv
        ;;
    "i")
        echo "insertion sort."
        ./Ex12.out i input_aasc.tsv > sortI.tsv
        ;;
    "s")
        echo "selection sort."
        ./Ex12.out s input_aasc.tsv > sortS.tsv
        ;;
esac
