package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {
    private double antalEnheder;
    private final ArrayList<LocalDate> datoer = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean givet = true;
        if (!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())) {
            insertDato(givesDen);
        } else {
            givet = false;
        }
        return givet;
    }

    private void insertDato(LocalDate givesDen) {
        boolean found = false;
        int i = 0;
        while (!found && i < datoer.size()) {
            LocalDate dato = datoer.get(i);
            if (dato.isAfter(givesDen)) {
                found = true;
            } else {
                i++;
            }
        }
        datoer.add(i, givesDen);
    }

    public double doegnDosis() {
        double doegnDosis = 0;
        if (datoer.size() == 1){
            doegnDosis = antalEnheder;
        } else if (datoer.size() > 1) {
            LocalDate førsteGivning = datoer.get(0);
            LocalDate sidsteGivning = datoer.get(datoer.size() - 1);
            long antalDage = ChronoUnit.DAYS.between(førsteGivning, sidsteGivning) + 1;
            doegnDosis = (getAntalGangeGivet() * antalEnheder) / antalDage;
        }
        return doegnDosis;
    }

    @Override
    public String getType() {
        return "PN";
    }

    public double samletDosis() {
        return antalEnheder * getAntalGangeGivet();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return datoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}