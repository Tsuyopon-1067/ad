#!/bin/bash
gcc Ex13.c -o Ex13.out
echo "complile complete."
case $1 in
    "b")
       echo "bubble sort."
        ./Ex13.out b input.tsv > sortB.tsv
        ;;
    "i")
        echo "insertion sort."
        ./Ex13.out i input.tsv > sortI.tsv
        ;;
    "s")
        echo "selection sort."
        ./Ex13.out s input.tsv > sortS.tsv
        ;;
    "q")
        echo "quick sort."
        ./Ex13.out q input.tsv > sortQ.tsv
        ;;
esac
