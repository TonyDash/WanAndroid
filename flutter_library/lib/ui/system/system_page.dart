import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter_library/controller/system_controller.dart';
import 'package:flutter_library/model/tip_data.dart';
import 'package:flutter_library/ui/state_page.dart';
import 'package:flutter_library/ui/system/views/system_content_page.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get/get.dart';

class SystemMainPage extends StatefulWidget {
  const SystemMainPage({Key? key}) : super(key: key);

  @override
  _SystemMainPageState createState() => _SystemMainPageState();
}

class _SystemMainPageState extends State<SystemMainPage>
    with AutomaticKeepAliveClientMixin, TickerProviderStateMixin {
  var _systemController = Get.put<SystemController>(SystemController());
  late TabController _tabController;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return GetX<SystemController>(
        initState: (_) {_systemController.getSystemData();},
        builder: (controller) {
          return DefaultTabController(
              length: controller.tipItems.length,
              child: Builder(builder: (context) {
                _tabController =TabController(length: controller.tipItems.length, vsync: this);
                  return Scaffold(
                    appBar: AppBar(
                      title: Text("体系"),
                      bottom: _buildSystemTabs(controller.tipItems),
                    ),
                    body: Builder(builder: (context){
                      if (_systemController.loadState.value == LoadState.LOADING) {
                        return LoadingPage();
                      }
                      if (_systemController.loadState.value == LoadState.EMPTY) {
                        return EmptyPage();
                      } else if (_systemController.loadState.value == LoadState.FAILURE) {
                        return Text("请求失败");
                      }
                      return TabBarView(
                        controller: _tabController,
                        children: controller.tipItems.map((e) {
                          return _buildSystemContent(e);
                        }).toList(),
                      );
                    }),
                  );
                }));
        });
  }

  ///构建顶部的tbs
  TabBar _buildSystemTabs(List<TipItem> systems) {
    return TabBar(
      isScrollable: true,
      controller: _tabController,
      tabs: systems.map((e) {
        return Tab(
          text: e.name,
        );
      }).toList(),
    );
  }

  ///构建体系内容页
  Widget _buildSystemContent(TipItem system){
    return  Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Container(
          height: 60,
          child: _buildTipItemChildren(system.children),
        ),
        SystemContentPage( system.children.isNotEmpty?system.children[0].id.toString() : system.id.toString() )
      ],
    );
  }

  ///体系下方的子类型列表
  Widget _buildTipItemChildren(List<TipItemChildren> tipItemChildren){
    List<Widget> _children = [];
    for(int i=0;i<tipItemChildren.length;i++){
      TipItemChildren children = tipItemChildren[i];
      var chip = ActionChip(
          elevation: 2.0,
          backgroundColor: Colors.transparent,
          label: Text(children.name),
          onPressed: (){
            Fluttertoast.showToast(msg: "${children.name +"   "+ children.id.toString()}");
      });
      _children.add(chip);
    }
    return ListView.separated(
        scrollDirection: Axis.horizontal,
        itemBuilder: (context,index){
          return _children[index];
        },
        separatorBuilder: (context,index){
          return VerticalDivider(color: Colors.transparent,width: 5,);
        },
        itemCount: _children.length);
  }

  @override
  bool get wantKeepAlive => true;
}

