import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_library/routes/Routes.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

import 'default/default.dart';
import 'default/global.dart';

// void main() => runApp(MyApp());
//
// class MyApp extends StatelessWidget {
//   const MyApp({Key? key}) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return GetMaterialApp(
//       debugShowCheckedModeBanner: false,
//       initialRoute:RoutesConfig.INITIAL,
//       getPages: RoutesConfig.getPages,
//     );
//   }
// }

// 是否开发环境
bool get isInDebugMode => true;

Future main() async{
  FlutterError.onError = (FlutterErrorDetails errorDetails) async{
    if (isInDebugMode == true) {
      FlutterError.dumpErrorToConsole(errorDetails);
    }else {
      Zone.current.handleUncaughtError(errorDetails.exception, errorDetails.stack??StackTrace.empty);
    }
  };

  // 捕获并上报 Dart 异常
  runZonedGuarded(() async {
    await Global.init();
    runApp(MyApp());
  }, (Object error, StackTrace stack) {
    _reportError(error, stack);
  });
}

// 上报异常的函数
Future<void> _reportError(dynamic error, dynamic stackTrace) async {
  print('Caught error: $error');
  if (isInDebugMode) {
    print(stackTrace);
  }
}

