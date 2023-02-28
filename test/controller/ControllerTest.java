package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private Patient patient;
    private Laegemiddel laegemiddel;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        controller.createSomeObjects();
        patient = new Patient("00000000", "Anders And", 10);
        laegemiddel = controller.getAllLaegemidler().get(1); // paracetemol
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
}