package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

class IOoutTest {
    public static void main(String[] args) {
        Employee[] staffes = new Employee[3];
        staffes[0] = new Employee("中", 1, 1988, 1, 8);
        staffes[1] = new Employee("男", 10, 1998, 1, 8);
        staffes[2] = new Employee("北", 100, 2018, 1, 8);
        try (PrintWriter pw = new PrintWriter("employee.txt", StandardCharsets.UTF_8)) {
            writeData(staffes, pw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader("employee.txt"))) {
            Employee[] newstaff = readData(in);
            for (Employee e : newstaff) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    private static Employee[] readData(BufferedReader in) throws IOException {
        int n = Integer.parseInt(in.readLine());
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.name + "|" + e.salary + "|" + e.hireDate);
    }

    public static Employee readEmployee(BufferedReader in) throws IOException {
        String line = in.readLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        int salary = Integer.parseInt(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}


class Employee {
    String name;
    int salary;
    LocalDate hireDate;

    public Employee(String aname, int asalary, int ayear, int amonth, int aday) {
        name = aname;
        salary = asalary;
        hireDate = LocalDate.of(ayear, amonth, aday);
    }

    public String toString() {
        return name + ":" + salary + ":" + hireDate;
    }
}
