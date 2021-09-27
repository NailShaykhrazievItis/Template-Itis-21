package com.itis.templateitis

class Student(
    email: String,
    var studentId: Int
) : UserKt(email), MyCallback {

    init {
        phone
    }

    override fun test() {

    }

    override fun testDefault() {
        1 to 2
    }
}
