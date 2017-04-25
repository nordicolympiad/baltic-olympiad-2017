#!/usr/bin/env python2
import sys

def hacker():
    n, q = map(int, sys.stdin.readline().split())
    x, y = map(int, sys.stdin.readline().split())
    distances = [0]*(2*10**6 + 10)
    
    for i in range(n):
        xa, ya, xb, yb = map(int, sys.stdin.readline().split())
        l, r, u, d = min(xa, xb), max(xa, xb), max(ya, yb), min(ya, yb)
        mind = min(abs(x-l)+abs(y-u), abs(x-l)+abs(y-d), abs(x-r)+abs(y-u), abs(x-r)+abs(y-d))
        maxd = max(abs(x-l)+abs(y-u), abs(x-l)+abs(y-d), abs(x-r)+abs(y-u), abs(x-r)+abs(y-d))
        if l <= x <= r and u >= y >= d:
            mind = 0
        elif l <= x <= r:
            mind = min(abs(y-u), abs(y-d))
        elif u >= y >= d:
            mind = min(abs(x-r), abs(x-l))
        distances[mind] += 1
        distances[maxd+1] -= 1
        
    presum = [0]
    for num in distances:
        presum.append(presum[-1] + num)
        
    for query in range(q):
        print(presum[1+int(sys.stdin.readline().strip())])
        
    return
    
hacker()
