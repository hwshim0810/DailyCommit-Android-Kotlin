package xyz.laziness.dailycommit.ui.modules.main.friends.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_friends_status.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.custom.swipe.SwipeController
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenter
import xyz.laziness.dailycommit.ui.modules.main.view.MainActivity
import javax.inject.Inject


class FriendsStatusFragment : BaseFragment(), FriendsStatusView {

    companion object {

        internal const val TAG = "FriendsStatusFragment"

        fun getInstance(): FriendsStatusFragment = FriendsStatusFragment()

    }

    @Inject
    internal lateinit var presenter: FriendsStatusPresenter<FriendsStatusView, FriendsStatusInteractor>
    @Inject
    internal lateinit var friendStatusAdapter: FriendStatusAdapter
    @Inject
    internal lateinit var friendStatusLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_friends_status, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initUI() {
        initFriendsStatusRecyclerView()
        addOnClickListeners()
        setProgressBar(graphProgress)

        presenter.run {
            doMyContributionRequest()
            doFriendsContributionRequest()
        }
    }

    override fun displayMyContributions(contributions: List<ContributionDay>) {
        if (myContributionsView == null) return

        Observable.just(myContributionsView.onDrawCanvas(contributions))
                .doOnNext { myContributionsView.takeIf { it != null }?.onDisplay() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { this.showErrorMessage() })
    }

    override fun displayFriendContributions(contributions: List<ContributionDay>, friendName: String) {
        friendStatusAdapter.addContributionToList(contributions, friendName)
    }

    override fun onResponseDeleteFriend(isSuccess: Boolean, pos: Int) {
        if (isSuccess) friendStatusAdapter.removeFriend(pos)
    }

    private fun initFriendsStatusRecyclerView() {
        friendStatusLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerFriendsStatus.layoutManager = friendStatusLayoutManager
        recyclerFriendsStatus.adapter = friendStatusAdapter
        recyclerFriendsStatus.itemAnimator = DefaultItemAnimator()
        recyclerFriendsStatus.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        context?.run {
            val swipeController = object : SwipeController(this) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = recyclerFriendsStatus.adapter as FriendStatusAdapter
                    presenter.onFriendDeleteButtonClicked(adapter.getFriendName(viewHolder.adapterPosition), viewHolder.adapterPosition)
                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeController)
            itemTouchHelper.attachToRecyclerView(recyclerFriendsStatus)
        }

    }

    private fun addOnClickListeners() {
        addFriendLayout.setOnClickListener {
            val activity = getBaseActivity() as MainActivity
            activity.showAddFriendDialog()
        }
    }
}