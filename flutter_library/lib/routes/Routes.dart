import 'package:flutter/material.dart';
import 'package:flutter_library/default/global.dart';
import 'package:flutter_library/ui/detail/web_page.dart';
import 'package:flutter_library/ui/system/system_page.dart';
import 'package:flutter_library/ui/test_page.dart';
import 'package:get/get.dart';

abstract class RoutesConfig {
  static const TEST = "/test";
  static const SYSTEM = "/system";
  static const INITIAL = "/initial";
  static const LOGIN_PAGE = "/login";
  static const WEB_PAGE = "/web_page";

  static final List<GetPage> getPages = [
    GetPage(name: INITIAL, page: () => SystemMainPage()),
    GetPage(name: SYSTEM, page: () => SystemMainPage()),
    GetPage(name: TEST, page: () => TestPage()),
    GetPage(name: WEB_PAGE, page: () => WebPage()),
  ];
}


///路由跳转的中间鉴权，需要登录后才能访问的页面
class RouteAuthMiddleware extends GetMiddleware {

  @override
  RouteSettings? redirect(String? route) {
    //没有登录过，就需要去登录页面
      if (!Global.isUserOnLine) {
        return RouteSettings(name: RoutesConfig.LOGIN_PAGE);
      }
      //登录过，就不要拦截正常跳转到目标页面。
      return null;
  }
}