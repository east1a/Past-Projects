#include <iostream>
#include <string>
using namespace std;

class LivingThing {
public:
	virtual void grow() {
		cout << "Living Thing grows by taking in nutrition." << endl;
	}
};

class Animal: public LivingThing {
public:
	virtual void eat() {
		cout << "Animal eats something it can digest and give it nutrition." << endl;
	}
};

class Plant: public LivingThing {
public:
	virtual void bloom() {
		cout << "Plant blooms during certain seasons when it will get enouhg sunlight and nutrition." << endl;
	}

	void grow() {
		cout << "Plant grows by performing photosynthesis from the suns rays and absorbing water." << endl;
	}
};

class Fish: public Animal {
public:
	void eat() {
		cout << "Fish eats plants if it is herbivorous, but some eat other fish and other aquatic animals." << endl;
	}

	void grow() {
		cout << "Fish grows by eating other fish or sea plants." << endl;
	}
};

class Mammal: public Animal {
public:
	void grow() {
		cout << "Mammal grows by tkaing in nutrition and digesting it." << endl;
	}
};

class Rose: public Plant {
public:
	void bloom() {
		cout << "Rose blooms during spring." << endl;
	}

	void grow() {
		cout << "Rose grows by performing photosynthesis and abosrbing water. It protects itself with thorns." << endl;
	}
};

class Plum: public Plant {

};

class Wolf : public Mammal {
public:
	void eat() {
		cout << "Wolf eats prey that it catches with the pack." << endl;
	}

	void grow() {
		cout << "Wolf grows by digesting meat." << endl;
	}
};

class Tiger : public Mammal {
public:
	void eat() {
		cout << "Tiger eats meat, which it gets by stalking its prey and getting close before pouncing." << endl;
	}
};

void displayLivingThing(LivingThing& a1) {
	a1.grow();
}

int main() {
	Fish fish1;
	Wolf wolf1;
	Tiger tiger1;
	Rose rose1;
	Plum plum1;
	Mammal mammal1;
	Plant plant1;
	cout << "Eat, grow and bloom calls: " << endl;
	fish1.eat();
	fish1.grow();
	wolf1.eat();
	wolf1.grow();
	tiger1.eat();
	tiger1.grow();
	mammal1.eat();
	mammal1.grow();
	rose1.bloom();
	rose1.grow();
	plum1.bloom();
	plum1.grow();
	plant1.bloom();
	plant1.grow();
	cout << "Display Living Thing: " << endl;
	displayLivingThing(fish1);
	displayLivingThing(wolf1);
	displayLivingThing(tiger1);
	displayLivingThing(mammal1);
	displayLivingThing(rose1);
	displayLivingThing(plum1);
	displayLivingThing(plant1);

	system("pause");
}