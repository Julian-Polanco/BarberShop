package co.com.poli.barbershop

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = ProfileFragment()
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        val profileLayout = findViewById<LinearLayout>(R.id.profile_layout)
        val photosLayout = findViewById<LinearLayout>(R.id.photos_layout)
        val videosLayout = findViewById<LinearLayout>(R.id.video_layout)
        val webSiteLayout = findViewById<LinearLayout>(R.id.web_layout)
        val buttonsLayout = findViewById<LinearLayout>(R.id.buttons_layout)
        profileLayout.setBackgroundResource( R.drawable.gradient_selected_item)

        profileLayout.setOnClickListener {
            photosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            videosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            webSiteLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            buttonsLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            profileLayout.setBackgroundResource( R.drawable.gradient_selected_item)
        }
        photosLayout.setOnClickListener {
            profileLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            videosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            webSiteLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            buttonsLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            photosLayout.setBackgroundResource( R.drawable.gradient_selected_item)
        }
        videosLayout.setOnClickListener {
            profileLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            photosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            webSiteLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            buttonsLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            videosLayout.setBackgroundResource( R.drawable.gradient_selected_item)
        }
        webSiteLayout.setOnClickListener {
            profileLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            photosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            videosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            buttonsLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            webSiteLayout.setBackgroundResource( R.drawable.gradient_selected_item)
        }
        buttonsLayout.setOnClickListener {
            profileLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            photosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            videosLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            webSiteLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.brown_50))
            buttonsLayout.setBackgroundResource( R.drawable.gradient_selected_item)
        }
        drawerLayout?.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.content_container, fragment).commit()
        this.navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content_container, fragment).commit()
                    true
                }
                R.id.nav_photos -> {
                    Toast.makeText(applicationContext,"fotos",Toast.LENGTH_SHORT).show();
                    true
                }
                R.id.nav_videos -> {
                    // Código para ir a la pantalla de citas
                    true
                }
                R.id.nav_web_site -> {
                    // Código para ir a la pantalla de perfil
                    true
                }
                R.id.nav_web_site -> {
                    // Código para cerrar sesión
                    true
                }
                else -> false
            }.also { drawerLayout?.closeDrawer(GravityCompat.START) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}