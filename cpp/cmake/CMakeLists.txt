cmake_minimum_required (VERSION 3.2)
project (DadaSandbox)

set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)

#find_package( Boost 1.58 COMPONENTS program_options REQUIRED )
#include_directories( /Users/cristcost/dev/SDK/boost_1_58_0 )

add_executable(multiply src/multiply.cpp src/utils.cpp src/arraymath.cpp)

target_link_libraries( multiply)

