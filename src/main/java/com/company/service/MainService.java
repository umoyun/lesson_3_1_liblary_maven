package com.company.service;

import com.company.container.ComponentContainer;
import com.company.model.Student;
import com.company.enums.StudentRole;
import com.company.enums.StudentStatus;
import com.company.controller.AdminController;
import com.company.controller.UserController;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public class MainService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private UserController userController;

    public void registration(Student student) {
        if (!isValid(student)) {
            return;
        }

        if (studentRepository.isPhoneExists(student.getPhone())) {
            System.out.println("Mazgi bu raham borqu.");
        } else {
            studentRepository.save_student(student);
        }
    }

    public void aut(String phone, String password) {
        Student student = studentRepository.auth2(phone, password);
        if (student == null) {
            System.out.println("Parol yoki Phone xato :)");
            return;
        }

        if (!student.getStatus().equals(StudentStatus.ACTIVE)) {
            System.out.println("Siz active emassiz!");
            return;
        }

        ComponentContainer.currentStudent = student;

        if (student.getRole().equals(StudentRole.USER)) {
            userController.userMenu();
        } else {
            adminController.adminMenu();
        }
    }

    private boolean isValid(Student student) {
        if (student.getName() == null || student.getName().length() < 2) {

            System.out.println("Name is wrong.");
            return false;
        }
        if (student.getPhone() == null || !Pattern.matches("[0-9]{12}", student.getPhone())) {
            System.out.println("Phone is wrong.");
            return false;
        }
        if (student.getSurname() == null || student.getSurname().length() < 2) {

            System.out.println("Surname is wrong.");
            return false;
        }
        if (student.getPassword() == null || student.getPassword().length() < 8) {

            System.out.println("Password is wrong.");
            return false;
        }
        if (studentRepository.isPhoneExists(student.getPhone())) {
            System.out.println("Mazgi bu raham borqu.");
            return false;
        }
        return true;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
