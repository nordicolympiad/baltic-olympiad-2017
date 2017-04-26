#!/usr/bin/env python3
import sys, argparse, random


parser = argparse.ArgumentParser(description="Random generator for political development. Generates a d-degenerate graph by randomly picking less than d neighbors of already constructed graph. Resulting graph is shuffled to hide ordering.")
parser.add_argument('graphtype', metavar='GT', type=str, help="Graph type. Use one of 'periferal' 'clique' 'indepset' 'bip' 'star' 'doublestar' 'cycle' 'distcycles' 'subdiv' 'bipsubdiv' 'cliquesperiferal' 'starsjoined' 'cliquesjoined' 'maxdeg' 'maxdegsmallerK' 'maxdegbigK' ")
parser.add_argument('-n', metavar='N', type=int, default=10,
                    help='Number of nodes')
parser.add_argument('-d', metavar='D', type=int, default=3,
                    help='Degeneracy')
parser.add_argument('-mindeg', metavar='MD', type=int, default=1,
                    help='Minimum degree of graph')
parser.add_argument('-density', metavar='DS', type=float, default=0.5,
                    help='likelyhood of possible edges being created')
parser.add_argument('-seed', metavar='S', type=int, default=None,
                    help='seed for generation (optional)')
args = parser.parse_args()
random.seed(args.seed)

# Options
n, d, mindeg, prob = args.n, args.d, args.mindeg, min(1, max(0, args.density))

# Helper functions
def printShuffled(graph, d):
    ## Shuffle graph
    shuffle = list(range(len(graph)))
    random.shuffle(shuffle)
    
    graph_s = [[] for i in range(len(graph))]
    for i in range(len(graph)):
        for nb in graph[i]:
            graph_s[shuffle[i]].append(shuffle[nb])
            
    ## Print graph
    print(len(graph_s), min(d+1, len(graph_s)))
    for i in range(len(graph_s)):
        if len(graph_s[i]) == 0:
            print(0)
        else:
            print(len(graph_s[i]), " ".join(map(str, graph_s[i])))
        
def disjointUnion(graph_a, graph_b):
    # Create the disjoint union of two graphs
    offset = len(graph_a)
    for nbrs in graph_b:
        graph_a.append([])
        for nb in nbrs:
            graph_a[-1].append(nb+offset)
    return graph_a
    
def joinwithdegD(graph_a, graph_b, numjoins, d):
    if d < 2:
        raise Exception("Cannot maintain d-degenracy for d < 2")
    len_a, len_b = len(graph_a), len(graph_b)
    conn_a, conn_b = min(len_a, d//2), min(len_b, d-d//2)
    offset = len_a
    
    graph = disjointUnion(graph_a, graph_b)
    offset2 = len(graph)
    
    for newnode in range(offset2, offset2+numjoins):
        graph.append([])
    
        nbhood = random.sample(range(len_a), conn_a)
        nbhood.extend(random.sample(range(offset, offset + len_b), conn_b))
        for i in nbhood:
            graph[newnode].append(i)
            graph[i].append(newnode)
    return graph
            
def clique(k):
    # Create a clique of size k
    graph = [[] for i in range(k)]
    for i in range(k):
        graph[i].extend(list(range(i)) + list(range(i+1, k)))
    return graph
    
def cycle(k):
    # Create a cycle of length k
    graph = [[] for i in range(k)]
    for i in range(k):
        graph[i].append((i+1)%k)
        graph[(i+1)%k].append(i)
    return graph
    
def star(k):
    # Create star with k nodes, whereof k-1 petals
    graph = [[0] for i in range(k)]
    graph[0] = list(range(1,k))
    return graph
    
def doublestars(n):
    # Create stars with root(n) substars, each of which have root(n) petals
    graph = [[] for i in range(n)]
    root = 0
    substars = range(1, 1+int(n**0.5))
    leaves = range(1+int(n**0.5), n)
    
    for sstar in substars:
        graph[0].append(sstar)
        graph[sstar].append(0)
        
    sstar = 0
    for leaf in leaves:
        graph[leaf].append(sstar + 1)
        graph[sstar + 1].append(leaf)
        sstar = (sstar + 1) % int(n**0.5)
    return graph

def indep_set(k):
    # Create independent set of size k
    return [[] for i in range(k)]
    
def kuniversal(n, k):
    graph = indep_set(n)
    for i in range(n-k):
        for j in range(n-k, n):
            graph[i].append(j)
            graph[j].append(i)
    return graph
    
def subdivideGraph(graph, percentage):
    # Subdivide given percentage of edges of graph.
    edges = set()
    for a in range(len(graph)):
        for b in graph[a]:
            edges.add((min(a, b), max(a, b)))
    edges = list(edges)
    random.shuffle(edges)
    
    divedges = edges[:int(round(len(edges) * percentage))]
    keepedgs = edges[int(round(len(edges) * percentage)):]
    
    offset = len(graph)
    subdiv = [[] for i in range(len(graph) + len(divedges))]
    for i, (a, b) in enumerate(divedges):
        subdiv[a].append(offset + i)
        subdiv[b].append(offset + i)
        subdiv[offset + i].extend([a, b])
        
    for a, b in keepedgs:
        subdiv[a].append(b)
        subdiv[b].append(a)
    return subdiv
    
def bipGenerator(n, d):
    # Generate random bipartite graph of degeneracy d and min degree d
    graph = [[] for i in range(n)]
    # Odds on one part, evens in other part
    for i in range(n):
        newnbrs_c = max(0, d - len(graph[i]))
        newnbrs = random.sample(range((1 if (i%2) == 0 else 0), n, 2), newnbrs_c)
        for nb in newnbrs:
            while nb in graph[i]:
                nb = random.choice(range(nb%2, n, 2))
            graph[i].append(nb)
            graph[nb].append(i)
    return graph

def randomGraph(k, percentage):
    # Return graph of size k where percentage*(k(k-1)/2) edges are randomly
    # chosen.
    graph = [[] for i in range(k)]
    edges = []
    for i in range(k):
        for j in range(i + 1, k):
            edges.append((i, j))
    random.shuffle(edges)
    for a, b in edges[:int(round(percentage * len(edges)))]:
        graph[a].append(b)
        graph[b].append(a)
    return graph
    
def cliqueleaves(n, d):
    ksize = d-1
    graph = indep_set(1)
    n -= 1
    while n > 0:
        ks = min(n, ksize)
        cliq = clique(ks)
        graph = disjointUnion(graph, cliq)
        
    
def periferalAttachment(graph, newnodes, maxdeg, mindeg):
    if mindeg > maxdeg:
        raise Exception("Can't have mindeg bigger than maxdeg")
    # If graph has deg maxdeg, new graph will also have deg maxdeg
        
    orglen = len(graph)
    for node in range(orglen, orglen+newnodes):
        graph.append([])
        nbrs_count = random.randint(min(mindeg, node), min(maxdeg, node))
        nbrs = random.sample(range(node), nbrs_count)
        for nb in nbrs:
            graph[node].append(nb)
            graph[nb].append(node)
    return graph
    
def maxdegGraph(graph, n, maxdeg):
    for i in range(len(graph), n):
        graph.append([])
        
    for i in range(n):
        for j in range(len(graph[i]), maxdeg):
            for attempt in range(10):
                nb = random.randint(0, n-1)
                if nb != i and len(graph[nb]) < maxdeg and nb not in graph[i]:
                    graph[i].append(nb)
                    graph[nb].append(i)
                    break

    available = set()
    for i in range(n):
        if len(graph[i]) < maxdeg:
            available.add(i)
        
    while len(available) > maxdeg+2:
        a, b = random.sample(available, 2)
        if a not in graph[b]:
            graph[a].append(b)
            graph[b].append(a)
        
        if len(graph[a]) >= maxdeg:
            available.remove(a)
        if len(graph[b]) >= maxdeg:
            available.remove(b)
    
    return graph
        


'periferal' 'clique' 'indepset' 'bip' 'star' 'doublestar' 'cycle' 'distcycles' 'subdiv' 'bipsubdiv' 'cliquesperiferal' 'starsjoined'
graphtype = args.graphtype

if graphtype == 'periferal':
    graph = periferalAttachment([], n, d, mindeg)

elif graphtype == 'bigcliques':
    graph = clique(d+1)
    nodesleft = n - d - 1
    while nodesleft > 0:
        graph2 = clique(min(nodesleft, d))
        graph = disjointUnion(graph, graph2)
        nodesleft -= d
        
elif graphtype == 'smallercliques':
    graph = clique(d)
    nodesleft = n - d
    while nodesleft > 0:
        graph2 = clique(min(nodesleft, d-1))
        graph = disjointUnion(graph, graph2)
        nodesleft -= (d-1)
    
elif graphtype == 'indepset':
    graph = indep_set(n)
    
elif graphtype == 'bip':
    graph = bipGenerator(n, d)
    
elif graphtype == 'star':
    graph = star(n)
    
elif graphtype == 'doublestar':
    graph = doublestars(n)
    
elif graphtype == 'cycle':
    graph = cycle(n)
    
elif graphtype == 'distcycles':
    graph = distjointUnion(cycle(n//2), cycle(n-n//2))
    
elif graphtype == 'subdiv':
    graph = subdivideGraph(randomGraph(int(n**0.5)-1, prob), 1.0)
    
elif graphtype == 'bipsubdiv':
    fn = n // 2
    graph = subdivideGraph(bipGenerator(fn, d), fn/(fn**2))
    
elif graphtype == 'bigcliquesperiferal':
    graph = clique(d+1)
    nodesleft = n - d - 1
    while nodesleft > 3*n // 4:
        graph2 = clique(min(nodesleft, d))
        graph = disjointUnion(graph, graph2)
        nodesleft -= d
    graph = periferalAttachment(graph, nodesleft, d, mindeg)

elif graphtype == 'smallercliquesperiferal':
    graph = clique(d)
    nodesleft = n - d
    while nodesleft > 3*n // 4:
        graph2 = clique(min(nodesleft, d-1))
        graph = disjointUnion(graph, graph2)
        nodesleft -= d - 1
    graph = periferalAttachment(graph, nodesleft, d, mindeg)
    
elif graphtype == 'starsjoined':
    graph = joinwithdegD(star(n//3), star(n//3), n-(2*(n//3)), d)
    
elif graphtype == 'maxdeg':
    graph = maxdegGraph([], n, d)
    
elif graphtype == 'maxdegbigK':
    graph = maxdegGraph(clique(d), n, d)
    
elif graphtype == 'maxdegsmallerK':
    graph = maxdegGraph(clique(d-1), n, d)
    
elif graphtype == 'kuniversal':
    graph = kuniversal(n, d)
    
elif graphtype == 'cliqueleaves':
    graph = cliqueleaves(n, d)
    
    
printShuffled(graph, d)
