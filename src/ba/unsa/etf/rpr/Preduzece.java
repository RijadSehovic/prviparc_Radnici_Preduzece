package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Function;

public class Preduzece {
    private int osnovica;
    private ArrayList<RadnoMjesto> radnaMjesta = new ArrayList<>();
    public Preduzece(int osnovica) throws NeispravnaOsnovica {
        if(osnovica <= 0 )
            throw new NeispravnaOsnovica("Neispravna osnovica "+ osnovica);
        else
        this.osnovica = osnovica;
    }

    public int dajOsnovicu() {
        return this.osnovica;
    }

    public void postaviOsnovicu(int osnovica) throws NeispravnaOsnovica {
        if(osnovica <= 0 )
            throw new NeispravnaOsnovica("Neispravna osnovica "+ osnovica);
        else
            this.osnovica = osnovica;
    }



    public void novoRadnoMjesto(RadnoMjesto rm) {
        radnaMjesta.add(rm);
    }

    public Map<RadnoMjesto, Integer> sistematizacija() {
        Map<RadnoMjesto, Integer> sis = new HashMap<>();
        Map<RadnoMjesto, ArrayList<Double>> pom = new HashMap<>();
        for (RadnoMjesto i : radnaMjesta) {
               if(!pom.containsKey(i)){
                   ArrayList<Double> pom2 = new ArrayList<>();
                   pom2.add(i.getKoeficijent());
                   pom.put(i,pom2);
               }else{
                   pom.get(i).add(i.getKoeficijent());
               }
        }

        for (RadnoMjesto x : pom.keySet()) {
            sis.put(x,pom.get(x).size());
        }
        return sis;
        }
/*
public Map<RadnoMjesto, Integer> sistematizacija() {                         //bosanska tastatura setxkbmap bs
        HashMap<RadnoMjesto, Integer> rezultat = new HashMap<>();
        for (RadnoMjesto rm : radnaMjesta) {
            Integer broj = rezultat.get(rm);
            if (broj == null)
                rezultat.put(rm, 1);
            else
                rezultat.put(rm, broj+1);
        }
        return rezultat;
    }
 */

    public void zaposli(Radnik r, String rm) {
        for(RadnoMjesto i : radnaMjesta){
            if(i.getNaziv() == rm){
                if(i.getRadnik() == null) {
                    i.setRadnik(r);
                    return;
                }
            }
        }
        throw new IllegalStateException("Nijedno radno mjesto tog tipa nije slobodno");
    }

    public Set<Radnik> radnici() {
        TreeSet<Radnik> radnici = new TreeSet<>();
        for (RadnoMjesto i : radnaMjesta) {
            if (i.getRadnik() == null) continue;
            else radnici.add(i.getRadnik());
        }
        return radnici;
    }

    public double iznosPlate() {
        double sveplate = 0;
        for(RadnoMjesto i : radnaMjesta){
            if(i.getRadnik() == null) continue;
            else sveplate += osnovica*i.getKoeficijent();
        }
        return sveplate;
    }

    public void obracunajPlatu() {
        for(RadnoMjesto i : radnaMjesta){
            if(i.getRadnik() == null) continue;
            else i.getRadnik().dodajPlatu(osnovica*i.getKoeficijent());
        }
    }

    public List<Radnik> filterRadnici(Function<Radnik,Boolean> f) {
        List<Radnik> pom = new ArrayList<>();
        for(RadnoMjesto i : radnaMjesta) {
            if (i.getRadnik() == null) continue;
            else{
                if (f.apply(i.getRadnik()) == true)
                    pom.add(i.getRadnik());
            }
        }
        return pom;
    }

    public List<Radnik> vecaProsjecnaPlata(double m) {
        return filterRadnici((Radnik x) -> {return x.prosjecnaPlata() > m;});
    }
}
