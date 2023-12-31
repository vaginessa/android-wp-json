package id.or.siber

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        setSupportActionBar(findViewById(R.id.toolbar))
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
}