import 'package:flutter_library/ui/system/system_page.dart';
import 'package:flutter_library/ui/test_page.dart';
import 'package:get/get.dart';

abstract class RoutesConfig {
  static const TEST = "/test";
  static const SYSTEM = "/system";
  static const INITIAL = "/initial";
  static const LOGIN_PAGE = "/login";

  static final List<GetPage> getPages = [
    GetPage(name: INITIAL, page: () => SystemMainPage()),
    GetPage(name: SYSTEM, page: () => SystemMainPage()),
    GetPage(name: TEST, page: () => TestPage()),
  ];
}