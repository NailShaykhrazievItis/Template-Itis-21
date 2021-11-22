package com.itis.templateitis

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var currentSong: Song? = null

    inner class LocaleBinder : Binder() {

        fun sum(a: Int, b: Int): Int = this@MusicService.sum(a, b)

        fun play() {
            if (Random.nextBoolean()) playLocaleMusic() else playRemoteMusic()
        }

        fun pause() = this@MusicService.pause()

        fun getSong(): Song? = currentSong

        fun setCurrentSong(song: Song?) {
            showSong(song)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = LocaleBinder()

    private fun sum(a: Int, b: Int): Int {
        Log.e("Service", "Sum: ${a + b}")
        return a + b
    }

    private fun pause() = mediaPlayer.pause()

    private fun showSong(song: Song?) {
        currentSong = song
        Log.e("Song from activity", song?.name.toString())
    }

    /**
     * When you call create, file is already prepared and don't need call prepare() method
     */
    private fun playLocaleMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.promise)
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop() // or call next() for change track
            }
        }
    }

    /**
     * If you use setDataSource() then need call prepare() method before calling start()
     */
    private fun playRemoteMusic() {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer.run {
            setDataSource("https://zaycev.net/musicset/dl/9bc817a0041fd20e8a6b6d3e13bb84db/8633041.json?spa=false")
//            setDataSource("https://zaycev.net/musicset/dl/c75625f883500bcf31854123b14d199c/17405061.json?spa=false")
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
    }
}
