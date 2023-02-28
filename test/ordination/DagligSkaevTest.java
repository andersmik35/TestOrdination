package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    private DagligSkaev dagligSkaev;

    @BeforeEach
    void setUp() {
        dagligSkaev = new DagligSkaev(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 3));
    }

    @Test
    void samletDosisTC1() {
        dagligSkaev.opretDosis(LocalTime.of(7, 0), 3);
        dagligSkaev.opretDosis(LocalTime.of(12, 0), 2);
        dagligSkaev.opretDosis(LocalTime.of(18, 0), 2);
        double tc1 = dagligSkaev.samletDosis();
        assertEquals(21, tc1);
    }
    @Test
    void samletDosisTC2(){
        dagligSkaev.opretDosis(LocalTime.of(7, 0), 0);
        dagligSkaev.opretDosis(LocalTime.of(12, 0), 0);
        dagligSkaev.opretDosis(LocalTime.of(18, 0), 0);
        double tc2 = dagligSkaev.samletDosis();
        assertEquals(0, tc2);
    }

}