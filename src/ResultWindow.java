import javax.swing.*;
import java.awt.*;

class ResultWindow extends JFrame {

    /**
     * Klasa odpowiedzialna za poprawne zbadanie przebiegu funkcji w zależności od wprowadzonych prarametrów a,b,c.
     */
    private final double a;
    private final double b;
    private final double c;
    private String Result;

    private final TextArea TResutl;

    ResultWindow(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        setTitle("Properties of the function ");
        setLayout(null);
        setResizable(false);
        setBounds(500, 100, 800, 600);
        setForeground(new Color(255, 197, 61));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TResutl = new TextArea();
        TResutl.setBackground(Color.BLUE);
        TResutl.setFont(new Font("Arial", Font.BOLD, 20));
        TResutl.setBounds(0, 0, 790, 570);
        add(TResutl);

        ResultMessage();

    }

    private void ResultMessage() { // Metoda, która sekwencyjnie buduje string wyświetlany w okienu końcowym
        Result = "I. Dziedziną funkcji jest zbiór liczb rzeczywistych.\n";
        Result = Result + "II. " + getValueDelta() + "\n";
        Result = Result + "III. " + getRangeofRrowth() + "\n";
        Result = Result + "IV. ";
        sgnDelta();

        TResutl.setText(Result); //Przekazanie stringu do wyświetlanego okiena
    }


    private void sgnDelta() { // badamy czy delta jest dodatnia/ujemna/ równa zeru
        if (getDelta() > 0) {
            Result = Result + " Trójmian kwadratowy posiada 2 pierwisatki: " + getX1() + " oraz " + getX2() + "\n";
            if (isPossitive()) {
                Result = Result + "V. Minimum lokalnym jest " + getP()+ "\n";
            } else
                Result = Result + "V. Maksimum lokalnym jest " + -getP() + "\n";

        } else if (getDelta() < 0) {

            Result = Result + " Trójmian kwadratowy nie ma pierwiastków.\n";
        } else if (getDelta() == 0) {

            Result = Result + "Trójmian kwadratowy ma jeden pierwiastek podwójny: " + getP() + "\n";
            Result = Result + "V. Brak ekstremów lokalnych.\n";
        }
    }


    private String getValueDelta() { // badamy zbiór wartości
        if (isPossitive()) {
            return "Zbiorem wartości trójmianu kwadratowego jest przedział <" +getQ() + ",+oo)";
        } else
            return "Zbiorem wartości trójmianu kwadratowego jest przedział (-oo," + getQ() + ">";
    }


    private String getRangeofRrowth() { // badamy kiedy funkcja jest malejca a kiedy rosnąca
        if (isPossitive()) {
            return "Funkcja jest malejąca w przedziale" + "(-oo,"+ getP() + ">, oraz rosnąca w przedziale <" + getP() + ",+oo)";
        } else
            return "Funkcja jest rosnąca w przedziale" + "(-oo," + getP() + ">, oraz malejąca w przedziale <" + getP() + ",+oo)";
    }

    private boolean isPossitive() { // metoda badająca czy współczynnik a jest dodatni czy nie
        return a > 0;
    }  // Metoda sprawdzająca czy wykres funkcji jest wklęsły czy wypukły

    private double getDelta() { //Obliczamy deltę
        return b * b - (4 * a * c);
    }

    private double getP() {// Meotda zwracjącą współrzędną x wierzchołka
        return  (Math.round((-b / (2 * a))*10000.0))/10000.0;
    }

    private double getQ() { // Meotda zwracjącą współrzędną x wierzchołka
        return (Math.round((-getDelta() / (4 * a))*10000.0))/10000.0;
    }

    private double getX1() { //Meotda getX1() i getX2() zwracjąc miejsca zerowe funkcji
        return Math.round(((-b - Math.sqrt(getDelta())) / 2 * a)*10000.0)/10000.0;
    }

    private double getX2() {
        return Math.round(((-b + Math.sqrt(getDelta())) / 2 * a)*10000.0)/10000.0;
    }

}
