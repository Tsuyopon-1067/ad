#!/bin/bash
gcc Ex12.c -o Ex12.out
echo "complile complete."
case $1 in
    "b")
        echo "bubble sort."
        ./Ex12.out b input_desc.tsv > sortB.tsv
        ;;
    "i")
        echo "insertion sort."
        ./Ex12.out i input_desc.tsv > sortI.tsv
        ;;
    "s")
        echo "selection sort."
        ./Ex12.out s input_desc.tsv > sortS.tsv
        ;;
esac
