#!/bin/bash

# Set the problem name to generate correct file names
PROBLEMNAME="toll"

# Set this if you want to generate answers.
g++ -std=c++11 -O2 ../submissions/accepted/toll.cpp -o /tmp/sol
SOLVER=/tmp/sol

subfolders=(secret/g1 secret/g2 secret/g3 secret/g4)
for i in ${subfolders[@]}
do
    if [ ! -d $i ]
    then
        mkdir $i
    fi
done

echo "grading: custom
grader_flags: all 7" > secret/g1/testdata.yaml
echo "grading: custom
grader_flags: all 10" > secret/g2/testdata.yaml
echo "grading: custom
grader_flags: all 31" > secret/g3/testdata.yaml
echo "grading: custom
grader_flags: all 52" > secret/g4/testdata.yaml

echo "Generating group 1..."
DR="secret/g1"
G="g1"
NE="30000"
NO="29999"
O="3000"
./generator.py -n 10 -k 1 -m 0.7 -o 10 -seed 0 > $DR/$PROBLEMNAME.$G.01.in
./generator.py -n 10 -k 1 -m 0.8 -o 10 -seed 3 > $DR/$PROBLEMNAME.$G.02.in
./generator.py -n 10 -k 1 -m 0.5 -o 10 -seed 4 > $DR/$PROBLEMNAME.$G.03.in

./generator.py -n 1000 -k 1 -m 0.95 -o 1000 -seed 5 > $DR/$PROBLEMNAME.$G.04.in
./generator.py -n 999 -k 1 -m 0.95 -o 1000 -seed 6 > $DR/$PROBLEMNAME.$G.05.in
./generator.py -n 1001 -k 1 -m 0.98 -o 1000 -seed 7 > $DR/$PROBLEMNAME.$G.06.in

./generator.py -n $NE -k 1 -m 0.999 -o $O -seed 9 > $DR/$PROBLEMNAME.$G.07.in
./generator.py -n $NO -k 1 -m 0.95 -o $O -seed 10 > $DR/$PROBLEMNAME.$G.08.in

./generator.py -n $NE -k 1 -m 1.0 -o $O -seed 13 > $DR/$PROBLEMNAME.$G.09.in
./generator.py -n $NO -k 1 -m 0.0 -o $O -seed 16 > $DR/$PROBLEMNAME.$G.10.in

echo "Generating group 2..."
DR="secret/g2"
G="g2"
NE="30000"
NO="29999"
O="3000"
./generator.py -n 10 -k 1 -m 0.8 -o 10 -seed 02 -forceA > $DR/$PROBLEMNAME.$G.01.in
./generator.py -n 10 -k 2 -m 0.8 -o 10 -seed 12 -forceA > $DR/$PROBLEMNAME.$G.02.in
./generator.py -n 10 -k 3 -m 0.8 -o 10 -seed 32 -forceA > $DR/$PROBLEMNAME.$G.03.in
./generator.py -n 10 -k 4 -m 0.8 -o 10 -seed 42 -forceA > $DR/$PROBLEMNAME.$G.04.in
./generator.py -n 10 -k 5 -m 0.8 -o 10 -seed 52 -forceA > $DR/$PROBLEMNAME.$G.05.in
./generator.py -n 1000 -k 1 -m 0.8 -o $O -seed 82 -forceA > $DR/$PROBLEMNAME.$G.06.in
./generator.py -n 1000 -k 2 -m 0.8 -o $O -seed 92 -forceA > $DR/$PROBLEMNAME.$G.07.in

./generator.py -n $NE -k 1 -m 0.999 -o $O -seed 102 -forceA > $DR/$PROBLEMNAME.$G.08.in
./generator.py -n $NE -k 2 -m 0.95 -o $O -seed 112 -forceA > $DR/$PROBLEMNAME.$G.09.in
./generator.py -n $NE -k 3 -m 0.9 -o $O -seed 122 -forceA > $DR/$PROBLEMNAME.$G.10.in
./generator.py -n $NO -k 4 -m 0.85 -o $O -seed 132 -forceA > $DR/$PROBLEMNAME.$G.11.in
./generator.py -n $NE -k 5 -m 0.99 -o $O -seed 142 -forceA > $DR/$PROBLEMNAME.$G.12.in

./generator.py -n $NO -k 5 -m 1.0 -o $O -seed 172 -forceA > $DR/$PROBLEMNAME.$G.13.in
./generator.py -n $NE -k 3 -m 1.0 -o $O -seed 182 -forceA > $DR/$PROBLEMNAME.$G.14.in
./generator.py -n $NE -k 5 -m 0.5 -o $O -seed 192 -forceA > $DR/$PROBLEMNAME.$G.15.in
./generator.py -n $NO -k 5 -m 0.5 -o $O -seed 202 -forceA > $DR/$PROBLEMNAME.$G.16.in


echo "Generating group 3..."
DR="secret/g3"
G="g3"
NE="30000"
NO="29999"
O="100"
./generator.py -n 10 -k 1 -m 0.8 -o 10 -seed 23  > $DR/$PROBLEMNAME.$G.01.in
./generator.py -n 10 -k 2 -m 0.8 -o 10 -seed 13  > $DR/$PROBLEMNAME.$G.02.in
./generator.py -n 10 -k 3 -m 0.8 -o 10 -seed 33  > $DR/$PROBLEMNAME.$G.03.in
./generator.py -n 10 -k 4 -m 0.8 -o 10 -seed 43  > $DR/$PROBLEMNAME.$G.04.in
./generator.py -n 10 -k 5 -m 0.8 -o 10 -seed 53  > $DR/$PROBLEMNAME.$G.05.in
./generator.py -n 1000 -k 1 -m 0.8 -o $O -seed 83  > $DR/$PROBLEMNAME.$G.06.in
./generator.py -n 1000 -k 2 -m 0.8 -o $O -seed 93  > $DR/$PROBLEMNAME.$G.07.in

./generator.py -n $NE  -k 1 -m 0.999 -o $O -seed 103  > $DR/$PROBLEMNAME.$G.08.in
./generator.py -n $NE  -k 2 -m 0.95 -o $O -seed 113  > $DR/$PROBLEMNAME.$G.09.in
./generator.py -n $NE  -k 3 -m 0.9 -o $O -seed 123  > $DR/$PROBLEMNAME.$G.10.in
./generator.py -n $NO -k 4 -m 0.85 -o $O -seed 133  > $DR/$PROBLEMNAME.$G.11.in
./generator.py -n $NE  -k 5 -m 0.99 -o $O -seed 143  > $DR/$PROBLEMNAME.$G.12.in

./generator.py -n $NO -k 5 -m 1.0 -o $O -seed 173  > $DR/$PROBLEMNAME.$G.13.in
./generator.py -n $NE  -k 3 -m 1.0 -o $O -seed 183 > $DR/$PROBLEMNAME.$G.14.in
./generator.py -n $NE  -k 5 -m 0.5 -o $O -seed 193  > $DR/$PROBLEMNAME.$G.15.in
./generator.py -n $NO -k 5 -m 0.5 -o $O -seed 203  > $DR/$PROBLEMNAME.$G.16.in

echo "Generating group 4..."
DR="secret/g4"
G="g4"
NE="30000"
NO="29999"
O="3000"

./generator.py -n $NE -k 1 -m 0.999 -o $O -seed 104  > $DR/$PROBLEMNAME.$G.01.in
./generator.py -n $NE -k 2 -m 0.95 -o $O -seed 114  > $DR/$PROBLEMNAME.$G.02.in
./generator.py -n $NO -k 4 -m 0.85 -o $O -seed 134  > $DR/$PROBLEMNAME.$G.03.in
./generator.py -n $NE -k 5 -m 0.99 -o $O -seed 144  > $DR/$PROBLEMNAME.$G.04.in
./generator.py -n $NE -k 3 -m 1.0 -o $O -seed 184  > $DR/$PROBLEMNAME.$G.05.in
./generator.py -n $NE -k 5 -m 0.5 -o $O -seed 194  > $DR/$PROBLEMNAME.$G.06.in



# generate solutions for all files
if [[ ! -z $SOLVER ]]
then
    for i in ${subfolders[@]}
    do
        for f in $i/*.in
        do
            echo "solving $f"
            $SOLVER < $f > ${f%???}.ans
        done
    done
fi
