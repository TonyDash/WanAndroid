// I2MainProcessAidlInterface.aidl
package com.cjy.webviewlibrary;

import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface;
// Declare any non-default types here with import statements

interface I2MainProcessAidlInterface {
    void handleWebCommand(String commandName,String jsonParams,in I2WebViewProcessAidlInterface i2WebViewInterface);
}