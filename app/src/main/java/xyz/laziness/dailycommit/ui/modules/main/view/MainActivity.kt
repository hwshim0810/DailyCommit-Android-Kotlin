package xyz.laziness.dailycommit.ui.modules.main.view

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.drawer_header.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.view.BaseActivity
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusFragment
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.presenter.MainPresenter
import xyz.laziness.dailycommit.ui.modules.main.settings.view.AppSettingPrefCompat
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusFragment
import xyz.laziness.dailycommit.utils.extensions.loadRoundedCornerImage
import xyz.laziness.dailycommit.utils.extensions.replaceFragmentInActivity
import xyz.laziness.dailycommit.widget.receiver.WidgetReceiver
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainPresenter<MainView, MainInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var userInfo: UserInfoResponse? = null

    private val fragmentDeque: ArrayDeque<Int> = ArrayDeque()
    private var isBackPressed = false

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    private val bottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_user -> {
                if (!isBackPressed) pushFragmentDeque(R.id.navigation_user)

                isBackPressed = false
                openUserStatusFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_friends -> {
                if (!isBackPressed) pushFragmentDeque(R.id.navigation_friends)

                isBackPressed = false
                openFriendsStatusFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                if (!isBackPressed) pushFragmentDeque(R.id.navigation_settings)

                isBackPressed = false
                openAppSettingFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val drawerNavItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navItemLogout -> {
                presenter.onDrawerLogoutItemClick()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDrawer()

        presenter.onAttach(this)
        bottomNavView.setOnNavigationItemSelectedListener(bottomNavItemSelectedListener)
        pushFragmentDeque(R.id.navigation_user)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onViewBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)

        if (fragmentDeque.size > 1) {
            isBackPressed = true
            fragmentDeque.pop()
            bottomNavView.selectedItemId = fragmentDeque.peek()
        } else {
            presenter.onBackPressed()
        }
    }

    override fun startLoginActivity() = Intent(this, LoginActivity::class.java).run {
        startActivity(this)
        finish()
    }

    override fun openUserStatusFragment() {
        if (!isEqualFragmentByTag(R.id.contentFrame, UserStatusFragment.TAG)) {
            UserStatusFragment.getInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame, true, UserStatusFragment.TAG)
            }
        }
    }

    override fun openFriendsStatusFragment() {
        if (!isEqualFragmentByTag(R.id.contentFrame, FriendsStatusFragment.TAG)) {
            FriendsStatusFragment.getInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame, true, FriendsStatusFragment.TAG)
            }
        }
    }

    override fun openAppSettingFragment() {
        if (!isEqualFragmentByTag(R.id.contentFrame, AppSettingPrefCompat.TAG)) {
            AppSettingPrefCompat.getInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame, true, AppSettingPrefCompat.TAG)
            }
        }
    }

    override fun lockDrawer() = drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer() = drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    override fun setViewData(userInfoResponse: UserInfoResponse) {
        userInfoResponse.apply {
            navUserNameText.text = userName
            navUserIdText.text = userId
            userAvatarImage.loadRoundedCornerImage(avatarUrl)
            userInfo = this
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            item?.run {
                when (item.itemId) {
                    R.id.actionAddFriend -> {
                        showAddFriendDialog()
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
            } ?: false

    override fun onResponseAddingFriend(friendName: String, id: Long) {
        if (isEqualFragmentByTag(R.id.contentFrame, FriendsStatusFragment.TAG)) {
            val fragmentFrame = supportFragmentManager.findFragmentByTag(FriendsStatusFragment.TAG) as FriendsStatusFragment

            if (!fragmentFrame.friendStatusAdapter.isFriendContain(friendName)) {
                FriendsStatusPresenterImpl.currentId = id
                fragmentFrame.isLoading = false
                fragmentFrame.presenter.doFriendContributionRequest(friendName)
            }
        }
        showToastMessage(R.string.add_success_msg)
    }

    override fun showToastMessage(@StringRes message: Int) =
            showToastMessage(getString(message))

    override fun showToastMessage(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun sendLogoutBroadCast() {
        val intent = Intent(this, WidgetReceiver::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        sendBroadcast(intent)
    }

    override fun showAddFriendDialog() {
        val editText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT
        }

        AlertDialog.Builder(this)
                .setTitle(R.string.add_friend)
                .setCancelable(true)
                .setView(editText)
                .setPositiveButton(getString(R.string.ok))
                { _, _ -> presenter.onAddFriendDialogOkClick(editText.text.toString()) }
                .setNegativeButton(getString(R.string.cancel))
                { dialog, _ -> dialog.cancel() }
                .show()
    }

    fun sendLoginBroadCast() {
        val intent = Intent(this, WidgetReceiver::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        sendBroadcast(intent)
    }

    private fun setUpDrawer() {
        setSupportActionBar(toolbar)
        ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close).run {
            drawerLayout.addDrawerListener(this)
            syncState()
        }
        drawerNavView.setNavigationItemSelectedListener(drawerNavItemSelectedListener)
    }

    private fun pushFragmentDeque(@IdRes id: Int) {
        if (fragmentDeque.size < 3)
            fragmentDeque.push(id)
        else {
            fragmentDeque.removeLast()
            fragmentDeque.push(id)
        }
    }

}
