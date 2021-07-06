package com.tonyDash.wanandroid.ui.flutter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tonyDash.wanandroid.R
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.fragment_system.*

class MyFlutterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_system)
        supportFragmentManager.beginTransaction().replace(
            R.id.flContainer,
            MyFlutterFragment(this, routePage = "/system").getFlutterFragment()
        ).commit()
        tvBottom.setOnClickListener {
            Toast.makeText(this,"tvBottom",Toast.LENGTH_SHORT).show()
        }
    }
}