#!/bin/bash

# Set the problem name to generate correct file names
PROBLEMNAME="birdrescue"
g++ -std=c++11 -O2 ../submissions/accepted/hacker.cpp -o /tmp/sol
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

function testcase_random {
	ind=$((ind+1))
  foo=$(printf "%02d" $ind)
	in=secret/$groupname/$PROBLEMNAME.$groupname.$foo.in
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

group g1 30
# N <= 10
repeat 4 testcase_random -n 10 -q 10 -mrange 10 -seed
repeat 6 testcase_random -n 10 -q 100000 -seed

group g2 30
# N <= 10
repeat 4 testcase_random -n 10 -q 10 -mrange 11 -seed
repeat 6 testcase_random -n 100000 -q 10 -seed

group g3 40
# No restrictions
repeat 2 testcase_random -n 10 -q 10 -mrange 12 -seed
repeat 2 testcase_random -n 100000 -q 100000 -mrange 100 -seed
repeat 6 testcase_random -n 100000 -q 100000 -seed
