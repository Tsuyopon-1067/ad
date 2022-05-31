#!/bin/bash
a=$1
out=""
for i in $(seq $a)
do
    t=${i}
    if [ $((${i} % 2)) = 0 ]; then
        t=$((a-i+2))
    fi
    echo ${t}$'\t'${i} >> input_${a}.tsv
done
