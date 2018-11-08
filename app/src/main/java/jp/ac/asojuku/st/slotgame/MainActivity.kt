package jp.ac.asojuku.st.slotgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var koin = 1000
    var kake = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        kakeinnupbtn.setOnClickListener{
            if (kake<koin) {
                kake += 100
                kakekinncoin.text = kake.toString()
            }
        }

        kakekindownbtn.setOnClickListener {
            if (10 < kake) {
                kake -= 100
                kakekinncoin.text = kake.toString()
            }
        }

        resetbtn.setOnClickListener {
                koin = 1000
                temotikointxt.text = koin.toString()
        }

        startbtn.setOnClickListener{onstartbtnTapped(it)}
    }

    override fun onResume() {
        super.onResume()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        koin = pref.getInt("temoti",1000)
        kake = 0
        temotikointxt.text = koin.toString()
        kakekinncoin.text = kake.toString()
    }

fun onstartbtnTapped(view: View?){
        val intent = Intent(this,GameActivity::class.java)
        intent.putExtra("temotikointxt",koin)
        intent.putExtra("kakekinncoin",kake)
        startActivity(intent)
    }
}
