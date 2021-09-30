package com.example.mysecondapplication.ui.main.hospital.my

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysecondapplication.R
import com.example.mysecondapplication.adapter.HosDataAdapter
import com.example.mysecondapplication.databinding.FragmentMyHosBinding
import com.example.mysecondapplication.ui.base.BaseFragment
import com.example.mysecondapplication.ui.main.hospital.CovidHosFragment
import com.example.mysecondapplication.util.hide
import com.example.mysecondapplication.util.show
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_my_hos.*
import java.lang.RuntimeException
import java.util.*


class MyHosFragment : BaseFragment<FragmentMyHosBinding, MyHosViewModel>() {

    companion object{
        private const val TAG = "MyHosFragment"
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override var getLayoutId: Int = R.layout.fragment_my_hos
    override var getViewModel: Class<MyHosViewModel> = MyHosViewModel::class.java

    private var hosDataAdapter = HosDataAdapter()

    //위치 값 넣을 변수
    lateinit var gugun : String
    lateinit var sido : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated: 실행" )

        //리사이클러뷰 세팅
        recyclerViewInit()

        requestPermission()

    }

    private fun recyclerViewInit(){
        myHos_recyclerview.let {
            it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            it.setHasFixedSize(true)
            it.adapter = hosDataAdapter
        }
    }


    //퍼미션 요청
    private fun requestPermission() {
        Log.e(TAG, "requestRuntimePermissions: 실행")
        if(ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "requestPermission: permission is grated.... so check Database" )

            //퍼미션 허용 된 상태
            //현재 주소 가져오기
            getCurrentLocation()

        } else {
            //퍼미션 허용 안 됐을 때
            //퍼미션 요청하기
            Log.e(TAG, "requestRuntimePermissions: permission is not granted... so request permission", )
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE
            )
        }
    }

    //퍼미션 요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty()) {
                    throw RuntimeException("Empty permission result")
                }
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "onRequestPermissionsResult: 최초로 퍼미션 허용됨, checkDatabase() 실행..." )
                    //최초로 퍼미션 허용 됨
                    getCurrentLocation()

                } else {
                    //shouldShowRequestPermission...()는 사용자가 Deny 버튼을 눌렀을 때 true 리턴한다.
                    //사용자가 Deny & Don't ask again 버튼을 누르면 false 리턴
                    //true 가 리턴 되는 경우 사용자에게 다시 권한을 요청할 수 있게 됨
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        Log.e(TAG, "User declined, but i can still ask for more ")
                        requestPermissions(
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            PERMISSION_REQUEST_CODE
                        )
                    } else {
                        Log.e("CovidHosFragment", "User declined, and i can't ask ")
                    }
                }
            }
        }
    }



    //현재 주소 가져오기.
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        Log.e(TAG, "getCurrentLocation: 실행..." )
        //Fused Location Provider Client 인스턴스 생성
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        //위치 요청 설정
        val locationRequest = LocationRequest.create()?.apply {
            //앱에서 선호하는 위치 업데이트 수신 간격을 밀리초 단위로 설정한다.
            interval = 500 * 1000
            //이 설정 사용하면 GPS 사용하여 위치 확인할 가능성 높음 -> 가장 정확한 위치 요청함
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        //x좌표값, longitude(경도)   y좌표값, latitude(위도)
                        val latitude = location.latitude //y 값
                        val longitude = location.longitude //x 값
                        Log.e(TAG, "y 값 :" + location.latitude + ",x 값 :" + location.longitude)

                        val geocoder = Geocoder(context, Locale.getDefault())
                        val address = geocoder.getFromLocation(latitude, longitude, 10)
                        Log.e(TAG, "주소 : " + address)

                        if (address.size > 0) {
                            sido = address.get(0).adminArea //인천광역시
                            Log.e(TAG, "시도 : " + sido)

                            gugun = address.get(0).subLocality //부평구
                            Log.e(TAG, "군구 : " + gugun)

                            //시도, 군구 데이터를 view에 바인딩하기
                            viewBinding.myHos = this@MyHosFragment

                            //db에 데이터 요청하기
                            getMyHosData(gugun)
                        }

                    }
                }
            },
            Looper.myLooper()
        )

    }
    //room db 에서 내 위치(주소)를 포함하는 진료소 데이터를 가져와야 한다.
    fun getMyHosData(gugun : String){
        //프로그레스바 show
        myHos_progressbar.show()
        Log.e(TAG, "getMyHosData: getMyHosData 호출...")
        viewModel.getMyHosData(gugun).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let { it ->
                if (it.size > 0) {
                    Log.e(TAG, "getMyHosData: it 값 =>"+it )
                    myHos_progressbar.hide()
                    hosDataAdapter.setList(it)
                }
            }

        })
    }
}