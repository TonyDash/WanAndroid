import 'package:flutter/material.dart';

class TestPage extends StatelessWidget {
  const TestPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        alignment: Alignment.center,
        child: Text("1234567890-"),
      );
  }
}

class StfTestPage extends StatefulWidget {
  const StfTestPage({Key? key}) : super(key: key);

  @override
  _StfTestPageState createState() => _StfTestPageState();
}

class _StfTestPageState extends State<StfTestPage>
    with TickerProviderStateMixin {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 3, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      //Card,Dialog,Drawer,Scaffold
      child: Card(
        child: TabBar(
          controller: _tabController,
          tabs: [
            Text("111111"),
            Text("222222"),
            Text("333333"),
          ],
        ),
      ),
    );
    // return Container(
    //   height: 50,
    //   child: Container(
    //     height: 100,
    //     child: AppBar(
    //         backgroundColor: Colors.red,
    //         title: Text("test"),
    //         bottom: TabBar(controller: _tabController, tabs: [
    //           Text("111111"),
    //           Text("222222"),
    //           Text("333333"),
    //         ])),
    //   ),
    // );
  }
}
