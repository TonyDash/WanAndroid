import 'package:flutter/material.dart';
import 'package:flutter_library/controller/system_controller.dart';
import 'package:flutter_library/model/article_data.dart';
import 'package:flutter_library/model/article_item.dart';
import 'package:flutter_library/ui/state_page.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get/state_manager.dart';
import 'package:get/get.dart';

class SystemContentPage extends StatefulWidget {
  final String typeId;

  const SystemContentPage(this.typeId, {Key? key}) : super(key: key);

  @override
  _SystemContentPageState createState() => _SystemContentPageState();
}

class _SystemContentPageState extends State<SystemContentPage>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    return GetX<SystemContentController>(
        //加上tag属性，不然每次调用Get.find<SystemContentController>找到的都是同一个对象，那么refreshcontroller和loadstate会有冲突
        init: Get.put<SystemContentController>(SystemContentController(),
            tag: widget.typeId),
        initState: (_) {
          var controller =
              Get.find<SystemContentController>(tag: widget.typeId);
          controller.setCid(widget.typeId);
          controller.initData(true);
        },
        builder: (controller) {
          return StatePageWithViewController<SystemContentController>(
              model: Get.find<SystemContentController>(tag: widget.typeId),
              controller: Get.find<SystemContentController>(tag: widget.typeId)
                  .refreshController,
              onPressed: () {
                Get.find<SystemContentController>(tag: widget.typeId).getArticleBySystem(true);
              },
              onRefresh: () async {
                Get.find<SystemContentController>(tag: widget.typeId).refresh();
              },
              onLoading: () async {
                Get.find<SystemContentController>(tag: widget.typeId).getArticleBySystem(false);
              },
              child: ListView.builder(
                itemBuilder: (context, index) {
                  return HomeListItemUI(
                      articleItem: Get.find<SystemContentController>(tag: widget.typeId).articleItems[index]);
                },
                itemCount: Get.find<SystemContentController>(tag: widget.typeId).articleItems.length,
              ));
        });
  }

  @override
  bool get wantKeepAlive => true;
}
