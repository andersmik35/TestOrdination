package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {


    private PN pn;


    @BeforeEach
    void setup() {
        pn = new PN(LocalDate.of(2023, 02, 20), LocalDate.of(2023, 02, 26), 2);

    }

    @Test
    void givDosisTC1() {
        boolean tc = pn.givDosis(LocalDate.of(2023, 02, 19));
        assertEquals(false, tc);
    }

    @Test
    void givDosisTC2() {
        boolean tc = pn.givDosis(LocalDate.of(2023, 02, 27));
        assertEquals(false, tc);
    }

    @Test
    void givDosisTC3() {
        boolean tc = pn.givDosis(LocalDate.of(2023, 02, 20));
        assertEquals(true, tc);
    }

    @Test
    void givDosisTC4() {
        boolean tc = pn.givDosis(LocalDate.of(2023, 02, 23));
        assertEquals(true, tc);
    }

    @Test
    void givDosisTC5() {
        boolean tc = pn.givDosis(LocalDate.of(2023, 02, 26));
        assertEquals(true, tc);
    }

    @Test
    void doegnDosisTC1() {
        double tc = pn.doegnDosis();
        assertEquals(0, tc);
    }

    @Test
    void doegnDosisTC2() {
        pn.givDosis(LocalDate.of(2023, 02, 20));
        double tc = pn.doegnDosis();
        assertEquals(2, tc);
    }

    @Test
    void doegnDosisTC3() {
        pn.givDosis(LocalDate.of(2023, 02, 20));
        pn.givDosis(LocalDate.of(2023, 02, 21));
        double tc = pn.doegnDosis();
        assertEquals(2, tc);

    }

    @Test
    void doegnDosisTC4() {
        pn.givDosis(LocalDate.of(2023, 02, 20));
        pn.givDosis(LocalDate.of(2023, 02, 22));
        double tc = pn.doegnDosis();
        assertEquals(1.3333, tc, 0.0001);

    }

    @Test
    void doegnDosisTC5() {
        pn.givDosis(LocalDate.of(2023, 02, 20));
        pn.givDosis(LocalDate.of(2023, 02, 20));
        pn.givDosis(LocalDate.of(2023, 02, 22));
        double tc = pn.doegnDosis();
        assertEquals(2, tc);

    }

    @Test
    void doegnDosisTC6() {
        pn.givDosis(LocalDate.of(2023, 02, 21));
        pn.givDosis(LocalDate.of(2023, 02, 20));
        double tc = pn.doegnDosis();
        assertEquals(2, tc);

    }
}