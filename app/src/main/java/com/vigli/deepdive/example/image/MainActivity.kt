package com.vigli.deepdive.example.image

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.vigli.deepdive.example.image.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Intent(this, ImageActivity::class.java)

        getNaviData().forEach { navi ->
            addNaviButtons(navi)
        }
    }

    private fun getNaviData(): List<Navi> = arrayListOf(
        Navi("이미지", ImageActivity::class.java),
        Navi("리사이클러뷰", RecyclerViewScrollingActivity::class.java),
        Navi("스크롤뷰", ScrollingActivity::class.java)
    )

    private fun addNaviButtons(navi: Navi) {
        binding.llContainer.addView(
            newNaviButton(navi)
        )
    }

    private fun newNaviButton(navi: Navi): AppCompatButton = AppCompatButton(this).apply {
        text = navi.btnText
        textSize = 14f
        setTextColor(
            ContextCompat.getColor(this@MainActivity, R.color.black)
        )

        setOnClickListener {
            startActivity(
                Intent(this@MainActivity, navi.targetClass)
            )
        }

        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        ).apply {
            val dp16 = 16.toDp(this@MainActivity).toInt()
            val dp4 = 4.toDp(this@MainActivity).toInt()

            setMargins(
                dp16, dp4, dp16, dp4
            )
        }
    }

    data class Navi(
        val btnText: String,
        val targetClass: Class<*>
    )
}