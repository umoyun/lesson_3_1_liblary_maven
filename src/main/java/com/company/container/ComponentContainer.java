package com.company.container;

import com.company.model.Student;
import java.util.Scanner;

public class ComponentContainer {
    public static final Scanner scannerNum = new Scanner(System.in);
    public static final Scanner scannerStr = new Scanner(System.in);
    public static Student currentStudent = null;
}
