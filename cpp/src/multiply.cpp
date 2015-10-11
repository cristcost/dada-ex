//
//  main.cpp
//  DadaSandbox
//
//  Created by Cristiano Costantini on 10/10/15.
//  Copyright (c) 2015 cristcost.net. All rights reserved.
//


#include <iostream>
#include <fstream>
#include <string>
#include <vector>

#include "utils.hpp"
#include "arraymath.hpp"


using namespace std;

int main(int argc, const char * argv[]) {
    
    if(argc < 3) {
        cout << "Usage:   multiply operand1 operand2" << endl;
        cout << "Example: multiply 123 321" << endl;
        exit(0);
    }
    
    string arg1 = string(argv[1]);
    string arg2 = string(argv[2]);
    
    if (!validateString(arg1)) {
        cout << "Invalid operand 1" << endl;
        exit(0);
    }
    if (!validateString(arg2)) {
        cout << "Invalid operand 2" << endl;
        exit(0);
    }
    
    vector<int> a = stringToArray(arg1);
    vector<int> b = stringToArray(arg2);
    
    vector<int> result = multiply(a, b);
    
    cout << arrayToString(result) << endl;
    
    return 0;
}
