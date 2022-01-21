package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class HomePage : AppCompatActivity() {
    lateinit var logout: TextView



    lateinit var profile: ImageView
    lateinit var profile2: TextView

    // Movies
    lateinit var starWars1: ImageView
    lateinit var starWars2: ImageView
    lateinit var starWars3: ImageView

    lateinit var alien: ImageView

    lateinit var spidermanHolland1: ImageView
    lateinit var spidermanHolland2: ImageView
    lateinit var spidermanHolland3: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        init()
        listeners()
    }

    private fun init(){
        // Movies ID
        starWars1 = findViewById(R.id.starWars1)
        starWars2 = findViewById(R.id.starWars2)
        starWars3 = findViewById(R.id.starWars3)

        alien = findViewById(R.id.alien)

        spidermanHolland1 = findViewById(R.id.spidermanHolland1)
        spidermanHolland2 = findViewById(R.id.spidermanHolland2)
        spidermanHolland3 = findViewById(R.id.spidermanHolland3)

        logout = findViewById(R.id.signOut)
        profile = findViewById(R.id.accountButton)
        profile2 = findViewById(R.id.accountButton2)


        val imageSlider = findViewById<ImageSlider>(R.id.bannerSlider)
        val imageList = arrayListOf<SlideModel>()

        imageList.add(SlideModel("https://images.wallpapersden.com/image/download/netflix-the-witcher-2019_a2xraGqUmZqaraWkpJRmbmdlrWZlbWU.jpg","The witcher - Season 2\n2021 - December 17"))
        imageList.add(SlideModel("https://www.ixpap.com/images/2021/11/HD-Spider-Man-No-Way-Home-Wallpaper-2.jpg","Spider-Man: No Way Home\n2021 - December 17"))
        imageList.add(SlideModel("https://wallpaperaccess.com/full/1534120.jpg","Hawkeye\n2021 - November 24"))
        imageList.add(SlideModel("https://en.free-wallpapers.su/data/media/9192/big/mv11211.jpg","Dune\n2021 - Octomber 1"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)


    }

    private fun listeners(){

        // Movie Listeners
        var clickedInfo = ""
        val intent = Intent(this, InformationActivity::class.java)

        starWars1.setOnClickListener {
            clickedInfo = "starwars1"
            intent.putExtra("clickedInfo", clickedInfo)
            startActivity(intent)
            finish()
        }

        starWars2.setOnClickListener {
            clickedInfo = "starwars2"
            intent.putExtra("clickedInfo", clickedInfo)
            startActivity(intent)
            finish()
        }

        starWars3.setOnClickListener {
            clickedInfo = "starwars3"
            intent.putExtra("clickedInfo", clickedInfo)
            startActivity(intent)
            finish()
        }



        // Movie Listeners


        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        profile2.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setMessage("Do you want to exit?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}