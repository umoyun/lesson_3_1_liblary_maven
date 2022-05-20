package com.company;

import com.company.config.Config;
import com.company.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);

        MainController mainController
                = (MainController) applicationContext.getBean("mainController");
        mainController.start();
    }
}
