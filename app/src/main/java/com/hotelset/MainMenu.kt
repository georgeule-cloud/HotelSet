package com.hotelset

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hotelset.databinding.ActivityMainMenuBinding

class MainMenu : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainMenu.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main_menu)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_hotel, R.id.nav_noticias, R.id.nav_aboutUs
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        actualiza(navView)

    }

    private fun actualiza(navView: NavigationView) {
        val vista: View =navView.getHeaderView(0)
        val tvNombre: TextView = vista.findViewById(R.id.user)
        val tvCorreo: TextView = vista.findViewById(R.id.useremail)
        val imagen: ImageView = vista.findViewById(R.id.imagenu)

        val usuario = Firebase.auth.currentUser

        tvNombre.text = usuario?.displayName
        tvCorreo.text = usuario?.email
        val rutaFoto = usuario?.photoUrl.toString()
        if (rutaFoto.isNotEmpty()) {
            Glide.with(this)
                .load(rutaFoto)
                .circleCrop()
                .into(imagen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_logout -> {
                Firebase.auth.signOut()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}