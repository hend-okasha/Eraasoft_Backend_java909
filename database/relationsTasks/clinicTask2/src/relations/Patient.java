package relations;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    public int id;
    public String name;
    public int age;

    public List<Doctor> doctors;


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
