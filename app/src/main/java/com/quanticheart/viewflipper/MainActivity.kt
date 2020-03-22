package com.quanticheart.viewflipper

import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var initialX = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play.setOnClickListener {
            view_flipper.apply {
                isAutoStart = true
                flipInterval = 1000
                startFlipping()
            }
            Snackbar.make(it, "Automatic view flipping has started", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        stop.setOnClickListener {
            view_flipper.stopFlipping()
            Snackbar.make(it, "Automatic view flipping has stopped", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onTouchEvent(touchevent: MotionEvent): Boolean {
        when (touchevent.action) {
            MotionEvent.ACTION_DOWN -> initialX = touchevent.x
            MotionEvent.ACTION_UP -> {
                val finalX = touchevent.x
                if (initialX > finalX) {

                    if (view_flipper.displayedChild != 1) {

                        view_flipper.inAnimation = AnimationUtils.loadAnimation(
                            this,
                            R.anim.in_from_left
                        )
                        view_flipper.outAnimation = AnimationUtils.loadAnimation(
                            this,
                            R.anim.out_from_left
                        )
                        view_flipper.showNext()
                    }

                } else {

                    if (view_flipper.displayedChild != 0) {

                        view_flipper.inAnimation = AnimationUtils.loadAnimation(
                            this,
                            R.anim.in_from_right
                        )
                        view_flipper.outAnimation = AnimationUtils.loadAnimation(
                            this,
                            R.anim.out_from_right
                        )
                        view_flipper.showPrevious()
                    }
                }
            }
        }
        return false
    }
}
