#!/usr/bin/env python3
import sys, math
EPS = 1e-7

def toast(n, d, t):
    degrees = [(math.cos(i*2*math.pi/n), math.sin(i*2*math.pi/n))
                for i in range(1, n)]
    maxreachsq = (2*d)**2
    
    def distsq(p1, p2): return (p1[0]-p2[0])**2 + (p1[1]-p2[1])**2
    
    def countToasts(r):
        count = 0
        p = (r, 0)
        for x2, y2 in degrees:
            p2 = (x2*r, y2*r)
            if distsq(p, p2) <= maxreachsq:
                count += 1
        return (n*count)//2
        
    def bisearchmin(lb, ub):
        # Return min radius s.t. # toast <= t
        if lb+EPS > ub: return lb
        r = (lb + ub)/2
        if countToasts(r) > t:
            return bisearchmin(r, ub)
        return bisearchmin(lb, r)
        
    def bisearchmax(lb, ub):
        # Return max radius s.t. # toast >= t
        if lb+EPS > ub: return lb
        r = (lb + ub)/2
        if countToasts(r) < t: return bisearchmax(lb, r)
        return bisearchmax(r, ub)
        
    return bisearchmin(0, 2*n*d), bisearchmax(0, 2*n*d)
    
    
    
print(*toast(*map(int, sys.stdin.readline().split())))