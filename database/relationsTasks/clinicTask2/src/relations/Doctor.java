package relations;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    public int id;
    public String name;
    public double salary;

    public List<Patient> patients;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
