#!/usr/bin/python
from sys import stdin
import sys

n, k = map(int, stdin.readline().split())
adjlist = []
for i in range(n):
    adjlist.append(list(map(int, stdin.readline().split())))
    
for i in range(n):
    for j in range(n):
        if i == j:
            assert adjlist[i][j] == 0, "not 0 on diagonal"
        else:
            assert adjlist[i][j] != adjlist[j][i], "not antisymmetric"

# Nothing to report
sys.exit(42)
