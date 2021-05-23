package trust.androidtask.di

import org.koin.dsl.module
import retrofit2.Retrofit
import trust.androidtask.repository.JobsApi

val apiModule = module {

    fun provideCountriesApi(retrofit: Retrofit): JobsApi {
        return retrofit.create(JobsApi::class.java)
    }
    single { provideCountriesApi(get()) }

}