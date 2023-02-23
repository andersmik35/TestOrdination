package ordination;

import java.lang.reflect.Array;

public class DagligFast extends Ordination {
    private Dosis[] doses = new Dosis[4];

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
    // TODO
}
