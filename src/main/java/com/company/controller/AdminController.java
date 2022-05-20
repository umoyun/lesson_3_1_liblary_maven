package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.model.Book;
import com.company.service.BookService;
import com.company.service.StudentBookService;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentBookService studentBookService;

    public void adminMenu() {

        while (true) {
            show_menu();
            System.out.print("Enter operation: ");
            int action = ComponentContainer.scannerNum.nextInt();
            switch (action) {
                case 1 -> addBook();
                case 2 -> bookList();
                case 3 -> update();
                case 4 -> deleteBook();
                case 5 -> studentList();
                case 6 -> changeStatusProfile();
                case 7 -> takenBook();
            }
        }
    }

    public void show_menu() {
        System.out.println("** Admin Menu **");
        System.out.println("1. Book add");
        System.out.println("2. Book list");
        System.out.println("3. Book update");
        System.out.println("4. Book deleted");
        System.out.println("5. Profiles show");
        System.out.println("6. Change profile's status");
        System.out.println("7. Taken book");
        System.out.println("0. Logout");
    }

    public void addBook() {
        System.out.print("Enter book name: ");
        String title = ComponentContainer.scannerStr.next();

        System.out.print("Enter book author: ");
        String author = ComponentContainer.scannerStr.next();

        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);
        bookService.addBook(book);
    }

    public void bookList() {
        System.out.println("All book: ");
        bookService.bookList();
    }

    public void update() {

        System.out.print("Enter book id: ");
        int id = ComponentContainer.scannerNum.nextInt();

        System.out.print("Enter book name: ");
        String title = ComponentContainer.scannerStr.next();

        System.out.print("Enter book author: ");
        String author = ComponentContainer.scannerStr.next();

        bookService.updateBookService(id, title, author);
    }

    public void deleteBook() {
        System.out.print("Enter book id: ");
        int id = ComponentContainer.scannerNum.nextInt();
        bookService.deleteBook(id);
    }

    public void changeStatusProfile() {
        System.out.print("Enter phone: ");
        String phone = ComponentContainer.scannerStr.next();
        studentService.changeStatus(phone);
    }

    private void studentList() {
        studentService.userList();
    }

    public void takenBook() {
        studentBookService.allTakkenBook();
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setStudentBookService(StudentBookService studentBookService) {
        this.studentBookService = studentBookService;
    }
}
