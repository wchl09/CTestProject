//
// Created by 王春雷 on 2016/10/20.
//
#include <jni.h>


#ifndef CTESTPROJECT_TEXT_H
#define CTESTPROJECT_TEXT_H
#define PI 3.1415926f
#define TEMP 4.0f/3.0f
//    返回一串字符通过底层库
extern "C"
char *getStringFromTest();
extern "C"
//把传出的字符相加
int addIntArray(int *array);
extern "C"
/**
 * 求圆的体积
 */
double getCircularvVolume(float radius);

#endif //CTESTPROJECT_TEXT_H
