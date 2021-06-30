import 'package:flutter_library/network/http_manager.dart';
import 'package:flutter_library/routes/Routes.dart';
import 'package:flutter_library/string/string.dart';
import 'package:flutter_library/ui/state_page.dart';
import 'package:get/get.dart';

typedef Success(dynamic value);
typedef Failure(dynamic value);
typedef Done();

abstract class BaseGetXController extends GetxController {
  var loadState = LoadState.DONE.obs;
  var exception;
  var errorMessage = DString.LOAD_ERROR.obs;

  void handleRequest(Future<dynamic> future, bool isLoading, Success success,
      {Failure? failure, Done? done}) {
    if (isLoading) {
      loadState.value = LoadState.LOADING;
    }
    future.then((value) {
      //请求数据成功
      success(value);
    }).onError<ResultException>((error, stackTrace) {
      //请求发生错误
      if(isLoading){
        loadState.value = LoadState.FAILURE;
      }
      if(error.code == HttpDioError.LOGIN_CODE){
        //需要登录
        Get.toNamed(RoutesConfig.LOGIN_PAGE);
      }
      if(failure!=null){
        failure(error.message);
      }
      errorMessage.value = error.message;
    });
  }

  void initData(bool isShowLoading){

  }

  void refresh(){

  }
}
