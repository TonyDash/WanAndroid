// I2MainProcessAidlInterface.aidl
package com.cjy.commonlibrary;
import com.cjy.commonlibrary.I2WebViewProcessAidlInterface;

interface I2MainProcessAidlInterface {
    void handleWebCommand(String commandName,String jsonParams,in I2WebViewProcessAidlInterface i2WebViewInterface);
}