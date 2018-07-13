package xyz.laziness.dailycommit.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import xyz.laziness.dailycommit.data.database.repository.friends.Friend
import xyz.laziness.dailycommit.data.database.repository.friends.FriendDao


@Database(entities = [(Friend::class)], version = 1)
abstract class DailyCommitDatabase : RoomDatabase() {

    abstract fun friendsDao(): FriendDao

}