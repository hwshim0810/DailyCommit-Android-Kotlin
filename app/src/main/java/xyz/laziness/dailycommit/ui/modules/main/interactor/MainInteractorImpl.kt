package xyz.laziness.dailycommit.ui.modules.main.interactor

import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class MainInteractorImpl
@Inject internal constructor(appPreference: BasePreference) : BaseInteractorImpl(appPreference), MainInteractor