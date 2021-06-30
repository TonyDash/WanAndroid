import 'package:flutter_library/ui/system/system_page.dart';
import 'package:get/get.dart';

abstract class RoutesConfig {
  static const SYSTEM = "/system";
  static const INITIAL = "/system";
  static const LOGIN_PAGE = "/login";

  static final List<GetPage> getPages = [
    GetPage(name: INITIAL, page: () => SystemMainPage()),
    GetPage(name: SYSTEM, page: () => SystemMainPage())
  ];
}