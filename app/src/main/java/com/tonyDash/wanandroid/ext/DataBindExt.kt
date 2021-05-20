package com.tonyDash.wanandroid.ext

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.cjy.baselibrary.AppContext
import com.cjy.baselibrary.baseExt.otherwise
import com.cjy.baselibrary.baseExt.yes
import com.tonyDash.wanandroid.GlideApp
import com.tonyDash.wanandroid.R
import com.cjy.networklibrary.entity.Article
import com.cjy.networklibrary.entity.Tag

//DataBinding自定义属性
@BindingAdapter("url")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(AppContext).load(url)
        .placeholder(R.mipmap.ic_launcher)
        .into(imageView)
}

@BindingAdapter("tag")
fun loadTag(textView:TextView,tags:List<Tag>){
    tags.isEmpty().yes {
        textView.visibility = View.GONE
    }.otherwise {
        textView.visibility = View.VISIBLE
        textView.text = tags[0].name
    }
}

@BindingAdapter("author")
fun loadAuthor(textView:TextView,item: Article){
    textView.text = when {
        !item.author.isNullOrEmpty() -> {
            item.author
        }
        !item.shareUser.isNullOrEmpty() -> {
            item.shareUser
        }
        else -> AppContext.getString(R.string.anonymous)
    }
}

@BindingAdapter("htmlText")
fun formatHtmlText(textView: TextView,content:String){
    textView.text = content.htmlToSpanned()
}

@BindingAdapter("descVisible")
fun checkArticleDesc(textView: TextView,content:String){
    textView.visibility = content.isEmpty().yes {
        View.GONE
    }.otherwise {
        View.VISIBLE
    }
}

@BindingAdapter("loginVisible")
fun setViewVisible(textView: TextView,isLogin:Boolean){
    textView.visibility = isLogin.yes {
        View.VISIBLE
    }.otherwise {
        View.GONE
    }
}

@BindingAdapter("textValue")
fun setTextValue(textView: TextView,strValue:String){
    textView.text = strValue
}

@BindingAdapter("textValue")
fun setTextValue(textView: TextView,intValue:Int){
    textView.text = intValue.toString()
}

@BindingAdapter("textValue")
fun setTextValue(textView: TextView,longValue:Long){
    textView.text = longValue.toString()
}


////DataBinding类型转换
//@BindingConversion
//fun setReposLanguageBg(reposItemModel: ReposItemModel): Drawable? =
//    reposItemModel.language?.let {
//        when (it) {
//            "Java" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_java_circular_bg)
//            "Kotlin" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_kotlin_circular_bg)
//            "Swift" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_swift_circular_bg)
//            "HTML" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_html_circular_bg)
//            "JavaScript" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_js_circular_bg)
//            "CSS" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_css_circular_bg)
//            "PHP" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_php_circular_bg)
//            "TypeScript" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_ts_circular_bg)
//            "Python" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_python_circular_bg)
//            "C" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_c_circular_bg)
//            "C++" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_c_plus_circular_bg)
//            "C#" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_co_circular_bg)
//            "Objective-C" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_oc_circular_bg)
//            "Go" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_go_circular_bg)
//            "R" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_r_circular_bg)
//            "Shell" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_shell_circular_bg)
//            "Ruby" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_ruby_circular_bg)
//            "Vue" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_vue_circular_bg)
//            "Dart" -> ContextCompat.getDrawable(AppContext, R.drawable.shape_dart_circular_bg)
//            else -> ContextCompat.getDrawable(AppContext, R.drawable.shape_html_circular_bg)
//        }
//    }
//
//@BindingConversion
//fun setReceivedEventImage(receivedEventModel: ReceivedEventModel): Drawable? =
//    when (receivedEventModel.type) {
//        "WatchEvent" -> ContextCompat.getDrawable(AppContext, R.mipmap.icon_star_yellow)
//        "ForkEvent", "PushEvent", "CreateEvent" -> ContextCompat.getDrawable(
//            AppContext,
//            R.mipmap.icon_fork_green
//        )
//        else -> null
//    }
//
//@BindingConversion
//fun setReceivedEventContent(receivedEventModel: ReceivedEventModel): CharSequence {
//    val actor = receivedEventModel.actor.display_login
//    val action = when (receivedEventModel.type) {
//        "WatchEvent" -> "starred"
//        "CreateEvent" -> "created"
//        "ForkEvent" -> "forked"
//        "PushEvent" -> "pushed"
//        else -> receivedEventModel.type
//    }
//
//    val repo = receivedEventModel.repo.name
//
//    //利用SpannableStringBuilder处理富文本
//    return SpannableStringBuilder().apply {
//        append("$actor $action $repo")//添加文本内容
//        //设置文本样式
//        setSpan(StyleSpan(Typeface.BOLD), 0, actor.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        setSpan(
//            StyleSpan(Typeface.BOLD),
//            actor.length + action.length + 2,
//            actor.length + action.length + repo.length + 2,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
//    }
//}