package tw.edu.ncku.gs.r76121243.ch04

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    // Step1. 宣告 ActivityResultLauncher，內部負責處理 SecActivity 回傳結果
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result: ActivityResult ->
        // Step12. 判斷回傳結果是否為 RESULT_OK，若是則執行以下程式碼
        if (result.resultCode == Activity.RESULT_OK) {
            // Step13. 取得回傳的 Intent，並從 Intent 中取得飲料名稱、甜度、冰塊
            val intent = result.data
            val drink = intent?.getStringExtra("drink")
            val sugar = intent?.getStringExtra("sugar")
            val ice = intent?.getStringExtra("ice")
            // Step14. 設定 tvMeal 的文字
            val tvMeal = findViewById<TextView>(R.id.tvMeal)
            tvMeal.text = "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Step2. 定義元件變數，並透過 findViewById 取得元件
        val btnChoice = findViewById<Button>(R.id.btnChoice)
        // Step3. 設定 btnChoice 的點擊事件
        btnChoice.setOnClickListener {
            // Step4. 宣告 Intent，透過 Intent 從 MainActivity 切換到 SecActivity
            val intent = Intent(this, SecActivity::class.java)
            // Step5. 使用 startForResult 啟動 Intent
            startForResult.launch(intent)
        }
    }
}