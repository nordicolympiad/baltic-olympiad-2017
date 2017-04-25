#!/usr/bin/env python3
import sys, argparse, random


parser = argparse.ArgumentParser(description="Generator for pongtournament. Graphs generated will have both partitions S and G-S acyclic, but edges between partitions are generated randomly.")
parser.add_argument('n', metavar='N', type=int, nargs=1,
                    help='# of nodes in tournament')
parser.add_argument('k', metavar='K', type=int, nargs=1,
                    help='size of S (fvs)')
parser.add_argument('seed', metavar='S', nargs="?", type=int, default=None,
                    help='seed for generation (optional)')
args = parser.parse_args()
random.seed(args.seed)

def generateRandomGraph(n, k):
    if k > n: raise Exception("Illegal: k > n k={}, n={}".format(k, n))
    graph = [[0] * n for i in range(n)]
    order = list(range(n))
    random.shuffle(order)
    setS = order[:k]
    notS = order[k:]
    
    def makeorder(aset, agraph):
        for i, a in enumerate(aset):
            for j in range(i+1, len(aset)):
                agraph[a][aset[j]] = 1
    
    makeorder(setS, graph)
    makeorder(notS, graph)
    
    for a in setS:
        for b in notS:
            if random.randint(0, 1) == 0:
                graph[a][b] = 1
            else:
                graph[b][a] = 1
    
    return graph, sorted(setS)
    
def printGraph(graph, setS):
    print(len(graph), len(setS))
    for row in graph:
        print(" ".join(map(str,row)))
    print(" ".join(map(str,setS)))


graph, setS = generateRandomGraph(*args.n, *args.k)
printGraph(graph, setS)