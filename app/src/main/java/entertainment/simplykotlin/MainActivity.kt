package entertainment.simplykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import entertainment.showToast
import entertainment.simplyandroid.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    companion object  {
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.d(TAG, "MainActivity onCreate")
        showToast(this,resources.getString(R.string.name))

        button.setOnClickListener {
            //IO ,Main, Defaukt
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
            }
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Main){
            setText(input)
        }
    }

    private fun setText(result:String) {
        user_name_text.setText("result: ${result}");
    }

    private suspend fun fakeApiRequest() {
        val result = getResult1FromApi()
        println("debug: result:: ${result}")
        setTextOnMainThread(result)
        val result2 = getResult2FromApi()
        println("debug: result2:: ${result2}")
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi():String {
       logThreadName("getResult1FromApi")
        delay(1000)
        return RESULT_1
    }

    private suspend fun getResult2FromApi():String {
        logThreadName("getResult2FromApi")
        delay(1000)
        return RESULT_2
    }

    private fun logThreadName(methodName:String) {
        println("debug: ${methodName} and Thread ${Thread.currentThread().name}")
    }
}