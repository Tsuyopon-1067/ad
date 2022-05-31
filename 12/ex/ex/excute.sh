#!/bin/bash
gcc Ex12.c -o Ex12.out
echo "complile complete."
case $1 in
    "b")
        echo "bubble sort."
        ./Ex12.out b input.tsv > sortB.tsv
        ;;
    "i")
        echo "insertion sort."
        ./Ex12.out i input.tsv > sortI.tsv
        ;;
    "s")
        echo "selection sort."
        ./Ex12.out s input.tsv > sortS.tsv
        ;;
esac
