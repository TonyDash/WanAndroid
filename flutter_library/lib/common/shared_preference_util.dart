import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SharedPreferenceUtil{

  /***
   * 根据类型，存储数据
   */
  static void savePreference(String key,Object value) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    if(value is int){
      _sp.setInt(key, value);
    }else if(value is double){
      _sp.setDouble(key, value);
    }else if(value is bool){
      _sp.setBool(key, value);
    }else if(value is String){
      _sp.setString(key, value);
    }else if(value is List<String>){
      _sp.setStringList(key, value);
    }else{
      throw Exception("save preference type error!");
    }
  }


  /***
   * 根据默认值类型和key，获取值
   */
  static Future getPreference(String key,Object defaultValue) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    if(defaultValue is int){
      return _sp.getInt(key);
    }else if(defaultValue is double){
      return _sp.getDouble(key);
    }else if(defaultValue is bool){
      return _sp.getBool(key);
    }else if(defaultValue is String){
      return _sp.getString(key);
    }else if(defaultValue is List<String>){
      return _sp.getStringList(key);
    }else{
      throw Exception("get preference error!");
    }
  }

  static Future<int> getInt(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    return _sp.getInt(key)??0;
  }
  static Future<double> getDouble(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    return _sp.getDouble(key)??0;
  }
  static Future<String> getString(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    return _sp.getString(key)??"";
  }
  static Future<bool> getBool(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    return _sp.getBool(key)??false;
  }
  static Future<List<String>> getStringList(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    return _sp.getStringList(key)??[];
  }

  static removeByKey(String key) async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    _sp.remove(key);
  }

  static cleanPreference() async{
    SharedPreferences _sp = await SharedPreferences.getInstance();
    _sp.clear();
  }
}