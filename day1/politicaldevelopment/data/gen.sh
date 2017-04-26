#!/bin/bash

# Set the problem name to generate correct file names
PROBLEMNAME="poldev"
#g++ -std=c++11 -O2 ../submissions/accepted/hacker.cpp -o /tmp/sol
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

## Ideas to generate:
##  - Graphs with all subdivided edges (k == 3, clique == 2)
##  - Graphs of bounded tree-width
##  - Chordal graphs with maxclique (clique==tw)
##  - Random with forced some first clique
##  - Disjoint graphs
##  - Cycles (k==3, clique==2)
##  - Empty graph
##  - Bipartite graphs (clique == 2)


group g1 4
# Find edge
# N <= 5000, k == 2 - empty graph, graph with single edge, forests
repeat 2 testcase periferal -n 8 -d 1 -seed
repeat 1 testcase periferal -n 5000 -d 1 -seed
repeat 2 testcase star -n 5000 -d 1 -seed
repeat 2 testcase doublestar -n 5000 -d 1 -seed
repeat 1 testcase indepset -n 5000 -d 1 -seed
repeat 1 testcase indepset -n 1 -d 1 -seed
repeat 1 testcase bigcliques -n 5000 -d 1 -seed

group g2 12
# Find triangle in nm
# N <= 5000, k <= 3 - same + cycles, 2trees, tw2, mockforest, subdivided graphs
repeat 1 testcase star -n 5000 -d 1 -seed
repeat 1 testcase star -n 5000 -d 2 -seed
repeat 2 testcase periferal -n 5 -d 2 -mindeg 0 -seed
repeat 1 testcase doublestar -n 5000 -d 2 -seed
repeat 1 testcase indepset -n 5000 -d 2 -seed
repeat 1 testcase bigcliques -n 5000 -d 2 -seed
repeat 1 testcase smallercliques -n 5000 -d 2 -seed
repeat 1 testcase bigcliquesperiferal -n 5000 -d 2 -seed
repeat 1 testcase smallercliquesperiferal -n 5000 -d 2 -seed
repeat 1 testcase cycle -n 5000 -d 2 -seed
repeat 1 testcase starsjoined -n 5000 -d 2 -seed
repeat 2 testcase bip -n 5000 -d 2 -seed
repeat 2 testcase bipsubdiv -n 5000 -d 2 -seed
repeat 2 testcase subdiv -n 5000 -d 2 -seed
repeat 2 testcase periferal -n 5000 -d 2 -mindeg 2 -seed

group g3 23
# Max-degree is at most 10
# N = 50000
repeat 1 testcase indepset -n 5000 -d 3 -seed
repeat 2 testcase maxdeg -n 20 -d 5 -seed
repeat 2 testcase maxdegbigK -n 20 -d 5 -seed
repeat 2 testcase maxdegsmallerK -n 20 -d 5 -seed
repeat 1 testcase maxdeg -n 50000 -d 9 -seed
repeat 1 testcase maxdegbigK -n 50000 -d 9 -seed
repeat 1 testcase maxdegsmallerK -n 50000 -d 9 -seed

group g4 38
# Allow n2
# N <= 5000 - + bounded tw, clique-trees, d-degen bipartite graphs
repeat 1 testcase periferal -n 5000 -d 3 -seed
repeat 1 testcase periferal -n 5000 -d 5 -mindeg 3 -seed
repeat 1 testcase periferal -n 5000 -d 7 -seed
repeat 1 testcase periferal -n 5000 -d 8 -mindeg 8 -seed
repeat 1 testcase periferal -n 5000 -d 9 -mindeg 9 -seed
repeat 1 testcase star -n 5000 -d 1 -seed
repeat 1 testcase kuniversal -n 5000 -d 9 -seed
repeat 1 testcase doublestar -n 5000 -d 9 -seed
repeat 1 testcase indepset -n 5000 -d 5 -seed
repeat 1 testcase bigcliques -n 5000 -d 5 -seed
repeat 1 testcase bigcliques -n 5000 -d 9 -seed
repeat 1 testcase smallercliques -n 5000 -d 6 -seed
repeat 1 testcase smallercliques -n 5000 -d 9 -seed
repeat 1 testcase bigcliquesperiferal -n 5000 -d 3 -seed
repeat 1 testcase bigcliquesperiferal -n 5000 -d 9 -seed
repeat 1 testcase smallercliquesperiferal -n 5000 -d 7 -seed
repeat 1 testcase smallercliquesperiferal -n 5000 -d 9 -seed
repeat 1 testcase cycle -n 5000 -d 7 -seed
repeat 1 testcase starsjoined -n 5000 -d 6 -seed
repeat 1 testcase bip -n 5000 -d 9 -seed
repeat 1 testcase bipsubdiv -n 5000 -d 9 -seed
repeat 1 testcase subdiv -n 5000 -d 5 -seed
repeat 1 testcase maxdegsmallerK -n 5000 -d 9 -seed

group g5 23
# Max 2^d *d^2 * n
# N = 50000, k <= 5
repeat 1 testcase periferal -n 50000 -d 3 -mindeg 3 -seed
repeat 1 testcase periferal -n 50000 -d 4 -mindeg 2 -seed
repeat 1 testcase star -n 50000 -d 1 -seed
repeat 1 testcase kuniversal -n 50000 -d 4 -seed
repeat 1 testcase doublestar -n 50000 -d 4 -seed
repeat 1 testcase indepset -n 50000 -d 3 -seed
repeat 1 testcase bigcliques -n 50000 -d 2 -seed
repeat 1 testcase bigcliques -n 50000 -d 4 -seed
repeat 1 testcase smallercliques -n 50000 -d 3 -seed
repeat 1 testcase smallercliques -n 50000 -d 4 -seed
repeat 1 testcase bigcliquesperiferal -n 50000 -d 3 -seed
repeat 1 testcase bigcliquesperiferal -n 50000 -d 4 -seed
repeat 1 testcase smallercliquesperiferal -n 50000 -d 4 -seed
repeat 1 testcase cycle -n 50000 -d 3 -seed
repeat 1 testcase starsjoined -n 50000 -d 3 -seed
repeat 1 testcase bip -n 50000 -d 4 -seed
repeat 1 testcase bipsubdiv -n 50000 -d 3 -seed
repeat 1 testcase bipsubdiv -n 50000 -d 4 -seed
repeat 1 testcase subdiv -n 50000 -d 4 -seed
repeat 1 testcase maxdegsmallerK -n 50000 -d 4 -seed

