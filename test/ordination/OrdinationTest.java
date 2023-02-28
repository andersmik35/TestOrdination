package ordination;

import controller.Controller;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    private DagligFast dagligFast;

    private Controller controller;

    private Laegemiddel laegemiddel;

    @BeforeEach
    void setup() {
        dagligFast = new DagligFast(LocalDate.of(2023, 02, 20), LocalDate.of(2023, 02, 26), 1, 0, 1, 2);
        controller = Controller.getTestController();
        controller.createSomeObjects();
        laegemiddel = controller.getAllLaegemidler().get(2);
    }

    @Test
        // Tester hvor mange antal dage der fra startDato til slutDato
    void antalDageTC1() {
        int tc = dagligFast.antalDage();
        assertEquals(7, tc);

    }


    @Test
    void getLaegemiddelTC1() {
        DagligFast c1 = controller.opretDagligFastOrdination(LocalDate.of(2023, 02, 20), LocalDate.of(2023, 02, 26), controller.getAllPatienter().get(0), laegemiddel, 1, 0, 2, 1);
        assertEquals(laegemiddel, c1.getLaegemiddel());


    }
}