package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;

    private ArrayList<Ordination> ordinationArrayList = new ArrayList<>();

    // TODO: Link til Ordination - done

    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    public ArrayList<Ordination> getOrdinationArrayList() {
        return new ArrayList<>(ordinationArrayList);
    }

    public void addOrdination(Ordination ordination){
        if(!ordinationArrayList.contains(ordination)){
            ordinationArrayList.add(ordination);
        }
    }


    public void removeOrdination(Ordination ordination){
        if (ordinationArrayList.contains(ordination)){
            ordinationArrayList.remove(ordination);
        }
    }

    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
