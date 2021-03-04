//Easton Anderson
//Dr. Wei
//COP2272C-03
//Recursion1
//February 14 2019

#include <iostream>
using namespace std;

void multiMove(int , int , int , int );
int main() {
	int disks, dest, source, buffer = 0;
	cout << "How many disks would you like to have moved? " << endl;
	cin >> disks;
	cout << "What tower are they located on initially? (1, 2 or 3)" << endl;
	cin >> source;
	cout << "What tower would you like them moved to?(1, 2 or 3) " << endl;
	cin >> dest;
	if (dest == source) {
		cout << "The destination tower cannot be the same as the source tower. Try again." << endl;
		cin >> dest;
	}
	if (dest * source == 2) {
		buffer = 3;
	}
	else if (dest * source == 3) {
		buffer = 2;
	}
	else if (dest * source == 6) {
		buffer = 1;
	}
	else {
		cout << "Input for towers must be 1, 2 or 3." << endl;
		system("exit");
	}
	multiMove(disks, dest, buffer, source);
	system("pause");
}

void multiMove(int numOfDisk, int dest, int buff, int source) {

	if (numOfDisk == 1) {
		cout << "Disk " << numOfDisk << " moved from tower " << source << " to tower " << dest << endl;
		return;
	}
	multiMove(numOfDisk - 1, buff, dest, source);
	cout << "Disk " << numOfDisk << " moved from tower " << source << " to tower " << dest << endl;
	multiMove(numOfDisk - 1, dest, source, buff);
}

