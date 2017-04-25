#!/bin/bash

# Set the problem name to generate correct file names
PROBLEMNAME="pong"
g++ -std=c++11 -O2 ../submissions/accepted/danielPong.cpp -o /tmp/sol
cp ../submissions/accepted/torstein.py /tmp/sol.py
SOLVER=/tmp/sol.py

rm -rf secret
mkdir secret
echo "grading: custom
grader_flags: sum" > secret/testdata.yaml

function group {
	groupname=$1
	points=$2
	mkdir secret/$groupname
	echo "grading: custom
grader_flags: all $points" > secret/$groupname/testdata.yaml
	ind=0
	echo "Generating group $groupname..."
}

function solve {
	$SOLVER < $1 > ${1%.in}.ans
}

function testcase {
	ind=$((ind+1))
  foo=$(printf "%02d" $ind)
	in=secret/$groupname/$PROBLEMNAME.$groupname.$foo.$1.in
  echo $in
  # echo "$@  $ind  $in"
	python3 generator.py "$@" $ind > $in
	solve $in
}

function repeat {
	rep=$1
	shift
	for i in $(seq 1 $rep); do
		eval $@
	done
}

##


group g1 20
# N <= 30
repeat 1 testcase random -n 2 -k 2 -kp 0 -seed
repeat 1 testcase random -n 3 -k 3 -kp 0 -inc -seed
repeat 1 testcase random -n 10 -k 6 -kp 0 -seed
repeat 1 testcase random -n 10 -k 6 -kp 4 -seed
repeat 1 testcase random -n 10 -k 3 -kp 2 -seed
repeat 1 testcase random -n 10 -k 6 -kp 0 -inc -seed
repeat 1 testcase nosingles -n 30 -k 6 -kp 3 -inc -seed
repeat 1 testcase nosingles -n 30 -k 10 -kp 20 -seed
repeat 1 testcase nosingles -n 30 -k 20 -kp 10 -seed
repeat 1 testcase random -n 30 -k 20 -kp 10 -seed

group g2 30
# N <= 100, k <= 8
repeat 1 testcase random -n 10 -k 8 -kp 1 -seed
repeat 1 testcase random -n 100 -k 8 -kp 4 -seed
repeat 1 testcase random -n 100 -k 3 -kp 2 -seed
repeat 1 testcase random -n 100 -k 6 -kp 0 -seed
repeat 1 testcase random -n 100 -k 6 -kp 0 -inc -seed
repeat 1 testcase random -n 100 -k 8 -kp 90 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 90 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 9 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 8 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 7 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 6 -inc -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 1 -seed
repeat 1 testcase nosingles -n 100 -k 8 -kp 0 -seed
repeat 1 testcase nosingles -n 100 -k 3 -kp 2 -seed

group g3 50
# K <= N <= 3000
repeat 1 testcase random -n 2000 -k 1000 -kp 1000 -seed
repeat 1 testcase random -n 2000 -k 1000 -kp 999 -seed
repeat 1 testcase random -n 2000 -k 1500 -kp 500 -seed
repeat 1 testcase random -n 2000 -k 500 -kp 1500 -seed
repeat 1 testcase random -n 2000 -k 1000 -kp 0 -seed
repeat 1 testcase random -n 2000 -k 1000 -kp 0 -inc -seed
repeat 1 testcase random -n 2000 -k 1800 -kp 1 -seed
repeat 1 testcase random -n 1999 -k 1700 -kp 3 -inc -seed
repeat 1 testcase nosingles -n 2000 -k 800 -kp 999 -seed
repeat 1 testcase nosingles -n 2000 -k 1000 -kp 10 -seed
repeat 1 testcase nosingles -n 2000 -k 1000 -kp 499 -seed
repeat 1 testcase nosingles -n 2000 -k 900 -kp 899 -seed
repeat 1 testcase nosingles -n 1999 -k 10 -kp 1800 -seed
repeat 1 testcase nosingles -n 1999 -k 1500 -kp 200 -seed
repeat 1 testcase nosingles -n 1999 -k 700 -kp 650 -inc -seed
repeat 1 testcase nosingles -n 2000 -k 1000 -kp 800 -seed
repeat 1 testcase nosingles -n 2000 -k 500 -kp 200 -seed
repeat 1 testcase nosingles -n 2000 -k 100 -kp 99 -seed

