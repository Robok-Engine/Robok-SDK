# Sets the minimum CMake version
cmake_minimum_required(VERSION 3.14.7 FATAL_ERROR)
cmake_policy(VERSION 3.14.7...3.28.1)

# Project info
set(PROJECT_NAME RobokSDK)
set(CPP_DIR src/main/cpp)
project(${PROJECT_NAME})

# Link source & header files
file(GLOB_RECURSE SOURCE_FILES "${CPP_DIR}/*.cpp" "${CPP_DIR}/*.c")
file(GLOB_RECURSE HEADER_FILES "${CPP_DIR}/*.h" "${CPP_DIR}/*.hpp")

foreach (_headerFile ${HEADER_FILES})
  get_filename_component(_dir ${_headerFile} PATH)
  list(APPEND INCLUDE_DIRS ${_dir})
endforeach()

add_library(${PROJECT_NAME} SHARED ${SOURCE_FILES})

target_include_directories(
  ${PROJECT_NAME}
  PUBLIC
  ${INCLUDE_DIRS})

# Log lib
find_library(log-lib
  log)

# Add support for the C++17 standard
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Link the required libraries
target_link_libraries(
  ${PROJECT_NAME}
  ${log-lib}
  EGL,
  GLESv3)