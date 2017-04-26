#include<iostream>
#include<string>

using namespace std;

void makeOneTest(int groupNumber, int number) {
	cout << "@echo testcase: " << number << "   time: %time% " << endl;  
	cout << "@Railway < ..\\..\\data\\secret\\g" << groupNumber << "\\railway." << (number < 10 ? "0" : "") << number << ".in";
	cout << " > ..\\..\\data\\secret\\g" << groupNumber << "\\railway." << (number < 10 ? "0" : "") << number << ".cppans" << endl;
}

void makeGroup(int groupNumber, int groupSize) {
	cout << "@echo --- GROUP " << groupNumber << " --- time: %time%" << endl;
	for(int i = 1; i <= groupSize; ++i) makeOneTest(groupNumber, i);
	cout << "@echo --- END GROUP " << groupNumber << " --- time: %time%" << endl;
}

int main() {
	makeGroup(1, 14);
	makeGroup(2, 21);
	makeGroup(3, 6);
	makeGroup(4, 7);
	makeGroup(5, 15);
	makeGroup(6, 50);
}
//[14, 21, 6, 7, 15, 50]