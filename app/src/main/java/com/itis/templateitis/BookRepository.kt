package com.itis.templateitis

import com.itis.templateitis.header.BookWrapper

object BookRepository {

    val books = arrayListOf(
        Book(
            "Мастер",
            "Маргарита",
            "https://flysmartavia.com/media/images/city/20200707_kaz.jpg"
        ),
        Book(
            "Булгаков",
            "Капитанская",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        ),
        Book(
            "Дочка",
            "Властелин",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        ),
        Book(
            "Дочка",
            "Властелин",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        ),
        Book(
            "Дочка",
            "Властелин",
            "https://www.tourprom.ru/site_media/cache/22/91/22913546eb2587bbb353d4c166e41c70.jpg"
        ),
        Book(
            "Колец",
            "Мартин",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        ),
        Book(
            "Колец",
            "Мартин",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMp0NZp_Dsz-ppU26mockuxbwNAlg5fVby8g&usqp=CAU"
        ),
    )

    val booksWithHeader = arrayListOf(
        BookWrapper("Книги"),
        BookWrapper(book = Book(
            "Мастер",
            "Маргарита",
            "https://flysmartavia.com/media/images/city/20200707_kaz.jpg"
        )),
        BookWrapper(book = Book(
            "Колец",
            "Мартин",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        )),
        BookWrapper("Фильмы"),
        BookWrapper(book = Book(
            "Дочка",
            "Властелин",
            "https://www.tourprom.ru/site_media/cache/22/91/22913546eb2587bbb353d4c166e41c70.jpg"
        )),
        BookWrapper(book = Book(
            "Властелин Колец",
            "Толкин",
            "https://zooblog.ru/wp-content/uploads/2021/01/atlantic-bottlenose-dolphin-jumping-high-during-a-dolphin-training-demonstration-154724035-59ce93949abed50011352530-1140x694.jpg"
        )),
    )
}
