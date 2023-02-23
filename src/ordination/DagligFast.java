package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
    private Dosis[] doses = new Dosis[4];
    public DagligFast(LocalDate startDen, LocalDate slutDen, double morgenAntal, double middagAntal,
                      double aftenAntal, double natAntal) {
        super(startDen, slutDen);
        doses[0] = new Dosis(LocalTime.of(8, 0), morgenAntal);
        doses[1] = new Dosis(LocalTime.of(12, 0), middagAntal);
        doses[2] = new Dosis(LocalTime.of(18, 0), aftenAntal);
        doses[3] = new Dosis(LocalTime.of(0, 0), natAntal);
    }

    public Dosis[] getDoser() {
        return doses.clone();
    }

    @Override
    public double samletDosis() {
        int samletDosis = 0;
        for (Dosis d : doses) {
            samletDosis += d.getAntal();
        }
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / 4;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
}
