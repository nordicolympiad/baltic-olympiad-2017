#!/usr/bin/env python3
import sys, argparse, random


parser = argparse.ArgumentParser(description="Generator for toll.")
parser.add_argument('-k', metavar='K', type=int, default=2,
                    help='Size of containers')
parser.add_argument('-n', metavar='N', type=int, default=10,
                    help='Number of cities')
parser.add_argument('-m', metavar='M', type=float, default=1.0,
                    help='Number of edges as percentage of all edges')
parser.add_argument('-o', metavar='O', type=int, default=10,
                    help='Number of orders')
parser.add_argument('-seed', metavar='S', type=int, default=None,
                    help='seed for generation (optional)')
parser.add_argument('-forceA', action="store_true", help='Force start at a')
parser.add_argument('-spread', action="store_true", help='All queries far apart')
args = parser.parse_args()
random.seed(args.seed)

# Options
n, k, o, force_a, spread = args.n, args.k, args.o, args.forceA, args.spread
prob = max(0, min(1, args.m))

# Helper functions
def randToll():
    return random.randint(1, 10000)

# Random generator
def generateRandom(n, k, o, force_a, prob):
    edges = []
    for group in range((n // k) - 1):
        for start in range(k):
            for end in range(k):
                sp = group*k + start
                ep = (group+1)*k + end
                assert( 0 <= sp < n)
                assert( 0 <= ep < n)
                edges.append((sp, ep, randToll()))
                    
    for start in range(k):
        for end in range(n % k):
            sp = ((n//k) - 1) * k + start
            ep = (n//k) * k + end
            assert( 0 <= sp < n)
            assert( 0 <= ep < n)
            edges.append((sp, ep, randToll()))
    
    m = int(prob*len(edges))
    print(k, n, m, o)
    random.shuffle(edges)
    for a, b, toll in edges[:m]:
        print(a, b, toll)
        
    for i in range(o):
        if force_a:
            b = random.randint(1, n-1)
            print(0, b)
        elif spread:
            a, b = random.randint(0, n//10), random.randint((9*n)//10, n-1)
            while (a >= b):
                a, b = random.randint(0, n//10), random.randint((9*n)//10, n-1)
            print(a, b)
        else:
            a, b = random.randint(0, n-1), random.randint(0, n-1)
            while (a >= b):
                a, b = random.randint(0, n-1), random.randint(0, n-1)
            print(a, b)

    #print(n, k, o, force_a, prob)
    return

generateRandom(n, k, o, force_a, prob)