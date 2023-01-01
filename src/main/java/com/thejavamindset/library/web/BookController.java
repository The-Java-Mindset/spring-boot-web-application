package com.thejavamindset.library.web;

import com.thejavamindset.library.model.Book;
import com.thejavamindset.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showBooksList(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return View.INDEX;
    }
}
