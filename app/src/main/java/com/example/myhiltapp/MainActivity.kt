package com.example.myhiltapp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myhiltapp.changelang.Changelang
import com.example.myhiltapp.databinding.ActivityMainBinding
import com.example.myhiltapp.db.FavActDatabase
import com.example.myhiltapp.repo.FavActREpo
import com.example.myhiltapp.viewmodel.FavActivityViewmodel
import com.example.myhiltapp.viewmodel.FavViewModelFactory
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var changelang: Changelang
    private lateinit var binding: ActivityMainBinding
    private val choose = arrayOf("Select", "AR", "EN")
    private lateinit var navController: NavController
    lateinit var mfavviewmodel: FavActivityViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changelang = Changelang(this)
        //   initSpinner()
        setupNavigationBottom()
        setUpViewmodel()

    }

//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(Changelang(newBase.setAppLocale("ar")))
//    }


    private fun setupNavigationBottom() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        val appConfig = AppBarConfiguration(setOf(R.id.homeFragment))
        setupActionBarWithNavController(navController, appConfig)
    }


//    private fun initSpinner() {
//
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, choose)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spSelect.apply {
//            this.adapter = adapter
//            this.selectedItem == 0
//        }
//       binding.spSelect.onItemSelectedListener=object :OnItemSelectedListener{
//           override fun onItemSelected(
//               parent: AdapterView<*>?,
//               view: View?,
//               position: Int,
//               id: Long
//           ) {
//               val lang=choose[position]
//               if (lang=="EN"){
//                 setLocal(this@MainActivity,lang)
//                 // wrap(this@MainActivity,lang)
//                   finish()
//                   startActivity(intent)
//
//               }else if (lang=="AR"){
//                 setLocal(this@MainActivity,lang)
//               //   wrap(this@MainActivity,lang)
//                   finish()
//                   startActivity(intent)
//               }
//           }
//
//           override fun onNothingSelected(parent: AdapterView<*>?) {
//               TODO("Not yet implemented")
//           }
//
//       }
//    }


    // fun  to change language in app
    private fun setLocal(context: Activity, lang: String) {

        val local = Locale(lang)
        Locale.setDefault(local)
        val resource = context.resources
        val config = resource.configuration
        config.setLocale(local)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//           context.createConfigurationContext(config);
//        } else {
//         context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
//        }

        resource.updateConfiguration(config, resource.displayMetrics)

    }


    fun Context.setAppLocale(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return createConfigurationContext(config)

    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }


    fun setUpViewmodel() {

        val repo = FavActREpo(FavActDatabase.getDatabase(this))
        val factviewmodel = FavViewModelFactory(repo)
        mfavviewmodel = ViewModelProvider(this, factviewmodel).get(FavActivityViewmodel::class.java)

    }


}