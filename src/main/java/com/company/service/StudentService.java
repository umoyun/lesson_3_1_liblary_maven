package com.company.service;

import com.company.enums.StudentStatus;
import com.company.model.Student;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void userList() {
        List<Student> studentList = studentRepository.selectAll();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void changeStatus(String phone) {
        Student student = studentRepository.getStudentByPhone(phone);
        if (student == null) {
            System.out.println("Kalla No Student");
            return;
        }

        if (student.getStatus().equals(StudentStatus.ACTIVE)) {
            student.setStatus(StudentStatus.BLOCK);
        } else {
            student.setStatus(StudentStatus.ACTIVE);
        }
        studentRepository.changeStatus(student.getId(), student.getStatus());
        System.out.println("Success");
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
