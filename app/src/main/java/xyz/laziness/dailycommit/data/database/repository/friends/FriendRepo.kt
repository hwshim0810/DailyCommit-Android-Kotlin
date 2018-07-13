package xyz.laziness.dailycommit.data.database.repository.friends

import io.reactivex.Observable


interface FriendRepo {

    fun insertFriend(friend: Friend): Observable<Pair<Long, Boolean>>

    fun deleteFriend(friend: Friend): Observable<Boolean>

    fun loadFriends(userName: String, currentId: Long): Observable<List<Friend>>

    fun loadFriendByName(userName: String, friendName: String): Observable<Friend>

}