package trust.androidtask.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import trust.androidtask.db.JobDao
import trust.androidtask.repository.JobsApi
import trust.androidtask.repository.JobsRepository
import trust.androidtask.repository.JobsRepositoryImpl

val repositoryModule = module {

    fun provideCountryRepository(api: JobsApi, context: Context, dao : JobDao): JobsRepository {
        return JobsRepositoryImpl(api, context, dao)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }

}