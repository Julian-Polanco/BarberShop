package co.com.poli.barbershop

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { item ->
            val id = item.itemId

            if (id == R.id.nav_home) {
                val textView: TextView = findViewById(R.id.text_view)
                textView.text = "Has seleccionado el ítem de menú"
            }

            true
        }
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                true
            } else super.onOptionsItemSelected(item)
        }
}