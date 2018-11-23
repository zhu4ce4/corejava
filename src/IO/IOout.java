package IO;

import java.io.*;
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


class Employee implements Serializable {
    String name;
    double salary;
    LocalDate hireDate;
    String department="AI comp";    //当前版本增加了一个域数据(在被读入的版本中没有该域数据),对象会被赋值为null,数字为0,boolean值为false;
    //反之,若当前版无该数据域，被读入的旧版本有该数据域，该数据域会被忽略；若当前版本及被读入的版本的数据域存在名字匹配而类型不匹配，
    // 则不兼容，不会尝试进行类型转换
    public static final long serialVersionUID = 1;  //要尝试读入旧版本，需指定旧版本类的uid；若不进行显式指定uid系统会自动计算出一个
//    public static final long serialVersionUID = 2;    //显式修改uid导致不能读入之前存储的对象数据

    public Employee(String aname, int asalary, int ayear, int amonth, int aday) {
        name = aname;
        salary = asalary;
        hireDate = LocalDate.of(ayear, amonth, aday);
    }

    public void raiseSalary(double percentOfRaise) {
        salary *=(1.0 + percentOfRaise / 100.0);
    }

    public String toString() {
        return name + ":\t" + salary + ":\t" + hireDate;
    }
}

class Manager extends Employee {
    private Employee secretary;
    public Manager(String aname, int asalary, int ayear, int amonth, int aday) {
        super(aname, asalary, ayear, amonth, aday);
    }

    public void setSecretary(Employee asecretary) {
        secretary = asecretary;
    }
    public String toString() {
        return department+":"+name + ":\t" + salary + ":\t" + hireDate+":secretary:"+secretary.name;
//        return name + ":\t" + salary + ":\t" + hireDate+":secretary:"+secretary.name;
//        return name + ":\t" + salary + ":\t" + hireDate+":secretary:"+secretary.name+"after";   //如果这个类只有方法产生了
        // 变化,那么在读入新对象数据时不会有任何问题
    }
}
