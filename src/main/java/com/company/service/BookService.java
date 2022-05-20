package com.company.service;

import com.company.container.ComponentContainer;
import com.company.model.Book;
import com.company.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        // validation
        if (bookRepository.isExistBook(book.getName())) {
            System.out.println("Already exist");
        } else {
            bookRepository.saveBook(book);
            System.out.println("success");
        }
    }

    public void bookList() {
        List<Book> bookList = bookRepository.selectAllBook();
        int index = 1;
        for (Book book : bookList) {
            System.out.println(index + ". " + book);
            index++;
        }
    }

    public void updateBookService(int id, String name, String author) {
        // validation
        if (!bookRepository.isExsistBookId(id)) {
            System.out.println("not found id");
            return;
        }
        // name unique
        /*if (bookRepository.isExistBook(name)) {
            System.out.println("Already exist");
        }*/
        bookRepository.updateBook(id, name, author);
    }

    public void deleteBook(int id) {
        if (!bookRepository.isExsistBookId(id)) {
            System.out.println("not found id");
            return;
        }
        bookRepository.deleteBook(id);
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
