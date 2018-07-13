package xyz.laziness.dailycommit.ui.modules.main.user.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusView


interface UserStatusPresenter<V: UserStatusView, I: UserStatusInteractor> : BasePresenter<V, I> {

    fun userInfoRequest()

}