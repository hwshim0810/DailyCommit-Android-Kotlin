package xyz.laziness.dailycommit.ui.modules.main.friends.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenter
import javax.inject.Inject


class FriendsStatusFragment : BaseFragment(), FriendsStatusView {

    companion object {

        internal const val TAG = "FriendsStatusFragment"

        fun getInstance(): FriendsStatusFragment = FriendsStatusFragment()

    }

    @Inject
    internal lateinit var presenter: FriendsStatusPresenter<FriendsStatusView, FriendsStatusInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_friends_status, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}