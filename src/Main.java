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
            double operation;

            try {
                operation = Double.parseDouble(scn.nextLine());
                flag = false;
                switch ((int) operation) {
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
        System.out.println("-------------------------");
        Long empCode = getEmpCode();
        if (valid(empCode)) {
            employeeList = employeeList.stream().filter(employee -> employee.getEmpId() != empCode).collect(Collectors.toList());
            System.out.println("-------------------------");
            System.out.println("Employee deleted");
            System.out.println("-------------------------");
            System.out.println("Updated employee list");
            display();
        } else {
            System.out.println("Doesn't exists employee, Please add first employee details ! ");
            delete();
        }
    }

    private static void getAll() {
        System.out.println("----------------------------------");
        System.out.println("All employee details : ");
        display();
    }

    private static void edit() {
        System.out.println("-------------------------");
        Long empCode = getEmpCode();
        if (valid(empCode)) {
            String updatedFullName = getFullName();
            if (updatedFullName != null) {
                String updatedTechStack = getTechStack();
                if (updatedTechStack != null) {
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
                }
            } else {
                System.out.println("Please enter valid full name!");
            }
        }else{
            System.out.println("Employee doesn't exists in Database, Please add details first");
            edit();
        }
    }

    public static void add() {
        System.out.println("-------------------------");
        Long empCode = getEmpCode();
//        Employee optionalEmployee = employeeList.stream().filter(emp -> emp.getEmpId() == empCode).findFirst().get();
        if ((empCode != -1) && (!valid(empCode))) {
            String fullName = getFullName();
            if (fullName != null) {
                String techStack = getTechStack();
                if (techStack != null) {
                    employeeList.add(new Employee(empCode, fullName, techStack));
                    System.out.println("-------------------------");
                    System.out.println("Employee details added :");
                    display();
                } else {
                    System.out.println("Enter valid tech");
                }
            } else {
                System.out.println("Please enter valid full name!");
            }
        } else {
            System.out.println("User already exists, Please enter unique code.");
            add();
        }
    }

    public static Long getEmpCode() {
        try {
            System.out.print("Enter Employee's code : ");
            Long empCode = Long.parseLong(scn.nextLine());
            if (String.valueOf(empCode).matches("^(?=[\\S\\s]{1,10}$)[\\S\\s]*")) {
                return empCode;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("------ Please enter valid code --------");
            return getEmpCode();
        }
        return Long.valueOf(-1);
    }

    public static String getFullName() {
        try {
            System.out.print("Enter full-name : ");
            String fullName = scn.nextLine();
            if ((fullName != null) && (!fullName.equals(""))
                    && (fullName.matches("^[a-zA-Z \\-\\.\\']*$")) && (fullName.length() < 40)) {
                return fullName;
            } else {
                System.out.println("------- Please enter valid name or length is too big ! ---------");
                return getFullName();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter valid input");
        }
        return null;
    }

    public static String getTechStack() {
        try {
            System.out.print("Enter tech stack : ");
            String techStack = scn.nextLine();
            if (((techStack != null) && (!techStack.equals(""))
                    && (techStack.matches("^[a-zA-Z \\-\\.\\']*$")))) {
                return techStack;
            } else {
                System.out.println("----------- Please enter valid tech ---------");
                return getTechStack();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter valid input");
        }
        return null;
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

    public static boolean valid(Long empCode) {
        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.getEmpId() == empCode).findAny();
        if (employee.isPresent()) {
            return true;
        }
        return false;
    }
}