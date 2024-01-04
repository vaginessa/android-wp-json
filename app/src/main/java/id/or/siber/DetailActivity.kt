package id.or.siber

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailActivity : AppCompatActivity() {

    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var imageView: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvContent: TextView
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val upArrow = ContextCompat.getDrawable(this, androidx.appcompat.R.drawable.abc_ic_ab_back_material)?.mutate()
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("category") ?: ""
        supportActionBar?.let {
            val textColor = ContextCompat.getColor(this, R.color.white)
            val title = SpannableString(supportActionBar?.title).apply {
                setSpan(ForegroundColorSpan(textColor), 0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
            supportActionBar?.title = title
        }
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ab_gradient_vertical))
//        to use parallax effect uncomment the code below and use activity_detail2 as layout
//        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
//        appBarLayout = findViewById(R.id.appBar)
        imageView = findViewById(R.id.imageView)
        tvTitle = findViewById(R.id.tvTitle)
        tvContent = findViewById(R.id.tvContent)
        tvDate = findViewById(R.id.tvDate)

        intent.getStringExtra("content")?.let { Log.d(intent.getStringExtra("title"), it) }
        tvTitle.text = intent.getStringExtra("title")
        tvDate.text = intent.getStringExtra("date")
        tvContent.text = intent.getStringExtra("content")
        Glide.with(applicationContext)
            .load(intent.getStringExtra("media"))
            .placeholder(R.drawable.no_img)
            .error(R.drawable.no_img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_post_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareContent()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareContent() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("guid"))
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }

}