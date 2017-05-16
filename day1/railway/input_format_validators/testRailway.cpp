#include<iostream>
#include<vector>
#include<queue>



using namespace std;

bool totalSuccess = true;

void assert(bool expr, string failureString) {
	if(!expr) {
		cerr << failureString << endl;
		totalSuccess = false;
		return;
	}
}


int main() {
	int maxM = 100000; 
	int maxN = 200000;
	int maxS = 200000;
	
	std::ios::sync_with_stdio(false);

	int n, m, k;
	cin >> n >> m >> k;
	
	vector<vector<int> > T(n, vector<int> ());
	
	for(int i = 0; i < n - 1; ++i) {
		int u, v;
		cin >> u >> v;
		T[u-1].push_back(v-1);
		T[v-1].push_back(u-1);
	}
	
	vector<bool> visited(n, false);
	visited[0] = true;
	queue<int> Q;
	Q.push(0);
	
	while(!Q.empty()) {
		int v = Q.front(); Q.pop();
		
		for(int i = 0; i < T[v].size(); ++i) {
			int u = T[v][i];
			if(visited[u]) continue;
			Q.push(u);
			visited[u] = true;
		}
	}
	
	bool success = true;
	for(int i = 0; i < n; ++i) {success &= visited[i];}
	
	assert(success, "disconnected tree");

	assert(n >= 2, "Too few vertices");
	assert(n <= maxN,  "Too many vertices");
		
	
	assert(m >= 1, "Too few ministers");
	assert(m <= maxM, "Too many ministers");

	int s = 0;
	for(int i = 0; i < m; ++i) {
		int si;
		cin >> si;
		for(int j = 0; j < si; ++j) {
			int p;
			cin >> p;
			assert(1 <= p && p <= n, "city not in range");
		}
		assert(si >= 2, "si < 2");
		s += si;
	}
	assert(s >= 2*m, "Too small S");
	assert(s <= maxS, "Too big S");
	
	assert(k >= 1, "too small k");
	assert(k <= m, "too big k");
	
	return totalSuccess ? 42 : 1;
}