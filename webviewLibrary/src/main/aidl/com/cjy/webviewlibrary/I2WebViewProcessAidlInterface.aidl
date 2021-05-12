// I2WebViewProcessAidlInterface.aidl
package com.cjy.webviewlibrary;

// Declare any non-default types here with import statements

interface I2WebViewProcessAidlInterface {
    void onResult(String callName,boolean status,String response);
}