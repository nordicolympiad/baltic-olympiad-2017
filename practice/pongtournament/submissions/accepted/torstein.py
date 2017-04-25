#!/usr/bin/env python3
import sys
INF = float('inf')

def pongtournament():
    # Step 0: Read input O(n**2)
    n, k = map(int, sys.stdin.readline().split())
    graph = []
    for i in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))
    setS = list(map(int, sys.stdin.readline().split()))
    isS = [False] * n
    for x in setS: isS[x] = True
    
    # Step 1: Find ranking/order of disqualified group S. O(n**2)
    # If no such order, say impossible.
    rank = [-1] * n
    if not findRank(graph, rank, isS, True):
        print("impossible")
        return
    setS.sort(key=lambda x: rank[x])
    
    # Step 2: Find the ranking/order of the non-disqualified group. O(n**2)
    findRank(graph, rank, isS, False)
    
    # Step 3: For each non-disqualified, find the first in S they beat (group)
    # If there is someone they lost to later in order of S, put in S'. O(n**2)
    setZ = []
    group = [-1] * n
    for nonS in range(n):
        if isS[nonS]: continue
        firstBeatRank = INF
        prevwaswin = False
        for inS in setS:
            if graph[nonS][inS] and not prevwaswin:
                firstBeatRank = rank[inS]
                prevwaswin = True
            elif not graph[nonS][inS] and prevwaswin:
                firstBeatRank = -1
                setZ.append(nonS)
                break
        group[nonS] = firstBeatRank
        
    isZ = [False] * n
    for x in setZ: isZ[x] = True
                
    # Step 4: Find the longest non-decreasing subsequence among G-S-S', where
    # order is defined by ranking, and value defined by ranking of best in S they beat (group) O(n**2)
    data = []
    for i in range(n):
        if isZ[i] or isS[i]: continue
        data.append((rank[i], group[i], i))
    
    setZ.extend(longestnondecreasingsubsequence(data))
    
    if len(setZ) >= len(setS):
        print("impossible")
    else:
        print(len(setZ))
        #print(" ".join(map(str,setZ)))

    
def findRank(graph, rank, prop, criteria):
    # Return False if no rank was possible. Otherwise, found rank is 
    # stored in rank
    data = []
    for i in range(len(graph)):
        if prop[i] != criteria: continue
        wins = 0
        for j in range(len(graph)):
            if prop[j] != criteria: continue
            wins += graph[i][j]
        data.append((wins, i))
        
    data.sort(key=lambda x: -x[0])
    
    if len(data) == 0:
        return True
    if data[0][0] != len(data) - 1:
        return False

    rank[data[0][1]] = 0
            
    prevwins = len(data) - 1
    for prevrank, (wins, i) in enumerate(data[1:]):
        if wins != prevwins - 1:
            return False
        prevwins = wins
        rank[i] = prevrank + 1
        
    return True
    
def longestnondecreasingsubsequence(data):
    # Data contains triples (a, v, x), where a defines order, v defines
    # value, and x defines the element. Returns complement of a list of
    # elements found in longest subsequence
    if len(data) <= 1:
        return []
        
    data.sort()
    longestpath = [(0, -1)]*len(data)
    verybestend, verybestlen = 0, 0
    for i, (a, v, x) in enumerate(data):
        bestlen, bestpar = 0, -1
        for j in range(i):
            if data[j][1] <= v:
                if bestlen < longestpath[j][0] + 1:
                    bestlen = longestpath[j][0] + 1
                    bestpar = j
        longestpath[i] = (bestlen, bestpar)
        if bestlen > verybestlen:
            verybestlen = bestlen
            verybestend = i
                
    inlp = [False] * len(data)
    while verybestend >= 0:
        inlp[verybestend] = True
        verybestend = longestpath[verybestend][1]
    
    complement = []
    for i, (a, v, x) in enumerate(data):
        if not inlp[i]:
            complement.append(x)
        
    return complement
    
    
pongtournament()