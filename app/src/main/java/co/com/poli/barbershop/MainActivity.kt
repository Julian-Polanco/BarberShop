package co.com.poli.barbershop

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null

    private val layoutMap = mapOf(
        R.id.home_layout to Pair(HomeFragment(), R.id.home_line),
        R.id.profile_layout to Pair(ProfileFragment(), R.id.profile_line),
        R.id.photos_layout to Pair(PhotosFragment(), R.id.photos_line),
        R.id.video_layout to Pair(VideoFragment(), R.id.video_line),
        R.id.web_layout to Pair(WebFragment(), R.id.web_line),
        R.id.buttons_layout to Pair(ButtonsFragment(), R.id.buttons_line)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout).apply {
            actionBarDrawerToggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                R.string.open,
                R.string.close
            ).also { addDrawerListener(it) }
        }

        actionBarDrawerToggle?.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        changeFragment(HomeFragment(), R.id.home_line)

        layoutMap.forEach { (layoutId, pair) ->
            findViewById<LinearLayout>(layoutId).setOnClickListener {
                changeFragment(pair.first, pair.second)
            }
        }

        navigationView = findViewById<NavigationView>(R.id.navigation_view).apply {
            setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_home -> changeFragment(HomeFragment(), R.id.home_line)
                    R.id.nav_profile -> changeFragment(ProfileFragment(), R.id.profile_line)
                    R.id.nav_photos -> changeFragment(PhotosFragment(), R.id.photos_line)
                    R.id.nav_videos -> changeFragment(VideoFragment(), R.id.video_line)
                    R.id.nav_web_site -> changeFragment(WebFragment(), R.id.web_line)
                    R.id.nav_buttons -> changeFragment(ButtonsFragment(), R.id.buttons_line)
                }
                drawerLayout?.closeDrawer(GravityCompat.START)

                true
            }
        }
    }


    fun changeFragment(fragment: Fragment, lineId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, fragment)
            .commit()

        layoutMap.values.forEach { (_, line) ->
            findViewById<View>(line).visibility = View.GONE
        }

        findViewById<View>(lineId).visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout?.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}