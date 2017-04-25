#include<iostream>
#include<cassert>
#include<algorithm>
#include<vector>
#include<cstdio>

using namespace std;

vector<vector<bool> > G;
vector<int> U; // undeletable solution
vector<int> R; // rest
vector<int> CR; // subset of R that is consistent
vector<int> LIS; // the set of vertices in R to NOT be deleted. 
vector<int> DS; // the set of vertices in R to be deleted. 
vector<int> indegUU; // indegrees from U to U (nonzero for U vertices)
vector<int> indegUR; // indegrees from U to R (nonzero for R vertices)
vector<int> indegRR; // indegrees from R to R (nonzero for R vertices)
int n, k;

bool debug = false; // debug flag

void readInput() {
	scanf("%d%d", &n, &k);
//    \item{Group 1, \textbf{20 points}} $n \leq 30$ % n-k choose k * n**2
//    \item{Group 2, \textbf{30 points}} $k \leq 8$, $n \leq 100$ %3**k * n**2
assert (n <= 30);

	G = vector<vector<bool> > (n, vector<bool> (n, false));
	for(int i = 0; i < n; ++i) {
		for(int j = 0; j < n; ++j) {
			int tmp; 
			scanf("%d", &tmp); 
			G[i][j] = tmp;
	}}
	
	U = vector<int> (k, 0);
	for(int i = 0; i < k; ++i) scanf("%d", &(U[i]));
}

void makeR() {
	vector<bool> used(n,0);
	for(int i = 0; i < U.size(); ++i) used[U[i]] = true;
	for(int i = 0; i < n; ++i) if(!used[i]) R.push_back(i);
}

void computeIndeg() {
	indegUU = vector<int> (n, 0);
	for(int i = 0; i < U.size(); ++i) {
		for(int j = 0; j < U.size(); ++j) {
			indegUU[U[i]] += G[U[j]][U[i]];
	}}		

	indegUR = vector<int> (n, 0);
	for(int i = 0; i < R.size(); ++i) {
		for(int j = 0; j < U.size(); ++j) {
			indegUR[R[i]] += G[U[j]][R[i]];
	}}		

	indegRR = vector<int> (n, 0); 
	for(int i = 0; i < R.size(); ++i) {
		for(int j = 0; j < R.size(); ++j) {
			indegRR[R[i]] += G[R[j]][R[i]];
	}}		
}

// verify that a set is topologically sorted
bool verifyTopoSort(vector<int> V) {
	// check that G[V] is topo sorted
	for(int i = 0; i < V.size(); ++i) {
		for(int j = i+1; j < V.size(); ++j) {
			if(!G[V[i]][V[j]]) return false;
	}}
	return true;
}

bool UUcomp(int i, int j) {return indegUU[i] < indegUU[j];}
bool RRcomp(int i, int j) {return indegRR[i] < indegRR[j];}
bool URcomp(int i, int j) {return indegUR[i] < indegUR[j];}

// topologically sorts G[U] and G[R] and returns false of G[U] is not a dag
bool sortAndVerifyU() {
	sort(U.begin(), U.end(), UUcomp);
	sort(R.begin(), R.end(), RRcomp);
	
	if(!verifyTopoSort(U)) return false;
	
	// check that G[R] is topo sorted, not necessary strictly speaking
	// remove after debug.
	if(!verifyTopoSort(R)) {
		cerr << "R is not topologically sorted!" << endl;
		return false;
	}
	
	return true;
}

void computeCR() {
	for(int i = 0; i < R.size(); ++i) {
		bool success = true;
		for(int j = 0; j < indegUR[R[i]]; ++j) {success &= G[U[j]][R[i]];}
		if(success) CR.push_back(R[i]);
	}
}

vector<int> indegreesUR (vector<int> rset) {
	vector<int> ans;
	for(int i = 0; i < rset.size(); ++i) ans.push_back(indegUR[rset[i]]);
	return ans;
}

// longest increasing subsequence
void computeLIS() {
	if(CR.empty()) return;
	
	vector<int> val = indegreesUR(CR);
	int N = val.size();

	vector<int> T(N, 1);
	vector<int> backPointer;
	for(int i = 0; i < N; ++i) backPointer.push_back(i);
	
	int pos = 0;
	for(int i = 1; i < N; ++i) {
		for(int j = i-1; j >= 0; --j) {
			if(val[j] <= val[i] && T[j] + 1 > T[i]) {
				T[i] = T[j] + 1;
				backPointer[i] = j;
		}}
		if(T[i] > T[pos]) pos = i;
	}
	
	LIS.push_back(CR[pos]);
	while(backPointer[pos] != pos) {
		pos = backPointer[pos];
		LIS.push_back(CR[pos]);
	}
}

void computeDS() {
	vector<bool> used(n, false);
	for(int i = 0; i < LIS.size(); ++i) {used[LIS[i]] = true;}
	for(int i = 0; i < R.size(); ++i) {if(!used[R[i]]) DS.push_back(R[i]);}
}


//--------------- DEBUGGING TOOLS

void printvec(string s, vector<int> V) {
	cerr << s << " :";
	for(int i = 0; i < V.size(); ++i) cerr << V[i] << " ";
	cerr << endl << endl;
}

void printvec(vector<int> V) {
	for(int i = 0; i < V.size(); ++i) cerr << V[i] << " ";
	cerr << endl;
}

void printG() {
	for(int i = 0; i < G.size(); ++i) {
		cerr << i << ": ";
		for(int j = 0; j < G[i].size(); ++j) {
			cerr << G[i][j] << " "; }
		cerr << endl; }	
}

// -------------------------------


int main() {
	readInput();
	if(debug) {
		cerr << "n: " << n << "  k: " << k << endl;
		printG(); 
	}
	
	makeR(); 
	if(debug) printvec("R", R);

	computeIndeg();
	if(debug) {
		printvec("Indegree R->R", indegRR);
		printvec("Indegree U->U", indegUU);
		printvec("Indegree U->R", indegUR);
	}
	
	if(!sortAndVerifyU()) {
		printf("impossible\n");
		return 0;
	}

	if(debug) printvec("R, topo sorted", R);
	
	computeCR();
	if(debug) printvec("consistent R, topo sirted", CR);
		
	computeLIS();
	if(debug) {printvec("L.I.S. in R", LIS);}
	
	computeDS();
	if(debug) {printvec("FVS ", DS);}

	if(DS.size() >= k) {
		printf("impossible\n");
		return 0;
	}
	printf("%d\n", DS.size());
	
	return 0;
}


