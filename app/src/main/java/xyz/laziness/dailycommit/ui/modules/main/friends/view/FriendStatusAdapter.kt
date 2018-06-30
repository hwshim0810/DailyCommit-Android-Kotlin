package xyz.laziness.dailycommit.ui.modules.main.friends.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.item_friends_status.view.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay


class FriendStatusAdapter(private val daysListItems: MutableList<Pair<List<ContributionDay>, String>>)
    : RecyclerView.Adapter<FriendStatusAdapter.FriendsStatusViewHolder>() {

    inner class FriendsStatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (contributions, friendName) = daysListItems[position]

            inflateData(contributions, friendName)
        }

        private fun inflateData(contributions: List<ContributionDay>, friendName: String) {
            Observable.just(itemView.friendContributionsView.onDrawCanvas(contributions))
                    .doOnNext {
                        itemView.friendContributionsView.onDisplay()
                        itemView.textViewFriendName.text = friendName
                    }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, { Log.d("EE", it.message)})
        }

    }

    override fun getItemCount(): Int = this.daysListItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendsStatusViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friends_status, parent, false))

    override fun onBindViewHolder(holder: FriendStatusAdapter.FriendsStatusViewHolder, position: Int) = holder.onBind(position)

    internal fun addContributionToList(contributions: List<ContributionDay>, friendName: String) {
        daysListItems.add(Pair(contributions, friendName))
        notifyItemChanged(itemCount)
    }
}