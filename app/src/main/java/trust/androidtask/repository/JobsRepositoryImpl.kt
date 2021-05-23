package trust.androidtask.repository

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import trust.androidtask.db.JobDao
import trust.androidtask.model.Job
import trust.androidtask.util.AppResult
import trust.androidtask.util.NetworkManager.isOnline
import trust.androidtask.util.TAG
import trust.androidtask.util.Utils.handleApiError
import trust.androidtask.util.Utils.handleSuccess
import trust.androidtask.util.noNetworkConnectivityError

class JobsRepositoryImpl(
    private val api: JobsApi,
    private val context: Context,
    private val dao: JobDao
) :
    JobsRepository {

    override suspend fun getAllJobs(): AppResult<List<Job>> {
        return if (isOnline(context)) {
            return try {
                val response = api.getAllCountries()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.addJobs(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
//            //check in db if the data exists
//            val data = getCountriesDataFromCache()
//            return if (data.isNotEmpty()) {
//                Log.d(TAG, "from db")
//                AppResult.Success(data)
//            } else
            //no network
            context.noNetworkConnectivityError()
        }
    }

//    private suspend fun getCountriesDataFromCache(): Flow<List<Job>> {
//        return withContext(Dispatchers.IO) {
//            dao.getAllJobs()
//        }
//    }

/*
This is another way of implementing where the source of data is db and api but we can always fetch from db
which will be updated with the latest data from api and also change findAll() return type to
LiveData<List<CountriesData>>
*/
    /* val data = dao.findAll()

     suspend fun getAllCountriesData() {
         withContext(Dispatchers.IO) {
             val response = api.getAllCountries()
             response.body()?.let {
                 withContext(Dispatchers.IO) { dao.add(it) }
             }
         }
     }*/

}