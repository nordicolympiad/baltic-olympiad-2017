#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int main() {
	std::ios::sync_with_stdio(false);

	int n, d;
	cin >> n >> d;
	
	vector<vector<int> > T(n, vector<int> ());
	
	bool success = true;
	
	for(int i = 1; i < n; ++i) {
		int u;
		cin >> u;
		success &= u < i;
		T[u].push_back(i);
		T[i].push_back(u);
	}
	
	if(!success) {
		cout << "too large input value" << endl;
		return 0;
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
	

	for(int i = 0; i < n; ++i) {success &= visited[i];}
	if(success) {
		cout << "connected!" << endl;
	} else {
		cout << "disconnected!" << endl;
	}
	
	return 0;
}