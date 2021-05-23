package trust.androidtask.repository

import retrofit2.Response
import retrofit2.http.GET
import trust.androidtask.model.Job

interface JobsApi {

    @GET("/positions.json?description=api")
    suspend fun getAllCountries(): Response<List<Job>>
}