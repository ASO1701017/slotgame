package jp.ac.asojuku.st.slotgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    var check1:String? = null
    var check2:String? = null
    var check3:String? = null

    var btn1:Int? =null
    var btn2:Int? =null
    var btn3:Int? =null


    var kake:Int = 0
    var temoti:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        kake = intent.getIntExtra("kakekinncoin", 10)
        temoti = intent.getIntExtra("temotikointxt", 1000)

        temotikointxt2.text = intent.getIntExtra("temotikointxt", 1000).toString()
        kakekinncoin2.text = intent.getIntExtra("kakekinncoin", 10).toString()

        stopbtn1.setOnClickListener {
            if (check1 != null) return@setOnClickListener
            check1 = "OK"
            slot(slotimg1)
        }
        stopbtn2.setOnClickListener {
            if (check2 != null) return@setOnClickListener
            check2 = "OK"
            slot(slotimg2)
        }
        stopbtn3.setOnClickListener {
            if (check3 != null) return@setOnClickListener
            check3 = "OK"
            slot(slotimg3)
        }



        //戻るボタンのクリック反応（リスナーとコールバックメソッド）を設定
        returnbtn.setOnClickListener{finish()}
        }

    private fun slot(imageView: ImageView){
        var rand = (Math.random() * 9).toInt()
        when (rand) {
            0 -> imageView.setImageResource(R.drawable.banana)
            1 -> imageView.setImageResource(R.drawable.bar)
            2 -> imageView.setImageResource(R.drawable.bigwin)
            3 -> imageView.setImageResource(R.drawable.cherry)
            4 -> imageView.setImageResource(R.drawable.grape)
            5 -> imageView.setImageResource(R.drawable.lemon)
            6 -> imageView.setImageResource(R.drawable.orange)
            7 -> imageView.setImageResource(R.drawable.seven)
            8 -> imageView.setImageResource(R.drawable.waltermelon)
        }

        when(imageView){
            slotimg1 -> btn1 = rand
            slotimg2 -> btn2 = rand
            slotimg3 -> btn3 = rand
        }

        if (check1 != null && check2 != null && check3 != null){
            temoti =temoti-kake
            if (btn1 ==7 && btn2 == 7&& btn3==7){
                temoti = temoti+kake*20
                temotikointxt2.text = temoti.toString()
                txt1.text = "20倍だよ！"
            } else if(btn1 ==2 && btn2 == 2&& btn3==2){
                temoti = temoti+kake*10
                temotikointxt2.text = temoti.toString()
                txt1.text = "10倍だよ！"
            } else if (btn1 ==1 && btn2 == 1&& btn3==1){
                temoti = temoti+kake*5
                temotikointxt2.text = temoti.toString()
                txt1.text = "5倍だよ！"
            }  else if (btn1 ==btn2 && btn2 == btn3){
                temoti = temoti+kake*2
                temotikointxt2.text = temoti.toString()
                txt1.text = "2倍だよ！"
            }else if ((btn1 ==btn2 && btn2 != btn3 && btn1==7)  || (btn1!= btn2 && btn2 == btn3 && btn2==7) || (btn1==btn3 && btn1 !=btn2 && btn1==7)){
                temoti = temoti + kake * 3
                temotikointxt2.text = temoti.toString()
                txt1.text = "3倍だよ！"
            } else if ((btn1 ==btn2 && btn2 != btn3)  || (btn1!= btn2 && btn2 == btn3) || (btn1==btn3 && btn1 !=btn2)){
                temoti = temoti+kake*1
                temotikointxt2.text = temoti.toString()
                txt1.text = "1倍だよ！"
            }else if ((btn1==8 && btn2!=8  && btn3!=8)||(btn1!=8 && btn2==8  && btn3!=8) || (btn1!=8 && btn2!=8  && btn3==8)) {
                temoti = temoti + kake * 1
                temotikointxt2.text = temoti.toString()
                txt1.text = "1倍だよ！"
            }else if (btn1==6 && btn2==3  && btn3==5) {
                temoti = temoti + kake * 30
                temotikointxt2.text = temoti.toString()
                txt1.text = "30倍だよ！"
            }else if (btn1==8 && btn2==0  && btn3==4) {
                    temoti = temoti + kake * 10
                    temotikointxt2.text = temoti.toString()
                    txt1.text = "10倍だよ！"
            }else{
                temotikointxt2.text = temoti.toString()
                txt1.text = "はずれ！"
            }
            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = pref.edit()
            editor.putInt("temoti",temoti)
                .apply()
        }
    }
}
