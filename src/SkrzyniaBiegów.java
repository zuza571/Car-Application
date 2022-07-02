public class SkrzyniaBiegów extends Komponent {

    Sprzęgło sprzęgło;

    private int aktualnyBieg;
    private int ilośćBiegów;
    private double aktualnePrzełożenie;

    public SkrzyniaBiegów(String nazwa, int cena, int waga, Sprzęgło sprzęgło, int aktualnyBieg, int ilośćBiegów){
        this.setNazwa(nazwa);
        this.setWaga(waga);
        this.setCena(cena);
        this.sprzęgło = sprzęgło;
        this.aktualnyBieg = aktualnyBieg;
        this.ilośćBiegów = ilośćBiegów;
    }

    public int getAktualnyBieg() {
        return  aktualnyBieg;
    }
    public double getAktualnePrzełożenie() {
        return aktualnePrzełożenie;
    }
    public Sprzęgło getSprzęgło() {
        return sprzęgło;
    }

    public int zwiększBieg(){
        if (aktualnyBieg < ilośćBiegów) {
            aktualnyBieg++;
            zmianaPrzełożenia();
        }
        return aktualnyBieg;
    }
    public int zmniejszBieg(){
        if (aktualnyBieg > 0) {
            aktualnyBieg --;
            zmianaPrzełożenia();
        }
        return aktualnyBieg;
    }

    public double zmianaPrzełożenia(){
        if (aktualnyBieg == 0) {
            aktualnePrzełożenie = 0;
        }
        else if(aktualnyBieg == 1){
            aktualnePrzełożenie = 1.1;
        }
        else if(aktualnyBieg == 2){
            aktualnePrzełożenie = 1.3;
        }
        else if(aktualnyBieg == 3){
            aktualnePrzełożenie = 1.5;
        }
        else if(aktualnyBieg == 4){
            aktualnePrzełożenie = 1.7;
        }
        else if(aktualnyBieg == 5){
            aktualnePrzełożenie = 1.9;
        }
        else if(aktualnyBieg == 6){
            aktualnePrzełożenie = 2.1;
        }

        return aktualnePrzełożenie;
    }
}
