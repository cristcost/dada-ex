//
//  main.cpp
//  Sandbox
//
//  Created by Cristiano Costantini on 10/10/15.
//  Copyright (c) 2015 cristcost.net. All rights reserved.
//


#include <iostream>
#include <fstream>
#include <string>

using namespace std;

bool validateString(const char* string) {
    return false;
}

int main(int argc, const char * argv[]) {
    
    if(argc < 3) {
        cout << "Usage:   multiply operand1 operand2" << endl;
        cout << "Example: multiply 123 321" << endl;
        exit(0);
    }
    if (!validateString(argv[1])) {
        cout << "Invalid operand 1" << endl;
        exit(0);
    }
    if (!validateString(argv[2])) {
        cout << "Invalid operand 2" << endl;
        exit(0);
    }
    
    cout << "Sandbox project for Multiply exercise" << endl;
    
    return 0;
}
