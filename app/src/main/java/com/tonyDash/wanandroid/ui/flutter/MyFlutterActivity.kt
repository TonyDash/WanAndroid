package com.tonyDash.wanandroid.ui.flutter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tonyDash.wanandroid.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import kotlinx.android.synthetic.main.fragment_system.*

class MyFlutterActivity : AppCompatActivity() {

    lateinit var fragment: FlutterFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_system)
        fragment = MyFlutterFragment(this,parmas = "tiga", routePage = "/system").getFlutterFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.flContainer,
            fragment
        ).commit()
        tvBottom.setOnClickListener {
            Toast.makeText(this,"tvBottom",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        fragment.onPostResume()
    }
}