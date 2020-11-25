package com.example.betnews.utils

enum class FragmentTag(tag: String) {
    DETAIL_FRAGMENT("detail Fragment") ;
}

enum class FragmentOpenType {
    ADD,
    REPLACE
}

enum class FragmentAnimType {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT,
    BOTTOM_ENTER,
    BOTTOM_TO_TOP
}