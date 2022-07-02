public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;
    private boolean stan;

    public Silnik(String nazwa, int maxObroty, int obroty, boolean stan, int waga, int cena) {
        this.maxObroty = maxObroty;
        this.obroty = obroty;
        this.stan = stan;
        this.setWaga(waga);
        this.setCena(cena);
        this.setNazwa(nazwa);
    }

    public int getObroty() {
        return obroty;
    }

    public void setObroty(int obroty) {
        this.obroty = obroty;
    }

    public void uruchom(){
        obroty = 1000;
        System.out.println("aktualne obroty: " + obroty);
    }
    public void zatrzymaj(){
        obroty = 0;
        System.out.println("aktualne obroty: " + obroty);
    }

    public void zwiększObroty(int x){
        if (obroty + x < maxObroty)
            obroty = obroty + x;
        else
            obroty = maxObroty;
        System.out.println("aktualne obroty: " + obroty);
    }
    public void zmniejszObroty(int x){
        if (obroty - x > 0)
            obroty = obroty - x;
        else
            obroty = 0;
        System.out.println("aktualne obroty: " + obroty);
    }
}
