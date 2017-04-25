#!/usr/bin/env python3
import sys, argparse, random


parser = argparse.ArgumentParser(description="Generator for hacker.")
parser.add_argument('-n', metavar='N', type=int, default=10,
                    help='Number of buildings')
parser.add_argument('-q', metavar='Q', type=int, default=10,
                    help='Number of queries')
parser.add_argument('-mrange', metavar='R', type=int, default=int(1e6),
                    help='Max coordinate')
parser.add_argument('-seed', metavar='S', type=int, default=None,
                    help='seed for generation (optional)')
args = parser.parse_args()
random.seed(args.seed)

# Options
n, q, maxrange = args.n, args.q, args.mrange
# raise Exception("{} {} {}\n".format(n, q, maxrange))

# Helper functions
def randomCoord():
    return random.randint(0, maxrange), random.randint(0, maxrange)
    
def randomDist():
    return random.randint(0, 2*maxrange)

# Random generator
def generateRandom(n, q):
    print(n, q)
    print(*randomCoord())
    for i in range(n):
        (x1, y1), (x2, y2) = randomCoord(), randomCoord()
        print(x1, y1, x2, y2)
        
    for i in range(q):
        print(randomDist())
    return

generateRandom(n, q)