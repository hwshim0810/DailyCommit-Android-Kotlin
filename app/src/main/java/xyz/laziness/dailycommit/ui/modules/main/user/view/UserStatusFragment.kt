package xyz.laziness.dailycommit.ui.modules.main.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.presenter.UserStatusPresenter
import javax.inject.Inject


class UserStatusFragment : BaseFragment(), UserStatusView {

    companion object {

        internal const val TAG = "UserStatusFragment"

        fun getInstance(): UserStatusFragment = UserStatusFragment()
    }

    @Inject
    internal lateinit var presenter: UserStatusPresenter<UserStatusView, UserStatusInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_user_status, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}