#include<stdio.h>
#include<vector>
#include<iostream>
#define llint long long 

using namespace std;

int mindist(int pos, int leftwall, int rightwall) {
	if(leftwall > rightwall) return mindist(pos, rightwall, leftwall);
	if(leftwall <= pos && pos <= rightwall) return 0;
	return pos < leftwall ? leftwall-pos : pos-rightwall;
}

int maxdist(int pos, int leftwall, int rightwall) {
	if(leftwall > rightwall) return maxdist(pos, rightwall, leftwall);
	if(pos < leftwall) return rightwall - pos;
	if(pos > rightwall) return pos - leftwall;
	return (rightwall - pos > pos - leftwall) ? rightwall - pos : pos - leftwall;
}

int main() {
	int n,q,xa,ya,d;
	scanf("%d%d",&n,&q);
	scanf("%d%d",&xa,&ya);
	
	vector<int> A(2000005, 0);
	
	for(int i = 0; i < n; ++i) {
		int x1,x2,y1,y2;
		scanf("%d%d%d%d",&x1,&y1,&x2,&y2);
		A[mindist(xa, x1, x2) + mindist(ya, y1, y2)]++;
		A[maxdist(xa, x1, x2) + maxdist(ya, y1, y2) + 1]--;
	}
	
	for(int i = 1; i < 2000005; ++i) A[i] += A[i-1];
	
	for(int i = 0; i < q; ++i) {
		scanf("%d", &d);
		printf("%d\n", A[d]);
	}
	return 0;
}