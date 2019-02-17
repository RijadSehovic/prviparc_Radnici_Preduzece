package ba.unsa.etf.rpr;

public class RadnoMjesto implements Comparable<RadnoMjesto>{
    private String naziv;
    private double koeficijent;
    private Radnik radnik = null;

    public RadnoMjesto() {
    }

    public RadnoMjesto(String naziv, double koeficijent, Radnik radnik) {
        this.naziv = naziv;
        this.koeficijent = koeficijent;
        this.radnik = radnik;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getKoeficijent() {
        return koeficijent;
    }

    public void setKoeficijent(double koeficijent) {
        this.koeficijent = koeficijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    @Override
    public int compareTo(RadnoMjesto o) {

            if((this.getNaziv().compareTo(o.getNaziv()) == 0 && (this.koeficijent == o.getKoeficijent()))) return 0;
            else return 1;

    }
    @Override
    public boolean equals(Object o){

           if(this.compareTo((RadnoMjesto) o) == 0) return true;
           else return false;
    }
    @Override
    public int hashCode(){
        return 0;
    }
}
