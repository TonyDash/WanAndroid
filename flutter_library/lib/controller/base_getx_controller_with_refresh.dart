import 'package:flutter_library/controller/base_getx_controller.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

abstract class BaseGetXControllerWithRefresh extends BaseGetXController{

  late RefreshController _refreshController;
  RefreshController get refreshController => _refreshController;

  @override
  void onInit() {
    super.onInit();
    _refreshController = RefreshController(initialRefresh: false);
  }

  @override
  void dispose() {
    super.dispose();
    _refreshController.dispose();
  }
}