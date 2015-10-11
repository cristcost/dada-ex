//
//  utils.hpp
//  DadaSandbox
//
//  Created by Cristiano Costantini on 11/10/15.
//
//

#ifndef utils_hpp
#define utils_hpp

#include <stdio.h>
#include <vector>
#include <string>

bool validateString(std::string);

bool validateArray(std::vector<int> array);

std::vector<int> stringToArray(std::string);

std::string arrayToString(std::vector<int> array);


#endif /* utils_hpp */
