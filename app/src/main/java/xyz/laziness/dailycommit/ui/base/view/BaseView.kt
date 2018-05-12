package xyz.laziness.dailycommit.ui.base.view


interface BaseView {

    fun showBackButtonToast(backMessageCode: Int)

    fun showErrorMessage()

    fun finishView()

}