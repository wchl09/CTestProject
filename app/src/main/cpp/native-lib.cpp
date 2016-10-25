#include <jni.h>
#include <string>

#include "filetest.h"


extern "C"
jstring
Java_com_example_wangchunlei_ctestproject_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    hello =a();
    return env->NewStringUTF(hello.c_str());
}

/**
 * char To string
 */
jstring charToString(_JNIEnv *env, char *c) {
    jclass strClass = env->FindClass("Ljava/lang/String;");
    jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = env->NewByteArray(strlen(c));
    env->SetByteArrayRegion(bytes, 0, strlen(c), (jbyte *) c);
    return env->NewStringUTF("utf-8");
}