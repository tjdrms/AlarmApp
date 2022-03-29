package com.example.alarm

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step0 뷰를 초기화해주기
        initOnDffButton()
        initChangealarmTimeButton()

        //step1 데이터 가져오기

        //step2 뷰에 데이터를 그려주기
    }

    private fun initOnDffButton() {
        val onOffButton = findViewById<Button>(R.id.onOffButton)
        onOffButton.setOnClickListener {
            //데이터 확인을 한다.

            //온오프에 따라 작업을 처리한다.

            //오프라면 알람을 제거
            //온이라면 알람을 등록

            //데이터 저장
        }

    }

    private fun initChangealarmTimeButton() {
        val changeAlarmButton = findViewById<Button>(R.id.changeAlarmTimeButton)
        changeAlarmButton.setOnClickListener {
            //현재시간 가져오기
            //TimePickDialog 띄워줘서 시간설정, 시간을 가져와서
            val calendar = Calendar.getInstance()

            TimePickerDialog(this, { picker, hour, minute ->

                val model = saveAlarmModel(hour, minute, false)

                //데이터 저장
                //뷰 업데이트
                //기존에 있던 알람을 삭제

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()

        }
    }

    private fun saveAlarmModel(
        hour: Int,
        minute: Int,
        onOff: Boolean
        ): AlarmDisplayModel {
        val model = AlarmDisplayModel(
            hour = hour,
            minute = minute,
            onOff = false
        )

        val sharedPreferences = getSharedPreferences("time", Context.MODE_PRIVATE)

        with(sharedPreferences.edit()) {
            putString("alarm", model.makeDataForDB())
            putBoolean("onOff", model.onOff)
            commit()
        }

        return model
    }
}