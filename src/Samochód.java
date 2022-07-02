public class Samochód extends Thread {
    Pozycja aktPozycja = new Pozycja(0,0);
    Silnik silnik;
    SkrzyniaBiegów skrzynia;
    Pozycja cel = new Pozycja(0,0);

    private  boolean stanWłączenia;
    private String nrRejest;
    private String model;
    private String marka;
    private double prędkośćMax;
    private int waga;

    double obwKoła = 0.02;
    // do zmieniania predkosci
    double dt = 0.02;

    public Samochód (Pozycja aktPozycja, Silnik silnik, SkrzyniaBiegów skrzynia, boolean stanWłączenia, String nrRejest, String model, String marka, int prędkośćMax){
        this.aktPozycja = aktPozycja;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.stanWłączenia = stanWłączenia;
        this.nrRejest = nrRejest;
        this.model = model;
        this.marka = marka;
        this.prędkośćMax = prędkośćMax;
        // uruchomienie wątku
        this.start();
    }

    public Pozycja getAktPozycja() {
        return aktPozycja;
    }
    public String getModel() {
        return model;
    }
    public String getMarka() {
        return marka;
    }
    public String getNrRejest() {
        return nrRejest;
    }
    public SkrzyniaBiegów getSkrzynia() {
        return skrzynia;
    }
    public Silnik getSilnik() {
        return silnik;
    }
    public double getAktPrędkość(){
        double v = this.silnik.getObroty() * this.skrzynia.getAktualnePrzełożenie() * obwKoła;
        if (this.getAktPozycja() != cel) {
            // max predkosc osiagnieta
            if (v >= this.prędkośćMax)
               v = prędkośćMax;
            return v;
        }
        // samochód się nie porusza
        else return 0;
    }

    // waga samochodu
    public int waga(){
        waga = this.getSilnik().getWaga() + this.getSkrzynia().getWaga() + this.getSkrzynia().getSprzęgło().getWaga() + 10000;
        return waga;
    }

    public void włącz(){
        if (! stanWłączenia){
            stanWłączenia = true;
            silnik.uruchom();
        }
        else
            System.out.println("samochód jest już włączony");
    }
    public void wyłącz(){
        if (stanWłączenia && silnik.getObroty() <= 1000){
            stanWłączenia = false;
            silnik.zatrzymaj();
        }
        else
            System.out.println("Za wysokie obroty");
    }

    public void zwiększBieg(){
        // trzeba włączyć sprzęgło
        if(this.skrzynia.sprzęgło.isStanSprzęgła()){
            // obroty powyzej 2000
            if (this.silnik.getObroty() >= 2000){
                this.skrzynia.zwiększBieg();
                this.silnik.zmniejszObroty(1000);
            }

            else
                System.out.println("za niskie oborty");
        }

        else
            System.out.println("wciśnij sprzęgło");
    }
    public void zmniejszBieg(){
        if(this.skrzynia.sprzęgło.isStanSprzęgła()){
            this.skrzynia.zmniejszBieg();
            this.silnik.zwiększObroty(1000);
        }

        else
            System.out.println("wciśnij sprzęgło");
    }

    public void dodajGazu(int x){
        if (getAktPrędkość() < this.prędkośćMax){
            if (! this.skrzynia.sprzęgło.isStanSprzęgła()){
                this.silnik.zwiększObroty(x);
            }

            else System.out.println("zwolnij sprzęgło");
        }

        else System.out.println("jedziesz z prędkością maksymalną");
    }

    // ustawienie celu
    public void jedźDo(double x, double y){
        cel.setX(x);
        cel.setY(y);
    }

    //----------RUN--------------
    public void run() {
        while (true) {
            this.aktPozycja.przemieść(this.getAktPrędkość(), dt, cel, getAktPozycja());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // wyswietlanie dobrze obiektu w okienku comboBoxa
    @Override
    public String toString(){
        String wyświetl = marka + "," + model + "," + nrRejest;
        return wyświetl;
    }
/*
    public static void main(String[] args){
        //startowe pozycje
        Pozycja pozycja = new Pozycja(1,1);

        //silniki
        Silnik diesel = new Silnik ("diesel", 7000, 1000, true, 10, 10000);

        //sprzęgło
        Sprzęgło sprzęgło = new Sprzęgło("S1", 2500, 20, false);

        //skrzynie biegów
        SkrzyniaBiegów manual6 = new SkrzyniaBiegów("manual - 6", 7000, 30, sprzęgło, 1, 6);

        Samochód lamborghini = new Samochód(pozycja, diesel, manual6, true,"KRA78679", "Lamborghini", "marka", 280);

        lamborghini.jedźDo(10,10);
    }

 */
}
