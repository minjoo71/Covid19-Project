# CovidProject
### 소개
코로나 공공데이터 API를 활용하여, 국내/지역별 코로나 감염 현황을 확인할 수 있고, 또한 현재 위치에서 가까운 선별 진료소 목록을 확인할 수 있습니다.


### 기술
**kotlin**, **Jetpack AAC**(***DataBinding, ViewModel, LiveData, Room, Navigation, Paging***), **Dagger2**, **RxJava2**, **Retrofit2**


### 주요 기능
* 국내/지역별 코로나 감염현황 확인
* 코로나 선별진료소 전체 목록 확인
* 현재 지역에서 가까운 선별 진료소 확인
![image](https://user-images.githubusercontent.com/79129823/136739518-c85c40c0-36cb-491e-b5f3-e8ae89601ebd.png)


### 서비스 구조
전체
![image](https://user-images.githubusercontent.com/79129823/136740477-9479cf2b-0a92-4338-a052-2306cd2e55d2.png)
dagger2
![image](https://user-images.githubusercontent.com/79129823/136740693-b48a9ad5-e26a-4839-a162-4e5e709bf314.png)
