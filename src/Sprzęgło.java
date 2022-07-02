public class Sprzęgło extends Komponent {
    private boolean stanSprzęgła;

    public Sprzęgło(String nazwa, int cena, int waga, boolean stanSprzęgła) {
        this.setNazwa(nazwa);
        this.setCena(cena);
        this.setWaga(waga);
        this.stanSprzęgła = stanSprzęgła;
    }

    public boolean isStanSprzęgła() {
        return stanSprzęgła;
    }

    public void wciśnij(){
        stanSprzęgła = true;
    }
    public void zwolnij(){
        stanSprzęgła = false;
    }
}
