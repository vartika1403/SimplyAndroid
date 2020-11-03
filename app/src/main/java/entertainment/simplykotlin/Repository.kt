package entertainment.simplykotlin

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job :CompletableJob?=null

    fun getUser(userId: String) :LiveData<User> {
        job = Job()
        return object: LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    job -> CoroutineScope(IO + job).launch {
                    val user = MyRetrofitBuilder.apiService.getUser(userId)
                    withContext(Main) {
                        value = user
                        job.complete()
                    }
                }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}