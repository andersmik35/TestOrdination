package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    private DagligFast dagligFast;


    @Test
    void samletDosisTC1() {
        dagligFast = new DagligFast(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 4), 1, 2, 0, 0);
        assertEquals(12, dagligFast.samletDosis());
    }
    @Test
    void samletDosisTC2() {
        dagligFast = new DagligFast(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 4), 0, 0, 0, 0);
        assertEquals(0, dagligFast.samletDosis());
    }


}