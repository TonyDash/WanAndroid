package com.tonyDash.wanandroid.command;

import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface;
import com.cjy.webviewlibrary.command.ICommand;
import com.google.auto.service.AutoService;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@AutoService(ICommand.class)
public class CollectCommand implements ICommand {
    @NotNull
    @Override
    public String name() {
        return "collect";
    }

    @Override
    public void execute(@Nullable Map<?, ?> params, @NotNull I2WebViewProcessAidlInterface callback) {

    }
}
