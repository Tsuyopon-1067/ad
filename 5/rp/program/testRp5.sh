#!/bin/sh
echo "touroku -------------------------------------------------------------"
java Rp5 rp5-data-1500.txt < quit.txt
java Rp5 rp5-data-1800.txt < quit.txt
java Rp5 rp5-data-2100.txt < quit.txt
java Rp5 rp5-data-2400.txt < quit.txt
java Rp5 rp5-data-2700.txt < quit.txt
java Rp5 rp5-data-2800.txt < quit.txt
java Rp5 rp5-data-2900.txt < quit.txt
echo ""
echo "tuika ---------------------------------------------------------------"
java Rp5 rp5-data-1500.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-1800.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-2100.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-2400.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-2700.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-2800.txt rp5-ins.txt < quit.txt
java Rp5 rp5-data-2900.txt rp5-ins.txt < quit.txt
echo ""
echo "find ----------------------------------------------------------------"
java Rp5 rp5-data-1500.txt rp5-find-1500.txt < quit.txt
java Rp5 rp5-data-1800.txt rp5-find-1800.txt < quit.txt
java Rp5 rp5-data-2100.txt rp5-find-2100.txt < quit.txt
java Rp5 rp5-data-2400.txt rp5-find-2400.txt < quit.txt
java Rp5 rp5-data-2700.txt rp5-find-2700.txt < quit.txt
java Rp5 rp5-data-2800.txt rp5-find-2800.txt < quit.txt
java Rp5 rp5-data-2900.txt rp5-find-2900.txt < quit.txt
echo ""
echo "delete ---------------------------------------------------------------"
java Rp5 rp5-data-1500.txt rp5-del-1500.txt < quit.txt
java Rp5 rp5-data-1800.txt rp5-del-1800.txt < quit.txt
java Rp5 rp5-data-2100.txt rp5-del-2100.txt < quit.txt
java Rp5 rp5-data-2400.txt rp5-del-2400.txt < quit.txt
java Rp5 rp5-data-2700.txt rp5-del-2700.txt < quit.txt
java Rp5 rp5-data-2800.txt rp5-del-2800.txt < quit.txt
java Rp5 rp5-data-2900.txt rp5-del-2900.txt < quit.txt
