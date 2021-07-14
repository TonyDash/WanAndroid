import 'package:flutter/material.dart';
import 'package:flutter_library/routes/Routes.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return RefreshConfiguration(
        child: ScreenUtilInit(
          designSize: Size(360, 690),
          builder: () => GetMaterialApp(
            darkTheme: ThemeData.dark(),
            debugShowCheckedModeBanner: false,
            initialRoute: RoutesConfig.INITIAL,
            getPages: RoutesConfig.getPages,
            localizationsDelegates: [
              // 这行是关键
              RefreshLocalizations.delegate,
              GlobalWidgetsLocalizations.delegate,
              GlobalMaterialLocalizations.delegate,
              GlobalCupertinoLocalizations.delegate
            ],
            supportedLocales: [
              const Locale('zh'),
              const Locale('en', 'US'),
            ],
          ),
        )
    );
  }
}
