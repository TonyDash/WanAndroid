import 'package:flutter_library/controller/base_getx_controller.dart';
import 'package:get/get.dart';

enum LoginState{LOGIN,LOGOUT}

class AppState extends BaseGetXController{
  var loginState = LoginState.LOGOUT.obs;

  void setLoginState(LoginState state){
    this.loginState.value = state;
  }
}