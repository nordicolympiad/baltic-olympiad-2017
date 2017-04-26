#include<iostream>
#include<string>
#include <stdlib.h> 
#include<cstring>
#include<sstream>
#include<time.h>

using namespace std;

string myitos(int a) {
	stringstream ss;
	ss << a;
	string str = ss.str();
	
	return str;
}

void makeOneTest(int groupNumber, int number) {
	cout << "Testcase: " << number << endl;  
//	cout << "@java -Xss64m Minister < ..\\..\\data\\secret\\g" << groupNumber << "\\railway." << (number < 10 ? "0" : "") << number << ".in";
//	cout << " > ..\\..\\data\\secret\\g" << groupNumber << "\\railway." << (number < 10 ? "0" : "") << number << ".ans" << endl;
	string s = "@..\\submissions\\accepted\\railway < ..\\data\\secret\\g" + myitos(groupNumber) + "\\railway." + (number < 10 ? "0" : "") + myitos(number) + ".in";
	int dummy = system(s.c_str());
}

void makeGroup(int groupNumber, int groupSize) {
	
	cout << "--- GROUP " << groupNumber << " --- time: " << time(NULL) << endl;
	
	
	time_t start = time(NULL);
	for(int i = 1; i <= groupSize; ++i) makeOneTest(groupNumber, i);
	cout << "--- END GROUP " << groupNumber << " --- time: " << difftime(time(NULL),start) << endl;
}

int main() {
	makeGroup(1, 14);
	//makeGroup(2, 21);
	//makeGroup(3, 6);
	//makeGroup(4, 7);
	//makeGroup(5, 15);
	//makeGroup(6, 50);
}
//[14, 21, 6, 7, 15, 50]
