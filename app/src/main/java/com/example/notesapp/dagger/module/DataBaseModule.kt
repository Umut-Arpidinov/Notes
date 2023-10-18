package com.example.notesapp.dagger.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notesapp.dagger.scope.ApplicationScope
import com.example.notesapp.data.local.database.NotesRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {


    private val MIGRATION_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note_new` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `text` TEXT NOT NULL, `type` TEXT DEFAULT 'DEFAULT');")
            database.execSQL("INSERT INTO `Note_new` (`uid`, `title`, `text`, `type`) SELECT `uid`, `title`, `text`, `type` FROM `notes`;")
            database.execSQL("DROP TABLE `notes`;")
            database.execSQL("ALTER TABLE `Note_new` RENAME TO `notes`;")
        }
    }


    @ApplicationScope
    @Provides
    fun provideNotesDb(context: Context): NotesRoomDatabase {
        return Room.databaseBuilder(context, NotesRoomDatabase::class.java,"NotesDB")
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}