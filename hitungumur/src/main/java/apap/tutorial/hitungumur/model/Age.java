package apap.tutorial.hitungumur.model;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Age implements Serializable {
    private LocalDate birthdate;

    public Age (LocalDate birthdate){
        this.birthdate = birthdate;
    }

    public int earthianYears() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.getBirthdate(), currentDate).getYears();
    }

    public double mercurianYears() {
        return Math.floor(this.earthianYears() / .24);
    }

    public double venusianYears() {
        return Math.floor(this.earthianYears() / .62);
    }

    public double martianYears() {
        return Math.floor(this.earthianYears() / 1.88);
    }

    public double jovianYears() {
        return Math.floor(this.earthianYears() / 11.86);
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

}
