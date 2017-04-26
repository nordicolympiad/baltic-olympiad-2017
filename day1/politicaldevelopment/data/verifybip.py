#!/usr/bin/env python3

n, d = map(int, input().split())
graph = []
for i in range(n):
    nums = list(map(int, input().split()[1:]))
    graph.append(nums)
    
color = [0]*n

q = []
for startnode in range(n):
    if color[startnode] != 0: continue
    q.append(startnode)
    color[startnode] = 1
    while len(q) > 0:
        node = q.pop()
        for nbr in graph[node]:
            if color[node] == color[nbr]:
                raise Exception("Not bipartite! {} {}")
            if color[nbr] == 0:
                color[nbr] = -color[node]
                q.append(nbr)

print("Bipartite confirmed!")