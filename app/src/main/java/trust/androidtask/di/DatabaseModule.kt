package trust.androidtask.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import trust.androidtask.db.JobDao
import trust.androidtask.db.JobsDataBase

val databaseModule = module {

    fun provideDatabase(application: Application): JobsDataBase {
       return Room.databaseBuilder(application, JobsDataBase::class.java, "jobs_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: JobsDataBase): JobDao {
        return  database.getJobDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}
