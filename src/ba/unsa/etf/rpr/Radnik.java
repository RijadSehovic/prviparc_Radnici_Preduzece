package ba.unsa.etf.rpr;

public class Radnik implements Comparable {
    private String imePrezime;
    private String jmbg;
    private double[] plate = new double[1000];
    private int velicinaniza = 0;

    public Radnik(String imePrezime, String jmbg) {
        this.imePrezime = imePrezime;
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void dodajPlatu(double i) {
        if(velicinaniza < 1000){
            plate[velicinaniza] = i;
            velicinaniza++;
        }else
            throw new IllegalArgumentException("Ne možete registrovati više od 1000 plata za radnika "+this.imePrezime);
    }

    public double prosjecnaPlata() {
        double prosjek = 0;
        if (velicinaniza==0) return 0;
        for(int i = 0; i<velicinaniza; i++){
            prosjek += plate[i];
        }
        prosjek = prosjek/velicinaniza;
        return prosjek;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Radnik)
        if(this.prosjecnaPlata() > ((Radnik) o).prosjecnaPlata()){
            return -1;
        }else return 1;
        return 0;
    }
}
