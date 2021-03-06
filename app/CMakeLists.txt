# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_VERBOSE_MAKEFILE on)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             motiondetecter

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/motiondetecter.cpp )

add_library( # Sets the name of the library.
             handsdetecter

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/handsdetecter.cpp )

add_library(lib_opencv SHARED IMPORTED)

# For mac
# set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ~/Documents/Android-code/HandsDetection/app/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so)

# For Windows
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION D:/AndroidCode/MR360/HandsDetection/HandsDetection/app/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so)

# For mac
# include_directories(~/Documents/Android-code/OpenCV-android-sdk/sdk/native/jni/include)

# For Windows
include_directories(D:/AndroidCode/opencv-3.2.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/include)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       motiondetecter lib_opencv

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.
                       handsdetecter lib_opencv

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )


