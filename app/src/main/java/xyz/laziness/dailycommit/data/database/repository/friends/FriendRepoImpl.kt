package xyz.laziness.dailycommit.data.database.repository.friends

import io.reactivex.Observable
import javax.inject.Inject


class FriendRepoImpl @Inject constructor(private val friendDao: FriendDao) : FriendRepo {

    override fun insertFriend(friend: Friend): Observable<Boolean> {
        friendDao.insertFriend(friend)
        return Observable.just(true)
    }

    override fun deleteFriend(friend: Friend): Observable<Boolean> {
        friendDao.deleteFriend(friend)
        return Observable.just(true)
    }

    override fun loadFriends(userName: String): Observable<List<Friend>> =
            Observable.fromCallable { friendDao.loadAllFriendsByUsername(userName) }
}