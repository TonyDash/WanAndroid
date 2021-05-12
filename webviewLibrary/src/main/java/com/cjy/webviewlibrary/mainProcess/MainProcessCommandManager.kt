package com.cjy.webviewlibrary.mainProcess
import com.cjy.baselibrary.utils.GsonUtil
import com.cjy.webviewlibrary.I2MainProcessAidlInterface
import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface
import com.cjy.webviewlibrary.command.ICommand
import java.util.*
import kotlin.collections.HashMap

class MainProcessCommandManager : I2MainProcessAidlInterface.Stub {

    private val commands:HashMap<String, ICommand> = HashMap()

    private constructor():super(){
        val serviceLoader:ServiceLoader<ICommand> = ServiceLoader.load(ICommand::class.java)
        for (iCommand in serviceLoader) {
            if (!commands.containsKey(iCommand.name())){
                commands[iCommand.name()] = iCommand
            }
        }
    }

    override fun handleWebCommand(commandName: String, jsonParams: String,callback: I2WebViewProcessAidlInterface) {
        Instance.executeCommand(
            commandName,
            GsonUtil.instance.parse(jsonParams, Map::class.java),
            callback
        )
    }

    private fun executeCommand(commandName: String, params: Map<*, *>?,callback: I2WebViewProcessAidlInterface) {
        commands[commandName]?.execute(params,callback)
    }

    companion object {
        val Instance = Holder.holder
    }

    private object Holder {
        val holder = MainProcessCommandManager()
    }
}