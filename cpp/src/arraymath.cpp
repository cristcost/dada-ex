//
//  arraymath.cpp
//  DadaSandbox
//
//  Created by Cristiano Costantini on 11/10/15.
//
//

#include "arraymath.hpp"
#include <algorithm>
#include <iostream>


std::vector<int> shift(std::vector<int> array) {
    std::vector<int> ret = std::vector<int>(array.size() + 1, 0);
    
    for (int i = 0; i < array.size(); i++) {
        ret[i+1] = array[i];
    }
    return ret;
}
std::vector<int> stripZeros(std::vector<int> array){
    while (array.back() == 0 && array.size() > 0) {
        array.pop_back();
    }
    return array;
}

int valueAtArrayIndexOrZero(std::vector<int> a, int i) {
    if(i < a.size()) {
        return a[i];
    }
    return 0;
}

std::vector<int> sum(std::vector<int> a, std::vector<int> b) {
    bool carry = false;
    std::vector<int> ret = std::vector<int>(std::max(a.size(), b.size()) + 1, 0);

    for (int i = 0; i < ret.size(); i++) {
        int partialSum = valueAtArrayIndexOrZero(a, i) + valueAtArrayIndexOrZero(b, i) + (carry ? 1 : 0);
        if (partialSum >= 10) {
            carry = true;
            partialSum -= 10;
        } else {
            carry = false;
        }
        ret[i] = partialSum;
    }
    return stripZeros(ret);
}

std::vector<int> multiply(std::vector<int> a, std::vector<int> b) {
    std::vector<int> ret = std::vector<int>(1,0);
    
    for (int i = 0; i < b.size(); i++) {
        for (int j = 0; j < b[i]; j++) {
            ret = sum(ret, a);
        }
        a = shift(a);
    }
    return stripZeros(ret);
}