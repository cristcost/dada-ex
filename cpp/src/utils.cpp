//
//  utils.cpp
//  DadaSandbox
//
//  Created by Cristiano Costantini on 11/10/15.
//
//

#include "utils.hpp"

// per debug
#include <iostream>
#include <sstream>

bool validateString(std::string str) {
    int l = str.length();
    
    for (int i = 0; i < l; i++) {
        if (str.c_str()[i] < '0' || str.c_str()[i] > '9') {
            return false;
        }
    }
    
    return true;
}

bool validateArray(std::vector<int> array){
    for (int i = 0; i < array.size(); i++) {
        if (array[i] < 0 || array[i] > 9) {
            return false;
        }
    }
    return true;
}

std::vector<int> stringToArray(std::string str){
    std::vector<int> ret;
    
    int l = str.length();
    for (int i = l-1; i >= 0; i--) {
        int value = str.c_str()[i] - '0';
        ret.push_back(value);
    }
    
    return ret;
}

std::string arrayToString(std::vector<int> array) {
    std::stringstream ret;
    for (int i = array.size() - 1; i >= 0; i--) {
        ret << array[i];
    }
    return ret.str();
}