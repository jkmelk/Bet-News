package com.example.betnews.utils

import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.betnews.R

fun Fragment.open(
    container: Int = R.id.main_container,
    fragmentManager: FragmentManager?,
    whitBackStack: Boolean = true,
    isAnimated: Boolean = true,
    openType: FragmentOpenType = FragmentOpenType.ADD,
    tag: FragmentTag? = null,
    animateType: FragmentAnimType = FragmentAnimType.LEFT_TO_RIGHT,
    duration: Long = 0
) {

    val fragment = tag?.let { fragmentManager?.findFragmentByTag(it.name) }

    val transaction = fragmentManager?.beginTransaction()
    fragment?.let { fragmentManager?.beginTransaction()?.remove(fragment)?.commit() }
    if (isAnimated) {
        val list = animList(animateType)
        transaction?.setCustomAnimations(list[0], list[1], list[2], list[3])
    } else transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    if (openType == FragmentOpenType.ADD) transaction?.add(container, this, tag?.name)
    else transaction?.replace(container, this, tag?.name)
    if (whitBackStack) transaction?.addToBackStack(tag?.name)
    Handler().postDelayed({ transaction?.commit() }, duration)
}

private fun animList(animType: FragmentAnimType) = when (animType) {
    FragmentAnimType.LEFT_TO_RIGHT -> rightAnimation()
    else-> rightToLeftAnimation()
}

fun rightToLeftAnimation(): MutableList<Int> {
    val list = mutableListOf<Int>()
    list.add(R.anim.slide_in_left)
    list.add(R.anim.slide_out_right)
    list.add(R.anim.slide_in_right)
    list.add(R.anim.slide_out_left)
    return list
}

private fun rightAnimation(): MutableList<Int> {
    val list = mutableListOf<Int>()
    list.add(R.anim.slide_in_right)
    list.add(R.anim.slide_out_left)
    list.add(R.anim.slide_in_left)
    list.add(R.anim.slide_out_right)
    return list
}


