import 'package:flutter/material.dart';
import 'package:flutter_library/controller/base_getx_controller.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

enum LoadState { LOADING, SUCCESS, FAILURE, DONE, NO_MORE, EMPTY }

class EmptyPage extends StatelessWidget {
  VoidCallback? onPressed;

  EmptyPage({this.onPressed, Key? key}) : super(key: key);

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

class NetWorkErrorPage extends StatelessWidget {
  VoidCallback? onPressed;
  final String? errorMeg;

  NetWorkErrorPage({this.errorMeg, this.onPressed});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.wifi_off,
                size: 50.w, color: Theme.of(context).primaryColor),
            Padding(padding: EdgeInsets.only(top: 10)),
            Text(errorMeg ?? "当前网络不可用"),
            Padding(padding: EdgeInsets.only(top: 10)),
            OutlinedButton(
              child: Text("点击重试",
                  style: TextStyle(color: Colors.blue, fontSize: 15)),
              onPressed: onPressed,
            )
          ],
        ),
      ),
    );
  }
}

class StatePageWithViewController<T extends BaseGetXController>
    extends StatefulWidget {
  final T model;
  final VoidCallback? onPressed;
  final VoidCallback? onRefresh;
  final VoidCallback? onLoading;
  final Widget? child;
  final RefreshController controller;
  final Widget? header;
  final Widget? footer;
  final Widget? failurePage;
  final Widget? emptyPage;
  final bool enablePullDown;

  StatePageWithViewController(
      {required this.model,
      required this.controller,
      this.onPressed,
      this.onRefresh,
      this.onLoading,
      this.child,
      this.failurePage,
      this.emptyPage,
      this.enablePullDown = true,
      this.header,
      this.footer});

  @override
  _StatePageWithViewControllerState createState() =>
      _StatePageWithViewControllerState();
}

class _StatePageWithViewControllerState
    extends State<StatePageWithViewController> {
  @override
  Widget build(BuildContext context) {
    if (widget.model.loadState.value == LoadState.LOADING) {
      return LoadingPage();
    } else if (widget.model.loadState.value == LoadState.EMPTY) {
      return widget.emptyPage ??
          EmptyPage(
            onPressed: widget.onPressed,
          );
    } else if (widget.model.loadState.value == LoadState.FAILURE) {
      return widget.failurePage ??
          NetWorkErrorPage(
            onPressed: widget.onPressed,
            errorMeg: widget.model.errorMessage.value,
          );
    }
    return Expanded(
        child: Container(
      child: SmartRefresher(
          controller: widget.controller,
          enablePullDown: widget.enablePullDown,
          enablePullUp: true,
          onRefresh: widget.onRefresh,
          onLoading: widget.onLoading,
          header: widget.header ?? ClassicHeader(),
          footer: widget.footer ??
              ClassicFooter(
                failedText: widget.model.errorMessage.value,
              ),
          child: widget.child),
    ));
  }
}
