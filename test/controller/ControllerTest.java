package controller;

import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.Ordination;
import ordination.Dosis;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.EventHandler;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private Patient patient;
    private Laegemiddel laegemiddel;
    private LocalDate startdato;
    private LocalDate slutdato;
    private LocalTime[] klokkeslet;
    private double[] enheder;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        controller.createSomeObjects();
        patient = new Patient("00000000", "Anders And", 10);
        laegemiddel = controller.getAllLaegemidler().get(1); // paracetamol
        startdato = LocalDate.of(2023, 2, 20);
        slutdato = LocalDate.of(2023, 2, 26);

        klokkeslet = new LocalTime[]{LocalTime.of(12, 0), LocalTime.of(12, 40),
                LocalTime.of(16, 0), LocalTime.of(18, 45)};
        enheder = new double[]{0.5, 1, 2.5, 3};
    }

    @Test
    void anbefaletDosisPrDoegnTC1() {
        assertEquals(1, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC2() {
        patient.setVaegt(25);
        assertEquals(1, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC3() {
        patient.setVaegt(26);
        assertEquals(1.5, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC4() {
        patient.setVaegt(120);
        assertEquals(1.5, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC5() {
        patient.setVaegt(121);
        assertEquals(2, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC6() {
        patient.setVaegt(130);
        assertEquals(2, controller.anbefaletDosisPrDoegn(patient, laegemiddel));
    }
    @Test
    void anbefaletDosisPrDoegnTC7() {
        assertThrows(IllegalArgumentException.class, () -> patient.setVaegt(-1));
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC1() {
        laegemiddel = controller.getAllLaegemidler().get(2);
        assertEquals("Fucidin", laegemiddel.getNavn());
        assertEquals(1, controller.antalOrdinationerPrVægtPrLægemiddel(55, 70, laegemiddel));
    }
    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC2() {
        laegemiddel = controller.getAllLaegemidler().get(2);
        assertEquals("Fucidin", laegemiddel.getNavn());
        assertThrows(IllegalArgumentException.class, () -> controller.antalOrdinationerPrVægtPrLægemiddel(70, 55, laegemiddel));
    }
    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC3() {
        laegemiddel = controller.getAllLaegemidler().get(2);
        assertEquals("Fucidin", laegemiddel.getNavn());
        assertEquals(0, controller.antalOrdinationerPrVægtPrLægemiddel(55, 55, laegemiddel));
    }

    @Test
    void opretDagligSkaevTC1() {
        startdato = LocalDate.of(2023, 2, 26);
        slutdato = LocalDate.of(2023, 2, 20);
        assertThrows(IllegalArgumentException.class, () -> controller.opretDagligSkaevOrdination(startdato, slutdato, patient, laegemiddel, klokkeslet, enheder));
    }
    @Test
    void opretDagligSkaevTC2() {
        startdato = LocalDate.of(2023, 2, 20);
        slutdato = LocalDate.of(2023, 2, 26);
        enheder = new double[]{1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> controller.opretDagligSkaevOrdination(startdato, slutdato, patient, laegemiddel, klokkeslet, enheder));
    }

    @Test
    void opretDagligSkaevTC3() {
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startdato, slutdato, patient, laegemiddel, klokkeslet, enheder);
        assertEquals(startdato, dagligSkaev.getStartDen());
        assertEquals(slutdato, dagligSkaev.getSlutDen());
        assertTrue(patient.getOrdinationer().contains(dagligSkaev));
        assertEquals(laegemiddel, dagligSkaev.getLaegemiddel());

        for (int i = 0; i < klokkeslet.length; i++) {
            Dosis d = dagligSkaev.getDoser().get(i);
            assertEquals(klokkeslet[i], d.getTid());
            assertEquals(enheder[i], d.getAntal());

        }
    }
}