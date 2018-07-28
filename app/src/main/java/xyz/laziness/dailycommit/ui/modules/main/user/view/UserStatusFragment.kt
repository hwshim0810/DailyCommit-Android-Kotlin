package xyz.laziness.dailycommit.ui.modules.main.user.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_status.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.presenter.UserStatusPresenter
import xyz.laziness.dailycommit.ui.modules.main.view.MainActivity
import xyz.laziness.dailycommit.utils.extensions.loadCircleImage
import xyz.laziness.dailycommit.utils.progress.ProgressHelper
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
        val parent = activity as MainActivity
        parent.userInfo?.run { setUiData(this) } ?: presenter.userInfoRequest()
    }

    override fun setUiData(userInfoResponse: UserInfoResponse) {
        val context = context
        userInfoResponse.apply {
            val progress = ProgressHelper.getCircularProgress(context)
            progress?.run {
                start()
                imageViewStatusAvatar.loadCircleImage(avatarUrl, this)
            } ?: imageViewStatusAvatar.loadCircleImage(avatarUrl)

            textViewStatusUsername.text = userName
            textViewStatusBio.text = bio

            if (!TextUtils.isEmpty(location)) {
                textViewLocation.visibility = View.VISIBLE
                textViewLocation.text = location
            }
        }
    }

    override fun sendLoginBroadCast() {
        val activity = getBaseActivity() as MainActivity
        activity.sendLoginBroadCast()
    }
}