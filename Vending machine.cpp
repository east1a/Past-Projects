//Easton Anderson
//Wei Ding
//Vending machine - Bonus project
//4/22/2019

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

class VendingMachine {
public:
	string option;
	double amount = 0;
	double price;
	double prices[6] = { 1.00, 1.50, 2.00, 2.00, 1.00, 1.50 };
	int stock[6] = { 1, 3, 4, 7, 2, 3 };
	virtual void display() {
		cout << "You have inserted " << amount * .25 << endl;
		cout << "Your options are: \nA1 - Granola Bar (" << stock[0] << " left) for 1.00\nA2 - Dwight's Wallet (" << stock[1] <<
			" left) for 1.50\nA3 - Candy Bar (" << stock[2] << " left) for 2.00\nB1 - Water Bottle (" << stock[3] <<
			" left) for 2.00\nB2 - Trail Mix (" << stock[4] << " left) for 1.00\nB3 - Chips (" << stock[5] << " left) for 1.50" << endl;
	}

	virtual void addCoins() {
		int coins = 0;
		cout << "How many quarters are you putting into the machine? " << endl;
		cin >> coins;
		amount += coins;
		display();
	}

	virtual void purchase() {
		bool again = true;
		while (again) {
			char answer;
			char response;
			bool stocked = false;
			bool price = false;
			cout << "Which item would you like to purchase? Enter the matching number." << endl;
			cin >> option;
			if (option == "A1") {
				if (amount*.25 >= prices[0]) {
					price = true;
					if (stock[0] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[0]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			else if (option == "A2") {
				if (amount*.25 >= prices[1]) {
					price = true;
					if (stock[1] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[1]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			else if (option == "A3") {
				if (amount*.25 >= prices[2]) {
					price = true;
					if (stock[2] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[2]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			else if (option == "B1") {
				if (amount*.25 >= prices[3]) {
					price = true;
					if (stock[3] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[3]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			else if (option == "B2") {
				if (amount*.25 >= prices[4]) {
					price = true;
					if (stock[4] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[4]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			else if (option == "B3") {
				if (amount*.25 >= prices[5]) {
					price = true;

					if (stock[5] > 0) {
						stocked = true;
						amount -= (price / .25);
						stock[5]--;
					}
					else {
						stocked = false;
					}
				}
				else {
					price = false;
				}
			}
			if (stocked && price) {
				cout << "Purchase successful! Your current balance is " << amount*.25 << endl;
				cout << "Would you like to make another purchase? (y for yes, any button for no)" << endl;
				cin >> answer;
				if (answer == 'y') {
					display();
					again = true;
				}
				else {
					again = false;
				}

			}
			else if (!price) {
				cout << "You need to insert more money to complete the purchase." << endl;
				cout << "Would you like to add more coins now? (y for yes, any button for no)";
				cin >> response;
				if (response == 'y') {
					addCoins();
					again = true;
				}
				else {
					cout << "Your change has been returned to you below." << endl;
					again = false;
				}
			}
			else if (!stocked) {
				cout << "We're sorry! There are no more items in that slot. Your change has been returned to you below." << endl;
				again = false;
			}
		}
	}

};

class DrinkVendor : public VendingMachine {
public: 
	double prices[6] = { 2.50, 1.00, 1.00, 2.00, 2.00, 1.50 };
	int stock[6] = { 1, 3, 4, 7, 2, 3 };
	virtual void display() {
		cout << "You have inserted " << amount * .25 << endl;
		cout << "Your options are: \nA1 - Coca Cola (" << stock[0] << " left) for 2.50\nA2 - Mountain Dew (" << stock[1] <<
			" left) for 1.00\nA3 - Sprite (" << stock[2] << " left) for 1.00\nB1 - Alka Seltzer (" << stock[3] <<
			" left) for 2.00\nB2 - Pepsi (" << stock[4] << " left) for 2.00\nB3 - Dasani (" << stock[5] << " left) for 1.50" << endl;
	}

};

int main() {
	std::cout << std::fixed << std::setprecision(2);
	VendingMachine machine;
	DrinkVendor vendor;
	cout << "You are using the main vending machine." << endl;
	machine.addCoins();
	machine.purchase();
	cout << "You are now using the drink vending machine." << endl;
	vendor.addCoins();
	vendor.purchase();

	system("pause");
}