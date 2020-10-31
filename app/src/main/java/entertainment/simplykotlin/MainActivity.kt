package entertainment.simplykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import entertainment.showToast
import entertainment.simplyandroid.R

class MainActivity : AppCompatActivity() {

    companion object  {
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.d(TAG, "MainActivity onCreate")
        showToast(this,resources.getString(R.string.name))
    }
}