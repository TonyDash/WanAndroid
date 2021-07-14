import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_library/common/constant_info.dart';
import 'package:flutter_library/common/shared_preference_util.dart';
import 'package:flutter_library/model/user_data.dart';
import 'package:flutter_library/state/app_state.dart';
import 'package:get/get.dart';

class Global {
  static User userProfile = User();

  //是否第一次打开程序
  static bool isFirstOpen = false;
  //用户是否在线
  static bool isUserOnLine = false;

  static Future init() async {
    var appState = Get.put<AppState>(AppState());
    //告诉程序在启动之前需要做一些其他操作
    WidgetsFlutterBinding.ensureInitialized();

    if (Platform.isAndroid) {
      //如果是android设备，状态栏设置为透明沉浸
      SystemUiOverlayStyle _style =
          SystemUiOverlayStyle(statusBarColor: Colors.transparent);
      SystemChrome.setSystemUIOverlayStyle(_style);
    }

    String spUser = await SharedPreferenceUtil.getString(ConstantInfo.KEY_USER);
    if (spUser != "") {
      userProfile = User.fromJson(spUser);
    }
    isFirstOpen =
        await SharedPreferenceUtil.getBool(ConstantInfo.KEY_IS_FIRST_OPEN_APP);
    SharedPreferenceUtil.getBool(ConstantInfo.KEY_USER).then((value) {
      isUserOnLine = value;
      appState.setLoginState(value ? LoginState.LOGIN : LoginState.LOGOUT);
    });
  }

  static saveUserProfile(User user) {
    SharedPreferenceUtil.savePreference(ConstantInfo.KEY_USER, user);
    userProfile = user;
  }
}
