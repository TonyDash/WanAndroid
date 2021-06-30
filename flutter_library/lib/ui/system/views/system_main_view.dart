import 'package:flutter/material.dart';
import 'package:flutter_library/string/string.dart';

class SystemMainView extends StatefulWidget {
  const SystemMainView({Key? key}) : super(key: key);

  @override
  _SystemMainViewState createState() => _SystemMainViewState();
}

class _SystemMainViewState extends State<SystemMainView>
    with AutomaticKeepAliveClientMixin, TickerProviderStateMixin {
  late TabController _tabController;
  List<String> _projectData = [];
  List<String> _projectListData = [];

  @override
  void initState() {
    super.initState();
    _projectData.add("1");
    _projectData.add("2");
    _projectData.add("3");
    _projectListData.add("value 1");
    _projectListData.add("value 2");
    _projectListData.add("value 3");
    _tabController = new TabController(length: _projectData.length, vsync: this);
  }

  @override
  // ignore: must_call_super
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(DString.SYSTEM),
        bottom: TabBar(
          tabs: _projectData.map((String name) {
            return Tab(
              text: name,
            );
          }).toList(),
          controller: _tabController,
        ),
      ),
      body: TabBarView(
        controller: _tabController,
        children: _projectListData.map((String content) {
          return Text(content);
        }).toList(),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
