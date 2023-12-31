package id.or.siber

import android.content.Intent
import android.os.Bundle
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
//        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.background = ContextCompat.getDrawable(this, R.drawable.ab_gradient)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        appBarLayout = findViewById(R.id.appBar)
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