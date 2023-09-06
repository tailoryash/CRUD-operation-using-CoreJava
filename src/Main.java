import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Employee> employeeList = new ArrayList<>();
    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        boolean flag;

        do {
            flag = true;
            System.out.println("------------------------------------------------");
            System.out.println("Welcome to Promount technologies's Employee portal");
            System.out.println("Press below options to perform operation");
            System.out.println("1. Add Employee");
            System.out.println("2. Retrive employee info");
            System.out.println("3. Delete employee");
            System.out.println("4. Edit Employee");
            System.out.println("5. Exit");

            System.out.print("Enter option : ");
            int operation;

            try {
                operation = Integer.parseInt(scn.nextLine());
                flag = false;
                switch (operation) {
                    case 1:
                        add();
                        break;
                    case 2:
                        getAll();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        edit();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid input !!!");
                        break;
                }
            } catch (Exception exp) {
                System.out.println("Please enter valid (1-5) input only !!");
                flag = false;
            }
        } while (!flag);

    }


    private static void delete() {
        try {
            System.out.println("-------------------------");
            System.out.print("Enter empcode to delete details : ");
            Long empCode = Long.parseLong(scn.nextLine());
            if (valid(empCode)) {
                employeeList = employeeList.stream().filter(employee -> employee.getEmpId() != empCode).collect(Collectors.toList());
                System.out.println("-------------------------");
                System.out.println("Employee deleted");
                System.out.println("-------------------------");
                System.out.println("Updated employee list");
                display();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter valid input");
            delete();
        }
    }

    public static boolean valid(Long empCode) {
        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.getEmpId() == empCode).findFirst();
        if (employee.isPresent()) {
            return true;
        }
        return false;
    }

    private static void getAll() {
        System.out.println("----------------------------------");
        System.out.println("All employee details : ");
        display();
    }

    private static void edit() {
        while (true) {
            try {
                System.out.println("-------------------------");
                System.out.print("Enter empcode to edit details : ");
                Long empCode = Long.parseLong(scn.nextLine());
                if (valid(empCode)) {
                    System.out.print("Enter update full-name : ");
                    String updatedFullName = scn.nextLine();
                    if ((updatedFullName != null) && (!updatedFullName.equals(""))
                            && (updatedFullName.matches("^[a-zA-Z \\-\\.\\']*$"))) {
                        System.out.print("Enter update tech stack : ");
                        String updatedTechStack = scn.nextLine();
                        if (((updatedTechStack != null) && (!updatedTechStack.equals(""))
                                && (updatedTechStack.matches("^[a-zA-Z \\-\\.\\']*$")))) {

                            Employee oldEmployee = employeeList.stream().filter(employee -> employee.getEmpId() == empCode).findFirst().get();
                            oldEmployee.setEmpId(empCode);
                            oldEmployee.setTech(updatedTechStack);
                            oldEmployee.setFullName(updatedFullName);
                            System.out.println("-------------------------");
                            System.out.println("Employee updated successfully");
                            System.out.println("Updated employee list");
                            display();
                        } else {
                            System.out.println("Enter valid tech");
                            continue;
                        }
                    } else {
                        System.out.println("Please enter valid full name!");
                        continue;
                    }
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Please enter valid input");
                edit();
            }
        }


    }

    public static void add() {
        while (true) {
            try {
                System.out.println("-------------------------");
                System.out.print("Enter Employee's code : ");
                Long empCode = Long.parseLong(scn.nextLine());
//        scn.nextLine();
                System.out.print("Enter full-name : ");
                String fullName = scn.nextLine();
                if ((fullName != null) && (!fullName.equals(""))
                        && (fullName.matches("^[a-zA-Z \\-\\.\\']*$"))) {
                    System.out.print("Enter tech stack : ");
                    String techStack = scn.nextLine();
                    if (((techStack != null) && (!techStack.equals(""))
                            && (techStack.matches("^[a-zA-Z\\\\s]*$")))) {
                        employeeList.add(new Employee(empCode, fullName, techStack));
                        System.out.println("-------------------------");
                        System.out.println("Employee details added :");
                        display();
                        break;
                    } else {
                        System.out.println("Enter valid tech");
                        continue;
                    }
                } else {
                    System.out.println("Please enter valid full name!");
                    continue;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Please enter valid input");
                add();
            }
        }

    }

    public static void display() {

        System.out.println("------------------------------------------------");
        System.out.printf("%5s %10s %5s", "EMPLOYEE CODE", "NAME", "TECH");
        System.out.println();
        System.out.println("------------------------------------------------");

        for (Employee employee : employeeList) {
            System.out.format("%7s %14s %7s", employee.getEmpId(), employee.getFullName(), employee.getTech());
            System.out.println();
        }
        System.out.println("------------------------------------------------");
    }
}