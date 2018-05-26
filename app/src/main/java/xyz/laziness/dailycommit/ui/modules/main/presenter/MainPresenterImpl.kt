package xyz.laziness.dailycommit.ui.modules.main.presenter

import com.androidnetworking.error.ANError
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.view.MainView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import java.net.HttpURLConnection
import javax.inject.Inject


class MainPresenterImpl<V: MainView, I: MainInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        MainPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        initUserInfoRequest()
        setUserStatusDisplay()
    }

    override fun setUserStatusDisplay() = getView()?.openUserStatusFragment()

    override fun initUserInfoRequest() {
        interactor?.let {
            compositeDisposable.add(
                    it.doUserInfoApiCall()
                            .compose(schedulerHelper.ioToMainObservableScheduler())
                            .subscribe({
                                getView()?.setUpDrawer(it)
                            }, {
                                it as ANError
                                if (it.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                                    getView()
                                else
                                    this.onError()
                            })

            )
        }
    }

    override fun onDrawerLogoutItemClick() {
        interactor?.let {
            compositeDisposable.add(
                    Observable.fromCallable {
                        it.removeUserInfoInPreference()
                    }
                    .compose(schedulerHelper.ioToMainObservableScheduler())
                    .subscribe(
                        { getView()?.startLoginActivity() },
                        { getView()?.showErrorMessage() }
                    )
            )
        }
    }

}
