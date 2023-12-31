package id.or.siber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // Start your main activity or any other activity after the splash screen delay
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish() // Finish the splash activity to prevent going back to it when pressing back button
        }, SPLASH_TIMEOUT.toLong())
    }

    companion object {
        private const val SPLASH_TIMEOUT = 1500
    }
}