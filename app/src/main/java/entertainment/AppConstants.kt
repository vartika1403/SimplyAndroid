package entertainment

import android.content.Context
import android.widget.Toast

object Constants {
     const val USER_MSG_KEY = "user_message"
 }

fun showToast(context: Context,value: String) {
     Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
}