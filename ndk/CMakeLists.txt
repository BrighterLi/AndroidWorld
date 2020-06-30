# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1) #cmake 最低版本要求，低于2.6 构建过程会被终止

include_directories(
        ${CMAKE_SOURCE_DIR}/src/main/cpp/include #h文件目录
)
# 设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})

add_library(
        # Sets the name of the library.  设置生成so文件名
        HelloNDK

        # Sets the library as a shared library. 设置库的类型，一种是静态的STATIC .a文件，一种是动态SHARED so文件
        SHARED

        # Provides a relative path to your source file(s). 要编译的C/C++文件
        src/main/cpp/HelloNDK.c
        src/main/cpp/ArithmeticNative.cpp
)