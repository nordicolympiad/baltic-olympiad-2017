#include<iostream>
#include<string>

using namespace std;

void makeOneTest(int groupNumber, int number) {
	cout << "@echo testcase: " << number << "   time: %time% " << endl;  
	cout << "@testConnectivity < ..\\data\\secret\\g" << groupNumber << "\\catinatree.g" << groupNumber << "." << (number < 10 ? "0" : "") << number << ".in" << endl;
}

void makeGroup(int groupNumber, int groupSize) {
	cout << "@echo --- GROUP " << groupNumber << " --- time: %time%" << endl;
	for(int i = 1; i <= groupSize; ++i) makeOneTest(groupNumber, i);
	cout << "@echo --- END GROUP " << groupNumber << " --- time: %time%" << endl;
}

int main() {
	makeGroup(1, 20);
	makeGroup(2, 20);
	makeGroup(3, 10);
	
}
//[14, 21, 6, 7, 15, 50]