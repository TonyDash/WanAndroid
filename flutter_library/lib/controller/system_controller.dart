import 'package:flutter_library/controller/base_getx_controller.dart';
import 'package:flutter_library/controller/base_getx_controller_with_refresh.dart';
import 'package:flutter_library/model/article_data.dart';
import 'package:flutter_library/model/article_item.dart';
import 'package:flutter_library/model/tip_data.dart';
import 'package:flutter_library/network/http_manager.dart';
import 'package:flutter_library/network/request_api.dart';
import 'package:flutter_library/state/app_state.dart';
import 'package:flutter_library/ui/state_page.dart';
import 'package:get/get.dart';

/**
 * 项目类型
 */
class SystemController extends BaseGetXController {
  var _tipItems = <TipItem>[].obs;

  List<TipItem> get tipItems => _tipItems;

  //获取项目相关类型
  void getSystemData() {
    handleRequest(HttpManager.instance.get(RequestApi.SYSTEM_TREE_API), true,
        (value) {
      _tipItems.value = TipData.fromJson(value).data;
      loadState.value =
          _tipItems.length > 0 ? LoadState.SUCCESS : LoadState.EMPTY;
    });
  }
}

/**
 * 根据项目类型，获取对应的项目列表
 */
class SystemContentController extends BaseGetXControllerWithRefresh {
  var _articleItems = <ArticleItem>[].obs;

  get articleItems => _articleItems;
  int pageIndex = 0;

  //当前类型id
  var _cid = "".obs;

  void setCid(String id) {
    _cid.value = id;
  }

  @override
  void onInit() {
    super.onInit();
    ever(Get.find<AppState>().loginState, (callback) {
      //登录状态发生变化，重置主页数据
      initData(true);
    });
  }

  void getArticleBySystem(bool isShowLoading, {bool refresh = false}) async {
    handleRequest(
        HttpManager.instance.get('article/list/$pageIndex/json?cid=$_cid',
            list: true, refresh: refresh),
        isShowLoading, (value) {
      var result = ArticleData.fromJson(value).data;
      //当前页码
      int curPage = result.curPage;
      //总页数
      int pageCount = result.pageCount;

      if (curPage == 1) {
        _articleItems.clear();
      }
      //文章列表数据
      _articleItems.addAll(result.datas);
      if (curPage == 1 && result.datas.length == 0) {
        loadState.value = LoadState.EMPTY;
      } else if (curPage == pageCount) {
        loadState.value = LoadState.NO_MORE;
        refreshController.loadNoData();
        refreshController.refreshCompleted(resetFooterState: true);
      } else {
        loadState.value = LoadState.SUCCESS;
        refreshController.loadComplete();
        refreshController.refreshCompleted(resetFooterState: true);
        pageIndex++;
      }
    }, failure: (error) {
      refreshController.loadFailed();
      refreshController.refreshFailed();
    });
  }

  @override
  void refresh() {
    pageIndex = 0;
    getArticleBySystem(false, refresh: true);
  }

  @override
  void initData(bool isShowLoading) {
    pageIndex = 0;
    getArticleBySystem(isShowLoading);
  }
}
