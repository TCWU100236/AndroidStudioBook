package tw.edu.ncku.gs.r76121243.ch04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        // 對按鈕設定監聽器，判斷是否輸入飲料名稱，並取得 RadioGroup 資訊
        // Step6. 定義元件變數，並透過 findViewById 取得元件
        val edDrink = findViewById<TextView>(R.id.edDrink)
        val rgSugar = findViewById<RadioGroup>(R.id.rgSugar)
        val rgIce = findViewById<RadioGroup>(R.id.rgIce)
        val btnSend = findViewById<Button>(R.id.btnSend)

        // Step7. 設定 btnSend 點擊事件
        btnSend.setOnClickListener {
            // Step8. 如果 edDrink 為空，則顯示提示文字
            if (edDrink.text.isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            } else {
                val b = bundleOf(
                    "drink" to edDrink.text.toString(),
                    "sugar" to rgSugar.findViewById<RadioButton>(rgSugar.checkedRadioButtonId).text.toString(),
                    "ice" to rgIce.findViewById<RadioButton>(rgIce.checkedRadioButtonId).text.toString()
                )
                //Step10. 宣告 Intent，並將 Bundle 放入 Intent 中
                val i = Intent().putExtras(b)
                //Step11. 設定 Activity 的結果，並關閉 SecActivity
                setResult(RESULT_OK, i)
                finish()
            }
        }
    }

}