package Model;
import Enum.Qualification;
import Enum.Gender;


public class Applicant extends Person{

    private Qualification qualification;

    public Applicant(String fullName, String email, String phoneNumber, Integer age, Gender gender, Qualification qualification) {
        super(fullName, email, phoneNumber, age, gender);
        this.qualification = qualification;
    }
    public Applicant() {
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }


}
