package br.com.denilson100.viajabessa.ui.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity

class Animations: AppCompatActivity() {
    fun showCircularAnimationRigthTop(activity: Activity, view: View, idContent: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val centerX = view.right
            val centerY = view.bottom
            val radius = Math.max(view.width, view.height) * 2.0f

            try {
                if (view.visibility == View.INVISIBLE) {

                    view.visibility = View.VISIBLE
                    val animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, radius)
                    animator.duration = 700
                    animator.start()

                } else {
                    Log.d("TAG", "ENTROU NO ELSE")
                    val reveal = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, radius, 0f)
                    reveal.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            view.visibility = View.INVISIBLE
                        }
                    })
                    reveal.start()
                    activity.findViewById<View>(idContent).visibility = View.VISIBLE
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
                activity.finish()
            }

        }
    }
}