package com.cjy.networklibrary.exception

class ApiException(var code: Int, override var message: String) : RuntimeException()