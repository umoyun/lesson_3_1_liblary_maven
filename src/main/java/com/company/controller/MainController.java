package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.model.Student;
import com.company.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    public void start() {
        while (true) {
            show_menu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerNum.nextInt();

            switch (action) {
                case 1:
                    get_student_detail();
                    break;
                case 2:
                    authorization();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Mazgi to'gri sonni tanlang.");
            }
        }
    }

    public void show_menu() {
        System.out.println("** Menu **");
        System.out.println("1. Registration");
        System.out.println("2. Authorization");
        System.out.println("0. Exit");
    }

    public void get_student_detail() {
        System.out.print("Enter Name: ");
        String name = ComponentContainer.scannerStr.next();

        System.out.print("Enter Surname: ");
        String surname = ComponentContainer.scannerStr.next();

        System.out.print("Enter Phone: ");
        String phone = ComponentContainer.scannerStr.next();

        System.out.print("Enter Password: ");
        String password = ComponentContainer.scannerStr.next();

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPhone(phone);
        student.setPassword(password);

        mainService.registration(student);
    }

    public void authorization() {
        System.out.print("Enter Phone: ");
        String phone = ComponentContainer.scannerStr.next();

        System.out.print("Enter Password: ");
        String password = ComponentContainer.scannerStr.next();
        mainService.aut(phone, password);
    }
}
