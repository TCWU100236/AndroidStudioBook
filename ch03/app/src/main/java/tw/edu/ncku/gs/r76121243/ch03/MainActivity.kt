package tw.edu.ncku.gs.r76121243.ch03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Step1. 定義元件變數，並透過 findViewById 取得元件
        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnMora = findViewById<Button>(R.id.btnMora)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvTargetMora = findViewById<TextView>(R.id.tvTargetMora)

        // Step2. 設定 btnMora 的點擊事件
        btnMora.setOnClickListener {
            // Step3. 如果 edName 為空，則顯示提示文字
            if (edName.text.isEmpty()) {
                tvText.text = "請輸入玩家名稱"
                return@setOnClickListener
            }

            // Step4. 從 edName 取得玩家姓名
            val playerName = edName.text.toString()

            // Step5. 使用 (0...2).random() 回傳 0~2 的整數，以此做為電腦的出拳
            val targetMora = (0..2).random()

            // Step6. 透過 radioGroup.checkedRadioButtonId 取得選取的 RadioButton
            val myMora = when (radioGroup.checkedRadioButtonId) {
                R.id.btnScissor -> 0
                R.id.btnStone -> 1
                else -> 2
            }

            // Step8. 設定玩家名稱、我方出拳、電腦出拳文字
            tvName.text = "名字\n$playerName"
            tvMyMora.text = "我方出拳\n${getMoraString(myMora)}"
            tvTargetMora.text = "電腦出拳\n${getMoraString(targetMora)}"

            // Step9. 判斷勝利者
            when {
                myMora == targetMora -> {
                    tvWinner.text = "勝利者\n平手"
                    tvText.text = "平局，請再試一次!"
                }
                (myMora == 0 && targetMora == 2) || (myMora == 1 && targetMora == 0) || (myMora == 2 && targetMora == 1) -> {
                    tvWinner.text = "勝利者\n$playerName"
                    tvText.text = "恭喜你獲勝了!!"
                }
                else -> {
                    tvWinner.text = "勝利者\n電腦"
                    tvText.text = "你輸了!!"
                }
            }
        }
    }

    // Step7 傳入 0, 1, 2，回傳對應的文字，分別是剪刀、石頭、布
    private fun getMoraString(mora: Int): String {
        return when (mora) {
            0 -> "剪刀"
            1 -> "石頭"
            else -> "布"
        }
    }
}