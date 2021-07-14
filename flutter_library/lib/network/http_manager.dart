import 'dart:io';

import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_library/network/request_api.dart';
import 'package:path_provider/path_provider.dart';

class HttpManager {
  late final Dio _dio;
  // 静态私有成员，没有初始化
  static HttpManager? _instance;
  // late PersistCookieJar _cookieJar;
  static final int TIME_OUT = 30000;
  // 工厂模式 : 单例公开访问点
  factory HttpManager() => _getInstance();
  static HttpManager get instance => _getInstance();


  //构造方法私有,此处也是命名构造方法（类名+.+方法名）
  HttpManager._() {
    BaseOptions options = BaseOptions(
        baseUrl: RequestApi.HOST,
        sendTimeout: TIME_OUT,
        receiveTimeout: TIME_OUT,
        connectTimeout: TIME_OUT);
    _dio = Dio(options);
    //cookie
    // _dio.interceptors.add(CookieManager(_cookieJar));
    //缓存机制
    // _dio.interceptors.add(NetCache());
    if (kDebugMode) {
      _dio.interceptors
          .add(LogInterceptor(request: true, responseBody: true, error: true));
    }
  }

  //静态、同步、私有访问点。
  //静态获取单例的方法，供外部使用。
  static HttpManager _getInstance() {
    return _instance ?? HttpManager._();
  }
  //
  // //将数据写入cookie
  // void addCookies(List<Cookie> cookies) async {
  //   await _cookieJar.loadForRequest(Uri.parse(RequestApi.HOST));
  // }
  //
  // //从cookie中获取数据
  // Future<PersistCookieJar?> getCookieJar() async {
  //   Directory appDocDir = await getApplicationDocumentsDirectory();
  //   String appDocPath = appDocDir.path;
  //   _cookieJar = new PersistCookieJar(storage: FileStorage(appDocPath));
  // }
  //
  // //清空cookies
  // Future<void> clearCookies() async {
  //   await _cookieJar.deleteAll();
  // }

  Future get(String url,
      {Map<String, dynamic>? params,
      bool refresh = false,
      bool list = false,
      bool cacheDisk = true,
      bool noCache = true}) async {
    Options options = Options(extra: {
      "list": list,
      "noCache": noCache,
      "cacheDisk": cacheDisk,
      "refresh": refresh
    });
    try {
      Response response;
      response = await _dio.get(url, queryParameters: params, options: options);
      if (response.data['errorCode'] != 0) {
        throw ResultException(
            response.data['errorCode'], response.data['errorMsg']);
      }
      return response.data;
    } on DioError catch (e) {
      throw HttpDioError.handleError(e);
    }
  }

  Future post(String url, {dynamic params}) async {
    try {
      Response response;
      if (params == null) {
        response = await _dio.post(url);
      } else {
        response = await _dio.post(url, data: params);
      }
      if (response.data['errorCode'] != 0) {
        throw ResultException(
            response.data['errorCode'], response.data['errorMsg']);
      }
      return response.data;
    } on DioError catch (e) {
      throw HttpDioError.handleError(e);
    }
  }


  Future postFormData(String url, Map<String, dynamic> params) async {
    try {
      Response response;
      response = await _dio.post(url, data: FormData.fromMap(params));
      if (response.data['errorCode'] != 0) {
        throw ResultException(
            response.data['errorCode'], response.data['errorMsg']);
      }
      return response.data;
    } on DioError catch (e) {
      throw HttpDioError.handleError(e);
    }
  }
}

class HttpDioError {
  static const int LOGIN_CODE = -1001;

  static ResultException handleError(DioError dioError) {
    int code = 9999;
    String message = "未知错误";
    switch (dioError.type) {
      case DioErrorType.connectTimeout:
        code = 9000;
        message = "网络连接超时，请检查网络设置";
        break;
      case DioErrorType.receiveTimeout:
        code = 90001;
        message = "服务器异常，请稍后重试！";
        break;
      case DioErrorType.sendTimeout:
        code = 90002;
        message = "网络连接超时，请检查网络设置";
        break;
      case DioErrorType.response:
        code = 90003;
        message = "服务器异常，请稍后重试！";
        break;
      case DioErrorType.cancel:
        code = 90004;
        message = "请求已被取消，请重新请求";
        break;
      case DioErrorType.other:
        code = 90005;
        message = "网络异常，请稍后重试！";
        break;
    }
    return ResultException(code, message);
  }
}

class ResultException {
  final int code;
  final String message;

  ResultException(this.code, this.message);
}
