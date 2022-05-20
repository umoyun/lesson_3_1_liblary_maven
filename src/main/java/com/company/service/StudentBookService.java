package com.company.service;

import com.company.container.ComponentContainer;
import com.company.enums.StudentBookStatus;
import com.company.model.StudentBook;
import com.company.repository.BookRepository;
import com.company.repository.StudentBookRepository;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class StudentBookService {

@Autowired
    private BookRepository bookRepository;
@Autowired
    private StudentRepository studentRepository;
@Autowired
    private StudentBookRepository studentBookRepository;

    public void takeBook(StudentBook studentbook) {
        if (!bookRepository.isExsistBookId(studentbook.getBookId())) {
            System.out.println("Book Not Found. Mazgi.");
            return;
        }
        studentBookRepository.takeBook(studentbook);
    }

    public void takkenBook() {
        List<StudentBook> studentBookList = studentBookRepository.getStudentBookList(StudentBookStatus.TAKEN,
                ComponentContainer.currentStudent.getId());

        for (StudentBook studentBook : studentBookList) {
            System.out.println(studentBook);
        }
    }

    public void allTakkenBook() {
        List<StudentBook> studentBookList = studentBookRepository.getStudentBookList(StudentBookStatus.TAKEN);

        for (StudentBook studentBook : studentBookList) {
            System.out.println(studentBook);
        }
    }

    public void returnedBook(int bId, int sid) {

        StudentBook studentBook = studentBookRepository.getStudentBook(bId, sid);
        if (studentBook == null) {
            System.out.println("Mazgi.");
            return;
        }

        studentBook.setReturnedDate(LocalDate.now());
        studentBook.setStatus(StudentBookStatus.RETURNED);

        studentBookRepository.returnedBook(studentBook);

    }

    public void history() {
        studentBookRepository.getStudentBookList(StudentBookStatus.RETURNED, ComponentContainer.currentStudent.getId());
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void setStudentBookRepository(StudentBookRepository studentBookRepository) {
        this.studentBookRepository = studentBookRepository;
    }
}
