package k.c.mapcardviewdemo.app

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.base.BaseActivity
import k.c.mapcardviewdemo.app.ui.BlankFragment
import k.c.mapcardviewdemo.app.ui.CallCarMapFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {



    private lateinit var currentFragment: Fragment

    private var blankFragment: BlankFragment = BlankFragment.newInstance()

    private var callCarMapFragment: CallCarMapFragment = CallCarMapFragment.newInstance()



    public override val contentViewLayout: Int get() = R.layout.activity_navigation


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.bnve_home,
            R.id.bnve_category,
            R.id.bnve_cart,
            R.id.bnve_mine -> {
                switchFragment(blankFragment)
            }


            R.id.bnve_car -> {

                switchFragment(callCarMapFragment)
            }

            else -> throw UnsupportedOperationException("Illegal branch!")
        }
        return loadFragment(currentFragment)
    }


    private fun switchFragment(target: Fragment?) {
        if (currentFragment === target)
            return

        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(currentFragment)

            if (target!!.isAdded) {
                transaction.show(target)
            } else {
                transaction.add(R.id.layout_container, target, target.javaClass.name)
            }

        transaction.commit()
        currentFragment = target
    }


    private fun loadFragment(mFragment: Fragment?): Boolean {

        return mFragment != null
    }


    override fun initView() {

        retrieveFragments()
        bnve.setOnNavigationItemSelectedListener(this)
        bnve.itemIconTintList = null


        supportFragmentManager.beginTransaction().add(R.id.layout_container, blankFragment).commit()
        currentFragment = blankFragment
        bnve.selectedItemId = R.id.bnve_car

    }

    override fun onBackPressed() {
        if (currentFragment != blankFragment) {
            bnve.selectedItemId = R.id.bnve_home
            return
        }
        moveTaskToBack(true)
    }

    private fun retrieveFragments() {
        val manager = supportFragmentManager
        when {
            manager.findFragmentByTag(BlankFragment::class.java.name) != null -> blankFragment = manager.findFragmentByTag(BlankFragment::class.java.name) as BlankFragment
            manager.findFragmentByTag(CallCarMapFragment::class.java.name) != null -> callCarMapFragment = manager.findFragmentByTag(BlankFragment::class.java.name) as CallCarMapFragment

        }

    }


}

