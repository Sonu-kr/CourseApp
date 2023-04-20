package com.example.reccardapp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

class WatchActivity : AppCompatActivity() {
    var exoPlayer: ExoPlayer? = null
    lateinit var bt_fullscreen: ImageView
    var isFullScreen = false
    var isLock = false
    var handler: Handler? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_activity)
        handler = Handler(Looper.getMainLooper())
        val playerView = findViewById<PlayerView>(R.id.player)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        bt_fullscreen = findViewById(R.id.bt_fullscreen)
        val bt_lockscreen = findViewById<ImageView>(R.id.exo_lock)
        //toggle button with change icon fullscreen or exit fullscreen
        //the screen can rotate base on you angle direction sensor
        bt_fullscreen.setOnClickListener(View.OnClickListener { view: View? ->
            requestedOrientation = if (!isFullScreen) {
                bt_fullscreen.setImageDrawable(
                    ContextCompat
                        .getDrawable(applicationContext, R.drawable.ic_baseline_fullscreen_exit)
                )
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                bt_fullscreen.setImageDrawable(
                    ContextCompat
                        .getDrawable(applicationContext, R.drawable.ic_baseline_fullscreen)
                )
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            isFullScreen = !isFullScreen
        })
        bt_lockscreen.setOnClickListener { view: View? ->
            //change icon base on toggle lock screen or unlock screen
            if (!isLock) {
                bt_lockscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_lock
                    )
                )
            } else {
                bt_lockscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_outline_lock_open
                    )
                )
            }
            isLock = !isLock
            //method for toggle will do next
            lockScreen(isLock)
        }
        //instance the player with skip back duration 5 second or forward 5 second
        //5000 millisecond = 5 second
        exoPlayer = ExoPlayer.Builder(this)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
        playerView.player = exoPlayer
        //screen alway active
        playerView.keepScreenOn = true
        //track state
        exoPlayer!!.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                //when data video fetch stream from internet
                if (playbackState == Player.STATE_BUFFERING) {
                    progressBar.visibility = View.VISIBLE
                } else if (playbackState == Player.STATE_READY) {
                    //then if streamed is loaded we hide the progress bar
                    progressBar.visibility = View.GONE
                }
                if (!exoPlayer!!.playWhenReady) {
                    handler!!.removeCallbacks(updateProgressAction)
                } else {
                    onProgress()
                }
            }
        })
        //pass the video link and play
//        Uri videoUrl = Uri.parse("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4");
        val videoUrl =
            Uri.parse("https://firebasestorage.googleapis.com/v0/b/canmnvas.appspot.com/o/Introduction%20to%20C%2B%2B%2C%20Installing%20VS%20Code%2C%20g%2B%2B%20%26%20more%20_%20C%2B%2B%20Tutorials%20for%20Beginners%20%231.mp4?alt=media&token=101f378e-c0fa-47a4-97ef-cdd7781445b2")
        val media = MediaItem.fromUri(videoUrl)
        exoPlayer!!.setMediaItem(media)
        exoPlayer!!.prepare()
        exoPlayer!!.play()
    }

    private val updateProgressAction = Runnable { onProgress() }

    //at 4 second
    var ad: Long = 4000
    var check = false
    private fun onProgress() {
        val player = exoPlayer
        val position = player?.currentPosition ?: 0
        handler!!.removeCallbacks(updateProgressAction)
        val playbackState = player?.playbackState ?: Player.STATE_IDLE
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            var delayMs: Long
            if (player!!.playWhenReady && playbackState == Player.STATE_READY) {
                delayMs = 1000 - position % 1000
                if (delayMs < 200) {
                    delayMs += 1000
                }
            } else {
                delayMs = 1000
            }

            //check to display ad
            if (ad - 3000 <= position && position <= ad && !check) {
                check = true
                initAd()
            }
            handler!!.postDelayed(updateProgressAction, delayMs)
        }
    }

    var rewardedInterstitialAd: RewardedInterstitialAd? = null
    private fun initAd() {
        if (rewardedInterstitialAd != null) return
        MobileAds.initialize(this)
        RewardedInterstitialAd.load(this, "ca-app-pub-3940256099942544/5354046379",
            AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(p0: RewardedInterstitialAd) {
                    rewardedInterstitialAd = p0
                    rewardedInterstitialAd!!.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                Log.d("WatchActivity_AD", adError.message)
                            }

                            override fun onAdShowedFullScreenContent() {
                                handler!!.removeCallbacks(updateProgressAction)
                            }

                            override fun onAdDismissedFullScreenContent() {
                                //resume play
                                exoPlayer!!.playWhenReady = true
                                rewardedInterstitialAd = null
                                check = false
                            }
                        }
                    val sec_ad_countdown = findViewById<LinearLayout>(R.id.sect_ad_countdown)
                    val tx_ad_countdown = findViewById<TextView>(R.id.tx_ad_countdown)
                    sec_ad_countdown.visibility = View.VISIBLE
                    object : CountDownTimer(4000, 1000) {
                        override fun onTick(l: Long) {
                            tx_ad_countdown.text = "Ad in " + l / 1000
                        }

                        override fun onFinish() {
                            sec_ad_countdown.visibility = View.GONE
                            rewardedInterstitialAd!!.show(this@WatchActivity) { rewardItem: RewardItem? -> }
                        }
                    }.start()
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    rewardedInterstitialAd = null
                }
            })
    }

    fun lockScreen(lock: Boolean) {
        //just hide the control for lock screen and vise versa
        val sec_mid = findViewById<LinearLayout>(R.id.sec_controlvid1)
        val sec_bottom = findViewById<LinearLayout>(R.id.sec_controlvid2)
        if (lock) {
            sec_mid.visibility = View.INVISIBLE
            sec_bottom.visibility = View.INVISIBLE
        } else {
            sec_mid.visibility = View.VISIBLE
            sec_bottom.visibility = View.VISIBLE
        }
    }

    //when is in lock screen we not accept for backpress button
    override fun onBackPressed() {
        //on lock screen back press button not work
        if (isLock) return

        //if user is in landscape mode we turn to portriat mode first then we can exit the app.
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bt_fullscreen!!.performClick()
        } else super.onBackPressed()
    }

    // pause or release the player prevent memory leak
    override fun onStop() {
        super.onStop()
        exoPlayer!!.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer!!.release()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer!!.pause()
    }
}