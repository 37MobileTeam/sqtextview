package com.sq.mobile.sqtextview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val desc =
            "37手游成立于2013年6月，是三七互娱旗下独立运营的核心子公司。2019年37手游的经营收入占三七互娱集团营业总收入的比例超过70%，在中国国内移动游戏发行市场上，37手游以超过10%的占有率居腾讯和网易之后、位列第三。" +
                    "37手游目前累计运营近2000款游戏、月活跃用户超过3000万，成功推出《永恒纪元》、《一刀传世》、《斗罗大陆》H5、《精灵盛典：黎明》、《混沌起源》、《大天使之剑H5》等诸多优秀作品。成龙、李连杰，以及黎明、王宝强、黎耀祥等均是37手游的形象代言人。" +
                    "37手游以领先的游戏运营能力、市场推广能力、广告设计能力为游戏行业普遍认可和熟知，提出立体化、AI智能化营销的“流量经营”策略，也为众多优秀厂商纷纷学习和效仿。"

        //有置顶的
        contentOnTop(findViewById(R.id.content1), desc)

        //没有置顶的
        val t = findViewById<CollapsedTextView>(R.id.content2)
        t.text = desc
    }


    private fun contentOnTop(tv: TextView, content: String) {
        val spannableString = SpannableString("   $content")
        val drawable = ContextCompat.getDrawable(this, R.drawable.icon_txt_on_top)
        drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        spannableString.setSpan(
            drawable?.let { VerticalImageSpan(it) },
            0,
            1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv.text = spannableString
    }


    inner class VerticalImageSpan : ImageSpan {

        constructor(drawable: Drawable) : super(drawable)

        constructor(b: Bitmap, context: Context) : super(context, b)

        /**
         * @param start
         * @param end
         * 例子：0，1：代表的开始位置从0开始，到第1个位置结束，也就是前面第一个位置加图标
         * 例子：-1，字符串长度：在最后面加上图标
         * */
        override fun draw(
            @NonNull canvas: Canvas, text: CharSequence,
            start: Int,
            end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int,
            @NonNull paint: Paint
        ) {

            val b = drawable
            val fm = paint.fontMetricsInt
            val transY = (y + fm.descent + y + fm.ascent) / 2 - b.bounds.bottom / 2
            canvas.save()
            canvas.translate(x, transY.toFloat())
            b.draw(canvas)
            canvas.restore()
        }
    }
}
