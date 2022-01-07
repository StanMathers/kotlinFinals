package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class InformationActivity : AppCompatActivity() {

    lateinit var movieName: TextView
    lateinit var releaseDate: TextView

    lateinit var headerImageView: ImageView

    lateinit var firstCast: ImageView
    lateinit var secondCast: ImageView
    lateinit var thirdCast: ImageView

    lateinit var imdb: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        init()
    }

    fun init(){

        // Star Wars: Phantom Menace
        val starWarsBanner1 = "https://starwarsblog.starwars.com/wp-content/uploads/2019/05/quigon-obiwan-maul-duel-tall.jpg"
        val starWarsFirstCast1 = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Hayden-cfda2010-0004%281%29_Cropped.jpg"
        val starWarsSecondCast1 = "https://static.wikia.nocookie.net/alexrider/images/6/6b/Ewan_McGregor.jpg/revision/latest?cb=20201109110243"
        val starWarsThirdCast1 = "https://qartulad.ge/media/MV5BMTMzMjU5NDA0NV5BMl5BanBnXkFtZTYwMTUwMzI0._V1.jpg"

        // Star Wars: Attack of the Clones

        val starWarsBanner2 = "https://upload.wikimedia.org/wikipedia/en/3/32/Star_Wars_-_Episode_II_Attack_of_the_Clones_%28movie_poster%29.jpg"
        val starWarsFirstCast2 = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Hayden-cfda2010-0004%281%29_Cropped.jpg"
        val starWarsSecondCast2 = "https://static.wikia.nocookie.net/alexrider/images/6/6b/Ewan_McGregor.jpg/revision/latest?cb=20201109110243"
        val starWarsThirdCast2 = "https://qartulad.ge/media/MV5BMTMzMjU5NDA0NV5BMl5BanBnXkFtZTYwMTUwMzI0._V1.jpg"

        // Star Wars: Revenge of the Sith

        val starWarsBanner3 = "https://images-na.ssl-images-amazon.com/images/S/pv-target-images/3a991f1ad651779c8701339593c0a7c6afa18ba8844da38a1740e9fea2ebe873._RI_V_TTW_.jpg"
        val starWarsFirstCast3 = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Hayden-cfda2010-0004%281%29_Cropped.jpg"
        val starWarsSecondCast3 = "https://static.wikia.nocookie.net/alexrider/images/6/6b/Ewan_McGregor.jpg/revision/latest?cb=20201109110243"
        val starWarsThirdCast3 = "https://qartulad.ge/media/MV5BMTMzMjU5NDA0NV5BMl5BanBnXkFtZTYwMTUwMzI0._V1.jpg"





        // Header Image
        headerImageView = findViewById(R.id.headerImageView)

        // Movie Name
        movieName = findViewById(R.id.movieName)

        // Release Date
        releaseDate = findViewById(R.id.releaseDate)

        // IMDB
        imdb = findViewById(R.id.imdb)

        // Cast Images
        firstCast = findViewById(R.id.firstCast)
        secondCast = findViewById(R.id.secondCast)
        thirdCast = findViewById(R.id.thirdCast)

        val getInfo = intent?.extras?.getString("clickedInfo")

        if(getInfo == "starwars1"){
            movieName.text = "Star Wars Episode I: The Phantom Menace"
            releaseDate.text = "May 19 - 1999"
            imdb.text = "6.5"


            Glide.with(this).load(starWarsFirstCast1).into(firstCast)
            Glide.with(this).load(starWarsSecondCast1).into(secondCast)
            Glide.with(this).load(starWarsThirdCast1).into(thirdCast)
            Glide.with(this).load(starWarsBanner1).into(headerImageView)
        }else if (getInfo == "starwars2"){

            movieName.text = "Star Wars Episode II: Attack Of The Clones"
            releaseDate.text = "May 2 - 2002"
            imdb.text = "6.5"


            Glide.with(this).load(starWarsFirstCast2).into(firstCast)
            Glide.with(this).load(starWarsSecondCast2).into(secondCast)
            Glide.with(this).load(starWarsThirdCast2).into(thirdCast)
            Glide.with(this).load(starWarsBanner2).into(headerImageView)

        }else if (getInfo == "starwars3"){

            movieName.text = "Star Wars Episode III: Revenge Of The Sith"
            releaseDate.text = "May 19 - 2005"
            imdb.text = "7.5"


            Glide.with(this).load(starWarsFirstCast2).into(firstCast)
            Glide.with(this).load(starWarsSecondCast2).into(secondCast)
            Glide.with(this).load(starWarsThirdCast2).into(thirdCast)
            Glide.with(this).load(starWarsBanner2).into(headerImageView)


        }



    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }
}