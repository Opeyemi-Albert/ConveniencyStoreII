package Model;
import Enum.Gender;

public abstract class Staff extends Person{
    private static Integer numberOfStaff = 0;
    private  Integer staffID;
    private Double salary;


    public Staff(String fullName, String email, String phoneNumber, Integer age, Gender gender, Double salary) {
        super(fullName, email, phoneNumber, age, gender);
        this.staffID = ++numberOfStaff;
        this.salary = salary;
    }

    public Staff() {
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
