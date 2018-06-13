package xyz.laziness.dailycommit.ui.modules.main.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
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
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusFragment
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.presenter.MainPresenter
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusFragment
import xyz.laziness.dailycommit.utils.extensions.loadImage
import xyz.laziness.dailycommit.utils.extensions.replaceFragmentInActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainPresenter<MainView, MainInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val fragmentFrame: Fragment by lazy { supportFragmentManager.findFragmentById(R.id.contentFrame) }

    var userInfo: UserInfoResponse? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    private val bottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_user -> {
                openUserStatusFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_friends -> {
                openFriendsStatusFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
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
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onViewBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) super.superOnBackPressed()
        else presenter.onBackPressed()
    }

    override fun startLoginActivity() = Intent(this, LoginActivity::class.java).run {
        startActivity(this)
        finish()
    }

    override fun openUserStatusFragment() {
        fragmentFrame as UserStatusFragment? ?: UserStatusFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame, false)
        }
    }

    override fun openFriendsStatusFragment() {
        fragmentFrame as FriendsStatusFragment? ?: FriendsStatusFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame, false)
        }
    }

    override fun lockDrawer() = drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer() = drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    override fun setViewData(userInfoResponse: UserInfoResponse) {
        userInfoResponse.apply {
            navUserNameText.text = userName
            navUserIdText.text = userId
            userAvatarImage.loadImage(avatarUrl)
            userInfo = this
        }
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

}
