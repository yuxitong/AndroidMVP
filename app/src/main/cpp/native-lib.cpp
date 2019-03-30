#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_yxt_network_NetUrl_stringFromJNI(JNIEnv *env, jobject instance, jint num) {

    std::string hello;
    std::string net = "https://app.yxt.com/app/";
    switch (num) {
        case 0:
            hello = "https://app.xiaozhenkj.com/app/";
            break;
        case 1:
            hello = net + "login/";
            break;
    }
    return env->NewStringUTF(hello.c_str());
}