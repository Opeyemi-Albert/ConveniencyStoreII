package Model;
import Enum.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagerTest {

    @Test
    void hireCashierAWithBSCQualificationAndBelow25() {
        Manager manager= new Manager("Robert Gray","gray2@gmail.com",
                "096873636",44, Gender.Male, 150000.00);

        Applicant applicant = new Applicant("Matthew Emma", "matt@gmail.ocm",
                "087363682", 23, Gender.Female, Qualification.BSC);
        Cashier cashier = new Cashier(applicant.getFullName(), applicant.getEmail(), applicant.getPhoneNumber(), applicant.getAge(), applicant.getGender(), 100000.00);

        Cashier result = manager.hireCashier(applicant);

        Assertions.assertEquals(cashier.getFullName(), result.getFullName());
    }
    @Test
    void doNotTakeCashierWithoutABSCQualificationAndAbove25() {
        Manager manager= new Manager("Robert Gray","gray2@gmail.com",
                "096873636",44, Gender.Male, 150000.00);

       Applicant applicant = new Applicant("Matthew Emma", "matt@gmail.ocm",
                "087363682", 29, Gender.Female, Qualification.BSC);

        Cashier result = manager.hireCashier(applicant);

        Assertions.assertEquals(null, result);
    }
    }
