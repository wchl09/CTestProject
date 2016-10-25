//
// Created by 王春雷 on 2016/10/20.
//
#include "test.h"
#include <sstream>

jstring s;
extern "C"
jstring
Java_com_example_wangchunlei_ctestproject_MainActivity_getStringFromC(JNIEnv *env,
                                                                      jobject /* this */) {
    char *pat = getStringFromTest();
    jclass strClass = env->FindClass("Ljava/lang/String;");
    jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = env->NewByteArray(strlen(pat));
    env->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte *) pat);
    return env->NewStringUTF("utf-8");
//    s = env->NewStringUTF(encoding.c_str());
//    return s;
}
extern "C"
jstring Java_com_example_wangchunlei_ctestproject_MainActivity_addIntArray(JNIEnv *env,
                                                                           jobject /* this */,
                                                                           jintArray array) {


    jint *carray;
    carray = env->GetIntArrayElements(array, false);
    if (carray == NULL) {
        return env->NewStringUTF("空的数组");
    }
    jsize len = env->GetArrayLength(array);
    std::stringstream ss;
    ss << "数组[";
    for (int i = 0; i < len; ++i) {
        ss << carray[i];
        ss << ",";
    }
//    ss.seekg(-1, _GLIBCXX_STDIO_SEEK_END);
    ss << "]的计算结果=";
    ss << addIntArray(carray);
    std::string string;
    ss >> string;
    ss.str("");
    ss.clear();
    return env->NewStringUTF(string.c_str());
}

char *getStringFromTest() {
    return "这是从C++中返回的数据";
}

extern "C"
jdouble Java_com_example_wangchunlei_ctestproject_MainActivity_getCircularvVolume(JNIEnv *env,
                                                                                  jobject /* this */,
                                                                                  jfloat radius) {
    int volume = getCircularvVolume(radius);
    return volume;
}

int addIntArray(int *array) {
    jsize len = sizeof(array);
    unsigned int count = 0;
    for (int i = 0; i < len; ++i) {
        count += array[i];
    }
    return count;
}

double getCircularvVolume(float radius) {
    double volume = TEMP * PI * radius * radius * radius;
    return volume;
}
