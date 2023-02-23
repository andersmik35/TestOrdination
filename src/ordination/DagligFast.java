package ordination;

import java.time.LocalDate;

public class DagligFast extends Ordination {
    private Dosis[] doses = new Dosis[4];
    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }
    // TODO

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
