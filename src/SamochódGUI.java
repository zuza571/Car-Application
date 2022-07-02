import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SamochódGUI extends Thread {
    private JPanel panel1;
    private JButton włączS;
    private JButton wyłączS;
    private JTextField Smodel;
    private JTextField nrRej;
    private JTextField Swaga;
    private JTextField Spredkosc;
    private JComboBox wybórSamochodu;
    private JButton dodajNowyS;
    private JButton usuńS;
    private JTextField sbNazwa;
    private JTextField sbCena;
    private JTextField sbWaga;
    private JTextField sbBieg;
    private JButton biegPlus;
    private JButton biegMinus;
    private JTextField silNazwa;
    private JTextField silCena;
    private JTextField silWaga;
    private JTextField silObroty;
    private JButton gazPlus;
    private JButton gazMinus;
    private JTextField sprzNazwa;
    private JTextField sprzCena;
    private JTextField sprzWaga;
    private JTextField sprzStan;
    private JButton wcisnijSprz;
    private JButton zwolnijSprz;
    private JPanel Mapa;
    private JLabel samochódIkona;

    private Samochód samochód;

    public SamochódGUI(Samochód s) {
        this.samochód = s;
        // wywołanie wątku
        start();

        włączS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.włącz();
                refresh();
            }
        });

        wyłączS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.wyłącz();
                refresh();
            }
        });

        biegPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.zwiększBieg();
                refresh();
            }
        });

        biegMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.zmniejszBieg();
                refresh();
            }
        });

        gazPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.dodajGazu(100);
                refresh();
            }
        });

        gazMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.silnik.zmniejszObroty(100);
                refresh();
            }
        });

        wcisnijSprz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzęgło.wciśnij();
                refresh();
            }
        });

        zwolnijSprz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzęgło.zwolnij();
                refresh();
            }
        });

        // dodaj nowy samochód
        dodajNowyS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // połączenie z NowySamochódGUI
                JFrame f = new NowySamochódGUI(wybórSamochodu);
                f.pack();
                f.setVisible(true);
                refresh();
            }
        });

        // usuń samochód
        usuńS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybórSamochodu.removeItem(samochód);
                // zatrzymanie wątku
                samochód.interrupt();
                refresh();
            }
        });

        // wybór samochodu
        wybórSamochodu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód = (Samochód) wybórSamochodu.getSelectedItem();

            }
        });

        // poruszanie po mapie
        Mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pozycja cel = new Pozycja(0,0);
                cel.setX(e.getX());
                cel.setY(e.getY());
                samochód.jedźDo(cel.getX(), cel.getY());
                refresh();
            }
        });
    }

    //------------Run------------
    public void run(){
        while (true){
            refresh();
            try{
                Thread.sleep(200);
            } catch (InterruptedException e ){
                e.printStackTrace();
                return;
            }
        }
    }

    // pobieranie wartosci
    public void refresh() {
        if (wybórSamochodu.getItemCount() > 0){
            // samochód
            Smodel.setText(samochód.getModel());
            nrRej.setText(samochód.getNrRejest());
            Swaga.setText(String.valueOf(samochód.waga()));
            Spredkosc.setText(String.valueOf(samochód.getAktPrędkość()));
            // gdy stoi to prędkość 0
            if (samochód.getAktPozycja().getX() == samochód.cel.getX() && samochód.getAktPozycja().getY() == samochód.cel.getY())
                Spredkosc.setText("0.0");

            // skrzynia biegów
            sbNazwa.setText(samochód.skrzynia.getNazwa());
            sbCena.setText(String.valueOf(samochód.getSkrzynia().getCena()));
            sbWaga.setText(String.valueOf(samochód.getSkrzynia().getWaga()));
            sbBieg.setText(String.valueOf(samochód.getSkrzynia().getAktualnyBieg()));

            // silnik
            silNazwa.setText(samochód.silnik.getNazwa());
            silCena.setText(String.valueOf(samochód.getSilnik().getCena()));
            silWaga.setText(String.valueOf(samochód.getSilnik().getWaga()));
            silObroty.setText(String.valueOf(samochód.getSilnik().getObroty()));

            // sprzęgło
            sprzNazwa.setText(samochód.skrzynia.sprzęgło.getNazwa());
            sprzCena.setText(String.valueOf(samochód.skrzynia.sprzęgło.getCena()));
            sprzStan.setText(String.valueOf(samochód.skrzynia.sprzęgło.isStanSprzęgła()));
            sprzWaga.setText(String.valueOf(samochód.skrzynia.sprzęgło.getWaga()));

            samochódIkona.show();
            // do ustawienia pozycji na mapie
            samochódIkona.setLocation((int) samochód.getAktPozycja().getX(), (int) samochód.getAktPozycja().getY());
        }

        // znikanie pól gdy nie ma samochodów
        else {
            Smodel.setText("");
            nrRej.setText("");
            Swaga.setText("");
            Spredkosc.setText("");
            sbNazwa.setText("");
            sbCena.setText("");
            sbWaga.setText("");
            sbBieg.setText("");
            silNazwa.setText("");
            silCena.setText("");
            silWaga.setText("");
            silObroty.setText("");
            sprzNazwa.setText("");
            sprzCena.setText("");
            sprzStan.setText("");
            sprzWaga.setText("");
            // ikona
            samochódIkona.hide();
        }
    }

    public static void main(String[] args) {
        //startowa pozycja
        Pozycja lamborghiniPozycja = new Pozycja(0,0);

        //silnik
        Silnik benzyna = new Silnik ("benzyna", 8000, 0, false, 12, 15000);
        Silnik diesel = new Silnik ("diesel", 7000, 0, false, 10, 10000);

        //sprzęgło
        Sprzęgło sprzęgło = new Sprzęgło("S1", 2500, 20, false);

        //skrzynie biegów
        SkrzyniaBiegów manual6 = new SkrzyniaBiegów("manual - 6", 7000, 30, sprzęgło, 0, 6);
        SkrzyniaBiegów manual5 = new SkrzyniaBiegów("manual - 5", 12000, 30, sprzęgło, 0, 5);

        Samochód lamborghini = new Samochód(lamborghiniPozycja, diesel, manual6, false,"KRA78679", "s60","volvo", 280);

        // tworzenie nowego samochodu
        JFrame frame = new JFrame("SamochódGUI");
        frame.setContentPane(new SamochódGUI(lamborghini).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}