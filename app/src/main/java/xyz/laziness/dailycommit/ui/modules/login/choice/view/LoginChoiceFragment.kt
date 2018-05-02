package xyz.laziness.dailycommit.ui.modules.login.choice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login_choice.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import xyz.laziness.dailycommit.ui.modules.login.choice.presenter.LoginChoicePresenter
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


class LoginChoiceFragment : BaseFragment(), LoginChoiceView {

    companion object {

        fun getInstance(): LoginChoiceFragment = LoginChoiceFragment()

    }

    @Inject
    internal lateinit var presenter: LoginChoicePresenter<LoginChoiceView, LoginChoiceInteractor>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initUI() {
        btnBasicLogin.setOnClickListener { presenter.onBasicLoginButtonClick() }
        btnTokenLogin.setOnClickListener { presenter.onTokenLoginButtonClick() }
    }

    override fun openLoginInputFragment(loginMethod: AppConstants.LoginMethod) {
        val activity = getBaseActivity() as LoginActivity?
        activity?.openLoginInputFragment(loginMethod)
    }
}