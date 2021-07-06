import 'package:flutter/material.dart';
import 'package:flutter_library/routes/Routes.dart';
import 'package:get/get.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      initialRoute:RoutesConfig.INITIAL,
      getPages: RoutesConfig.getPages,
    );
  }
}
