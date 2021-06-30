import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

enum LoadState { LOADING, SUCCESS, FAILURE, DONE, NO_MORE, EMPTY }

class EmptyPage extends StatelessWidget {

  VoidCallback? onPressed;

  EmptyPage({this.onPressed,Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset(
              "asset/images/no_data.png",
              width: 50,
              height: 50,
            ),
            Text("没有数据"),
            Padding(padding: EdgeInsets.only(top: 10)),
            OutlinedButton(
                onPressed: onPressed,
                child: Text(
                  "点击刷新",
                  style: TextStyle(color: Colors.blue, fontSize: 15),
                ))
          ],
        ),
      ),
    );
  }
}

class LoadingPage extends StatelessWidget {
  const LoadingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text("加载中");
  }
}

