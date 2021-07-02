import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'article_item.dart';

class ArticleData {
  ArticleResult? _data;
  int _errorCode = 0;
  String _errorMsg = "";

  ArticleResult get data => _data ??= ArticleResult();

  int get errorCode => _errorCode;

  String get errorMsg => _errorMsg;

  ArticleData({ArticleResult? data, int errorCode = 0, String errorMsg = ""}) {
    _data = data;
    _errorCode = errorCode;
    _errorMsg = errorMsg;
  }

  ArticleData.fromJson(dynamic json) {
    _data = json["data"] != null ? ArticleResult.fromJson(json["data"]) : null;
    _errorCode = json["errorCode"];
    _errorMsg = json["errorMsg"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    if (_data != null) {
      map["data"] = _data?.toJson();
    }
    map["errorCode"] = _errorCode;
    map["errorMsg"] = _errorMsg;
    return map;
  }
}

class ArticleResult {
  int _curPage = 0;
  List<ArticleItem>? _datas;
  int _offset = 0;
  bool _over = false;
  int _pageCount = 0;
  int _size = 0;
  int _total = 0;

  int get curPage => _curPage;

  List<ArticleItem> get datas => _datas ??= [];

  int get offset => _offset;

  bool get over => _over;

  int get pageCount => _pageCount;

  int get size => _size;

  int get total => _total;

  ArticleResult(
      {int curPage = 0,
      List<ArticleItem>? datas,
      int offset = 0,
      bool over = false,
      int pageCount = 0,
      int size = 0,
      int total = 0}) {
    _curPage = curPage;
    _datas = datas;
    _offset = offset;
    _over = over;
    _pageCount = pageCount;
    _size = size;
    _total = total;
  }

  ArticleResult.fromJson(dynamic json) {
    _curPage = json["curPage"];
    if (json["datas"] != null) {
      _datas = [];
      json["datas"].forEach((v) {
        _datas?.add(ArticleItem.fromJson(v));
      });
    }
    _offset = json["offset"];
    _over = json["over"];
    _pageCount = json["pageCount"];
    _size = json["size"];
    _total = json["total"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    map["curPage"] = _curPage;
    if (_datas != null) {
      map["datas"] = _datas?.map((v) => v.toJson()).toList();
    }
    map["offset"] = _offset;
    map["over"] = _over;
    map["pageCount"] = _pageCount;
    map["size"] = _size;
    map["total"] = _total;
    return map;
  }
}


class HomeListItemUI extends StatefulWidget {
  final ArticleItem articleItem;
  final GestureTapCallback? onTap;
  final bool isTop;


  HomeListItemUI({required this.articleItem, this.onTap, this.isTop = false});

  @override
  _HomeListItemUIState createState() => _HomeListItemUIState();
}


class _HomeListItemUIState extends State<HomeListItemUI> {
  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.only(top: 15, right: 10, bottom: 5),
        child: InkWell(
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                IconButton(
                    padding: EdgeInsets.all(3.0),
                    icon:widget.articleItem.collect ? Icon(Icons.favorite, color: Colors.red) : Icon(Icons.favorite_border),
                    onPressed: () {
                      // //点击收藏按钮
                      // !widget.articleItem.collect? Get.find<ArticleCollectionController>().collectionArticle(
                      //     widget.articleItem.id.toString(), () {
                      //   widget.articleItem.setCollection(true);
                      //   showToast("收藏成功");
                      //   setState(() {
                      //
                      //   });
                      // }, (value) {
                      //   showToast(value);
                      // }) : Get.find<ArticleCollectionController>().unCollectionArticle(
                      //     widget.articleItem.id.toString(), () {
                      //   //取消收藏
                      //   widget.articleItem.setCollection(false);
                      //   setState(() {
                      //
                      //   });
                      // }, (value) {
                      //   showToast(value);
                      // });
                    }),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        children: [
                          Visibility(
                            //显示“置顶”字样
                              visible: widget.isTop,
                              child: Container(
                                margin: EdgeInsets.only(right: 10),
                                padding:
                                EdgeInsets.symmetric(horizontal: 3),
                                decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(5),
                                    border: Border.all(
                                        color: Colors.red, width: 1)),
                                child: Text(
                                  "置顶",
                                  style: TextStyle(
                                      fontSize: 14, color: Colors.red),
                                ),
                              )),

                          ///第一行 作者
                          Text(
                            widget.articleItem.shareUser.isEmpty
                                ? widget.articleItem.author
                                : widget.articleItem.shareUser,
                            style:
                            TextStyle(fontSize: 14, color: Colors.grey),
                          ),
                          //第一行 右侧 时间
                          Expanded(
                            child: Text(
                              widget.articleItem.niceDate,
                              textAlign: TextAlign.right,
                              style: TextStyle(
                                  fontSize: 14, color: Colors.grey),
                            ),
                          )
                        ],
                      ),
                      Padding(padding: EdgeInsets.only(top: 5)),
                      //中间的标题
                      Text(widget.articleItem.title,
                          style: TextStyle(
                              fontSize: 17,
                              color: Theme.of(context).textTheme.headline1?.color,
                              fontWeight: FontWeight.bold)),
                      Padding(padding: EdgeInsets.only(top: 5)),
                      //底部的文章类型
                      Text(
                        "${widget.articleItem.superChapterName}/${widget.articleItem.chapterName}",
                        style: TextStyle(fontSize: 12, color: Colors.grey),
                      )
                    ],
                  ),
                ),
              ],
            ),
            onTap: widget.onTap??(){
              // Get.toNamed(
              //     RoutesConfig.WEB_PAGE,
              //     arguments: {
              //       ConstantInfo.ARTICLE_TITLE:widget.articleItem.title,
              //       ConstantInfo.ARTICLE_URL:widget.articleItem.link,
              //       ConstantInfo.ARTICLE_AUTHOR:widget.articleItem.shareUser.isEmpty?widget.articleItem.author:widget.articleItem.shareUser,
              //     });
            }));
  }
}
