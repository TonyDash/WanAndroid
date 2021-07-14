class TipData {
  List<TipItem>? _data;
  int _errorCode = 0;
  String _errorMsg = "";

  List<TipItem> get data => _data??[];

  int get errorCode => _errorCode;

  String get errorMsg => _errorMsg;

  TipData({List<TipItem>? data, int errorCode = 0, String errorMsg = ""}) {
    _data = data;
    _errorCode = errorCode;
    _errorMsg = errorMsg;
  }

  TipData.fromJson(dynamic json) {
    if (json["data"] != null) {
      _data = [];
      json["data"].forEach((v) {
        _data?.add(TipItem.fromJson(v));
      });
    }
    _errorCode = json["errorCode"];
    _errorMsg = json["errorMsg"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    if (_data != null) {
      map["data"] = _data?.map((v) => v.toJson()).toList();
    }
    map["errorCode"] = _errorCode;
    map["errorMsg"] = _errorMsg;
    return map;
  }
}

class TipItem {
  List<TipItemChildren>? _children;
  int _courseId = 0;
  int _id = 0;
  String _name = "";
  int _order = 0;
  int _parentChapterId = 0;
  bool _userControlSetTop = false;
  int _visible = 0;

  List<TipItemChildren> get children => _children ?? [];

  int get courseId => _courseId;

  int get id => _id;

  String get name => _name;

  int get order => _order;

  int get parentChapterId => _parentChapterId;

  bool get userControlSetTop => _userControlSetTop;

  int get visible => _visible;

  TipItem({List<TipItemChildren>? children,
    int courseId = 0,
    int id = 0,
    String name = "",
    int order = 0,
    int parentChapterId = 0,
    bool userControlSetTop = false,
    int visible = 0}) {
    _children = children;
    _courseId = courseId;
    _id = id;
    _name = name;
    _order = order;
    _parentChapterId = parentChapterId;
    _userControlSetTop = userControlSetTop;
    _visible = visible;
  }

  TipItem.fromJson(dynamic json) {
    if (json["children"] != null) {
      _children = [];
      json["children"].forEach((v) {
        _children?.add(TipItemChildren.fromJson(v));
      });
    }
    _courseId = json["courseId"];
    _id = json["id"];
    _name = json["name"];
    _order = json["order"];
    _parentChapterId = json["parentChapterId"];
    _userControlSetTop = json["userControlSetTop"];
    _visible = json["visible"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    if (_children != null) {
      map["children"] = _children?.map((v) => v.toJson()).toList();
    }
    map["courseId"] = _courseId;
    map["id"] = _id;
    map["name"] = _name;
    map["order"] = _order;
    map["parentChapterId"] = _parentChapterId;
    map["userControlSetTop"] = _userControlSetTop;
    map["visible"] = _visible;
    return map;
  }
}

class TipItemChildren {
  dynamic _children;
  int _courseId = 0;
  int _id = 0;
  String _name = "";
  int _order = 0;
  int _parentChapterId = 0;
  bool _userControlSetTop = false;
  int _visible = 0;

  List<dynamic> get children => _children;

  int get courseId => _courseId;

  int get id => _id;

  String get name => _name;

  int get order => _order;

  int get parentChapterId => _parentChapterId;

  bool get userControlSetTop => _userControlSetTop;

  int get visible => _visible;

  TipItemChildren({List<dynamic>? children,
    int courseId = 0,
    int id = 0,
    String name = "",
    int order = 0,
    int parentChapterId = 0,
    bool userControlSetTop = false,
    int visible = 0}) {
    _children = children;
    _courseId = courseId;
    _id = id;
    _name = name;
    _order = order;
    _parentChapterId = parentChapterId;
    _userControlSetTop = userControlSetTop;
    _visible = visible;
  }

  TipItemChildren.fromJson(dynamic json) {
    _children = json["children"];
    _courseId = json["courseId"];
    _id = json["id"];
    _name = json["name"];
    _order = json["order"];
    _parentChapterId = json["parentChapterId"];
    _userControlSetTop = json["userControlSetTop"];
    _visible = json["visible"];
  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    if (_children != null) {
      map["children"] = _children.map((v) => v.toJson()).toList();
    }
    map["courseId"] = _courseId;
    map["id"] = _id;
    map["name"] = _name;
    map["order"] = _order;
    map["parentChapterId"] = _parentChapterId;
    map["userControlSetTop"] = _userControlSetTop;
    map["visible"] = _visible;
    return map;
  }
}