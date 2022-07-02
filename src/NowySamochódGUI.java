import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySamochódGUI extends JFrame {
    private JPanel nowySPanel;
    private JTextField nowyNrRej;
    private JTextField nowyModel;
    private JTextField nowyMarka;
    private JTextField nowyPrędMax;
    private JButton nowyDodaj;
    private JButton nowyAnuluj;
    private JRadioButton manual5Biegów;
    private JRadioButton manual6Biegów;
    private JRadioButton benzyna;
    private JRadioButton diesel;

    public NowySamochódGUI (JComboBox wybranySamochód){
        setContentPane(nowySPanel);
        // nie poszerza okna wyboru
        setResizable(false);

        nowyDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pozycja
                Pozycja pozycja = new Pozycja(0,0);

                //silnik
                Silnik silnik = null;
                Silnik Sbenzyna = new Silnik ("benzyna", 8000, 0, false, 12, 15000);
                Silnik Sdiesel = new Silnik ("diesel", 7000, 0, false, 10, 10000);

                if (benzyna.isSelected())
                    silnik = Sbenzyna;

                else if (diesel.isSelected())
                    silnik = Sdiesel;

                //sprzęgło
                Sprzęgło sprzęgło = new Sprzęgło("sprzęgło normalne", 2500, 20, false);

                //skrzynia biegów
                SkrzyniaBiegów skrzynia = null;
                SkrzyniaBiegów manual6 = new SkrzyniaBiegów("manual - 6", 7000, 30, sprzęgło, 0, 6);
                SkrzyniaBiegów manual5 = new SkrzyniaBiegów("manual - 5", 12000, 30, sprzęgło, 0, 5);

                if (manual6Biegów.isSelected())
                    skrzynia = manual6;

                else if (manual5Biegów.isSelected())
                    skrzynia = manual5;

                //samochód
                Samochód samochód = new Samochód(pozycja, silnik, skrzynia, false, nowyNrRej.getText(), nowyModel.getText(),
                        nowyMarka.getText(), Integer.parseInt(nowyPrędMax.getText()));
                wybranySamochód.addItem(samochód);
                dispose();
            }
        });

        nowyAnuluj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // chowanie okna
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SamochódGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}