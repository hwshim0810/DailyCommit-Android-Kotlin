package xyz.laziness.dailycommit.data.database.repository.friends

import android.arch.persistence.room.*


@Dao
interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend: Friend): Long

    @Delete
    fun deleteFriend(friend: Friend)

    @Query("SELECT * FROM friends WHERE user_name = :userName and id > :currentId ORDER BY id LIMIT 5")
    fun loadAllFriendsByUsername(userName: String, currentId: Long): List<Friend>

    @Query("SELECT * FROM friends WHERE user_name = :userName and friend_name = :friendName")
    fun loadFriendByName(userName: String, friendName: String): Friend

}