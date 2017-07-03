#include <jni.h>
#include <string>
extern "C"

JNIEXPORT jstring JNICALL
Java_androidluckyguys_sqlcipher_MainActivity_stringFromJNIClass(JNIEnv *env, jobject instance) {

    // TODO
    std::string returnValue = "K#Y_4_Encrypt!0n";
    return env->NewStringUTF(returnValue.c_str());
}
extern "C"

JNIEXPORT jstring JNICALL
Java_com_example_sqlcipherjniexample_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "K#Y_4_Encrypt!0n";
    return env->NewStringUTF(hello.c_str());
}
