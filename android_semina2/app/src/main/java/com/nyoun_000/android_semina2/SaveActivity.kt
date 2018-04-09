package com.nyoun_000.android_semina2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_save.*

class SaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        val intent = intent
        iv_save.setImageResource(intent.getIntExtra("save_image", 0))
    }
}
