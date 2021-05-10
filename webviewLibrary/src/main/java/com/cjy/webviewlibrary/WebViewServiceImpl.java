package com.cjy.webviewlibrary;

import android.app.Activity;
import android.content.Intent;

import com.cjy.commonlibrary.autoservice.IWebViewService;
import com.cjy.webviewlibrary.utils.IntentUtil;
import com.google.auto.service.AutoService;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(@NotNull Activity fromActivity, @NotNull Map<String, ?> params) {
        Intent intent = new Intent(fromActivity,WebViewActivity.class);
        Intent intentByParams = IntentUtil.addParams(intent, params);
        fromActivity.startActivity(intentByParams);
    }
}
