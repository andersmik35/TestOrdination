package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private final ArrayList<LocalDate> datoer = new ArrayList<>();



    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }

    public PN(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public PN(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
       if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen())) {
           for (int i = 0; i < datoer.size(); i++) {
               LocalDate dato = datoer.get(i);
               if (dato.isBefore(givesDen)) {
                   datoer.add(i + 1, givesDen);
               }
           }
           datoer.add(givesDen);
           return true;
       }else {
           return false;
       }
    }

    public double doegnDosis() {
        LocalDate førsteGivning = datoer.get(0);
        LocalDate sidsteGivning = datoer.get(datoer.size() - 1);
        long antalDage = ChronoUnit.DAYS.between(førsteGivning, sidsteGivning);
        return (getAntalGangeGivet() * antalEnheder) / antalDage;
    }

    @Override
    public String getType() {
        return "PN";
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }


    public double samletDosis() {
        return antalEnheder * getAntalGangeGivet();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return datoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
