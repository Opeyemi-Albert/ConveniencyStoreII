package Model;

import Enum.Gender;
import Enum.Qualification;
import Interface.ManagerService;

public class Manager extends Staff implements ManagerService {
    private String staffRecords;

    public Manager(String fullName, String email, String phoneNumber, Integer age, Gender gender, Double salary) {
        super(fullName, email, phoneNumber, age, gender, salary);
    }


    public Manager() {
    }


    public String getStaffRecords() {
        return staffRecords;
    }

    public void setStaffRecords(String staffRecords) {
        this.staffRecords = staffRecords;
    }
    @Override
    public Cashier hireCashier(Applicant applicant) {
       if (applicant.getQualification().equals(Qualification.BSC) && applicant.getAge() <= 25){
        Cashier cashier = new Cashier(applicant.getFullName(), applicant.getEmail(), applicant.getPhoneNumber(), applicant.getAge(), applicant.getGender(), 100000.00);

        return cashier;
       }
       else {
           System.out.println("You are not eligible!");
       }
        return null;
    }
}

