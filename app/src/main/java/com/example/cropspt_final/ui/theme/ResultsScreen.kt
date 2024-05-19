package com.example.cropspt_final.ui.theme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cropspt_final.R
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlin.random.Random
import com.example.cropspt_final.ui.theme.MainActivity


class ResultsScreen :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val recArray = resources.getStringArray(R.array.recommendation_array).toList()
        val classNames = intent.getStringArrayListExtra("classNames")
        val uniqueClassNames = classNames?.distinct()
        val spoilagePercentage = intent.getIntExtra("spoilagePercentage", 0)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_bottom_sheet)

        // analysis text
        if (classNames != null) {
            classNames.forEach { className ->
                Log.i("ResultsScreen", "Class: $className")
            }
            // Display the results in a LinearLayout
            val container: LinearLayout = findViewById(R.id.analysis_text_container)
            if (uniqueClassNames != null) {
                uniqueClassNames.forEach { className ->
                    val textView = TextView(this).apply {
                        text = "$className"
                        setTextColor(resources.getColor(R.color.lightGreen, null))
                        background = ContextCompat.getDrawable(context, R.drawable.dark_rounded_background)
                        setPadding(10, 10, 10, 10)
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        params.setMargins(0, 20, 0, 10)
                    }
                    container.addView(textView)
                }
            }
        } else {
            Log.i("ResultsScreen", "No class names received")
        }

        //spoilage percentage

        val container: RelativeLayout = findViewById(R.id.percentage_container)
        if (spoilagePercentage != null) {
            val textView = TextView(this).apply {
                text = "These spoilage indicators, based on the image, span around $spoilagePercentage% of the whole lettuce"
                setTextColor(resources.getColor(R.color.darkGreen, null))
                setPadding(10, 10, 10, 10)
            }
            container.addView(textView)
        } else {
            Log.i("ResultsScreen", "No percentage received")
        }

        //recommendation text
        val recommendationText1 = findViewById<TextView>(R.id.recommendation_text1)
        val recommendationText2 = findViewById<TextView>(R.id.recommendation_text2)
        val recommendationText3 = findViewById<TextView>(R.id.recommendation_text3)
        val randomTips = recArray.shuffled().take(3)
        recommendationText1.text = randomTips[0]
        recommendationText2.text = randomTips[1]
        recommendationText3.text = randomTips[2]





        fun goToMainActivity() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

