package com.example.mysecondapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mysecondapplication.R
import com.example.mysecondapplication.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mViewBinding.root)

        //status bar text 색상
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        //get NavController
        navController = findNavController(R.id.fragment)
        //set up a NavigationView for use with a NavController
        navigationView.setupWithNavController(navController)

        //AppBarConfiguration 객체를 사용해서 앱 상단 왼쪽의 탐색버튼을 관리한다.
        //navigation graph 에서 정의 된 것을 전부 사용하려면, 파라미터에 navigation graph 전체 전달
        //DrawerLayout 을 AppBarConfiguration 에 전달하여 NavigationGraph 에 연결한다.
        appBarConfiguration = AppBarConfiguration(navController.graph, mViewBinding.drawerLayout)

        //create drawer menu(햄버거 메뉴)
        //파라미터로 전달한 두 객체를 AppBar에 적용한다.
        //By calling this method, the title in the actionbar will automatically be updated whe the destination changes
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    //drawer menu 버튼 클릭
    //AppBar에 생성되는 뒤로가기 버튼을 눌렀을 때, 뒤로 이동하려면 onSupportNavigateUp을 오버라이드 해야 한다.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}