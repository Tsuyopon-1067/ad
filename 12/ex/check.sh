#!/bin/bash
declare -i a
declare -i b
b=0;
for i in cut -f1 -d $1
do
    a=$i
    echo ${a} ${b}
    if [ ${a} -lt ${b} ];then
        echo ERROR
        exit 1
    fi
    b=$i
done
echo OK
exit 0
