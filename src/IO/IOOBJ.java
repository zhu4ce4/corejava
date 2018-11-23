package IO;

import java.io.*;

public class IOOBJ {
    public static void main(String[] args) {
        Employee harry = new Employee("harry hello", 10, 2019, 1, 1);
        Manager boss = new Manager("boss", 999, 1999, 9, 9);
        Manager bosswife = new Manager("bosswife", 888, 2001, 1, 1);
        boss.setSecretary(harry);
        bosswife.setSecretary(harry);

        Employee[] staffes = new Employee[3];
        staffes[0] = harry;
        staffes[1] = boss;
        staffes[2] = bosswife;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.txt"))) {
            oos.writeObject(staffes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.txt"))) {
            Employee[] newstaffes = (Employee[]) ois.readObject();
            newstaffes[0].raiseSalary(10);
            for (Employee staff : newstaffes) {
                System.out.println(staff);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
