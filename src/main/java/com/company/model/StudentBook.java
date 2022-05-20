package com.company.model;

import com.company.enums.StudentBookStatus;

import java.time.LocalDate;

public class StudentBook {

    private int id;
    private int studentId;
    private int bookId;
    private StudentBookStatus status;
    private LocalDate takkenDate;
    private LocalDate returnedDate;

    public StudentBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public StudentBookStatus getStatus() {
        return status;
    }

    public void setStatus(StudentBookStatus status) {
        this.status = status;
    }

    public LocalDate getTakkenDate() {
        return takkenDate;
    }

    public void setTakkenDate(LocalDate takkenDate) {
        this.takkenDate = takkenDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "StudentBook{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                ", status=" + status +
                ", takkenDate=" + takkenDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
