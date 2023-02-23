package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private final ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public Dosis opretDosis(LocalTime tid , double antal){
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
        return dosis;
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        double samletDagligDosis = 0;
        for (Dosis d : doser) {
            samletDagligDosis += d.getAntal();
        }
        return samletDagligDosis * antalDage();
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return "DagligSkæv";
    }
}
