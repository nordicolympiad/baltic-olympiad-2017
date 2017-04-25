#!/usr/bin/env python3
import sys, argparse, random


parser = argparse.ArgumentParser(description="Generator for pong.")
parser.add_argument('tp', metavar='T', type=str,
                    help="Type of graph. One of 'random' 'nosingles'")
parser.add_argument('-n', metavar='N', type=int, default=10,
                    help='Number of nodes in graph')
parser.add_argument('-k', metavar='K', type=int, default=2,
                    help='Size of given solution')
parser.add_argument('-kp', metavar='KP', type=int, default=0,
                    help='Size of other solution')
parser.add_argument('-inc', action='store_true',
                    help='Construct internal incompatability in S')
parser.add_argument('-seed', metavar='S', type=int, default=None,
                    help='seed for generation (optional)')
args = parser.parse_args()
random.seed(args.seed)

# Options
tp, n, k, kp, incomp = args.tp, args.n, args.k, args.kp, args.inc
if k+kp > n: raise Exception("Illegal: k > n k={}, n={}".format(k, n))

# Helper functions
def printGraph(graph, setS):
    print(len(graph), len(setS))
    for row in graph:
        print(" ".join(map(str,row)))
    print(" ".join(map(str,setS)))
    
# Types of graphs:
# - S internally incompatible
# - Random between S an S' (most single points in S' incompatible)
# - no single point in S' is incompatible with S
#   - LIS is random
#   - LIS very long
#   - LIS very short
    

def create():
    graph = [[0] * n for i in range(n)]
    mainorder = list(range(n))
    morderrev = [-1]*n
    random.shuffle(mainorder)
    
    # Initially, player i wins over all later players
    for i in range(n):
        morderrev[mainorder[i]] = i
        for j in range(i+1, n):
            graph[mainorder[i]][mainorder[j]] = 1
            
    # Find sets S and solS
    sets = random.sample(mainorder, k+kp)
    random.shuffle(sets)
    setS = sorted(sets[:k], key=lambda x: morderrev[x])
    solS = sorted(sets[k:], key=lambda x: morderrev[x])
    
    if tp == 'nosingles':
        for i, x in enumerate(solS):
            losses = random.randint(0, k)
            for j, y in enumerate(setS):
                graph[x][y] = 0 if j < losses else 1
                graph[y][x] = 1 if j < losses else 0
                
    elif tp == 'random':
        for x in solS:
            for y in setS:
                win = random.randint(0,1)
                graph[x][y] = 1 if win==1 else 0
                graph[y][x] = 0 if win==1 else 1

    if incomp:
        a, b, c = sorted(random.sample(setS, 3), key=lambda x:morderrev[x])
        graph[a][b] = graph[b][c] = graph[c][a] = 1
        graph[b][a] = graph[c][b] = graph[a][c] = 0
        
    return graph, setS
    
printGraph(*create())