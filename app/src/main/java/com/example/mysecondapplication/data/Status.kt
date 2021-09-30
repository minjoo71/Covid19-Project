package com.example.mysecondapplication.data

/**
 * Resource class for status
 * A generic class that contains data and status about loading this data
 * resource 클래스를 사용하여 네트워크 상태를 표시*/
class Status<T>(val status: StatusType, val data: T?, val message: String?) {

    enum class StatusType {
        SUCCESS, ERROR
    }

    //Companion object 로 선언된 객체도 object 로 선언된 클래스처럼 싱글톤 인스턴스다.
    //Object는 클래스 전체가 하나의 인스턴스로 선언되는 것이라면, Companion Object 는 클래스 내에 일부분을 싱글톤 객체로 갖는다.
    companion object{
        fun <T> success (data: T?): Status<T> {
            return Status(StatusType.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?):Status<T>{
            return Status(StatusType.ERROR, data, message)
        }
    }
}