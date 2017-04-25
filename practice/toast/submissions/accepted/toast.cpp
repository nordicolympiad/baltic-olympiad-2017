#include<iostream>
#include<math.h>
#include <iomanip>

using namespace std;

double angle(long long degree, long long totalPeople) {
	if(degree + 1 == totalPeople && (totalPeople % 2) == 0) return 2*3.141592653589793;
	double angleChunk = (2.0*3.141592653589793 / ((double)totalPeople));
	return angleChunk * ((double) degree);
}

double radius(long long degree, long long totalPeople, double armLength) {
	double ang = angle(degree, totalPeople) / 4.0;
	return armLength / sin(angle(degree, totalPeople) / 4.0);
}

int main() {
	long long N,D,T;
	cin >> N >> D >> T;
	long long deg = (2*T / N);
	double minRadius = (deg+1 == N) ? 0 : radius(deg + (deg == N-2 ? 1 : 2), N, (double) D);
	double maxRadius = radius(deg, N, (double) D);
	cout 	<< setprecision(10) << minRadius << " " << maxRadius << endl;
	return 0;
}