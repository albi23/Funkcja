import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa, w której zajmuję się obsługą okna do którego wprowadzamy nasze dane.
 */
class ParametrWindow extends JFrame implements ActionListener {



    // nowy branch

    private final JMenuItem MInfo, MExit;
    private final JTextField TParam_a;
    private final JTextField TParam_b;
    private final JTextField TParam_c;
    private final JButton B_OK;

    private boolean Guard = false; // strażnik do ochrony przed wprowadzaniem złych danych
    private double a, b, c;

    public ParametrWindow() {

         JLabel LFormule;
         JLabel LSentence;
         JLabel Lparam_a;
         JLabel Lparam_b;
         JLabel Lparam_c;
         JMenuBar MenuBar;
         JMenu Menu;
         String TypeOfFont = "Arial";

        setTitle("Properties of the function ");
        setLayout(null);
        setResizable(false);
        setBounds(500, 100, 550, 450);
        setForeground(new Color(255, 197, 61));

        MenuBar = new JMenuBar();
        setJMenuBar(MenuBar);

        Menu = new JMenu("Menu");
        MenuBar.add(Menu);

        MInfo = new JMenuItem("Informations");
        Menu.add(MInfo);
        MInfo.addActionListener(this);

        MExit = new JMenuItem("Exit");
        Menu.add(MExit);
        MExit.addActionListener(this);

        LFormule = new JLabel("f(x) = ax²+bx+c");
        LFormule.setBounds(80, 10, 500, 60);
        LFormule.setFont(new Font("Arial", Font.BOLD, 50));
        LFormule.setForeground(new Color(14, 17, 196));
        add(LFormule);

        LSentence = new JLabel("Write rates of the quadratic function down into appropriate places.");
        LSentence.setBounds(30, 100, 550, 80);
        LSentence.setFont(new Font(TypeOfFont, Font.BOLD, 16));
        add(LSentence);

        Lparam_a = new JLabel("A");
        Lparam_a.setBounds(30, 197, 30, 70);
        Lparam_a.setFont(new Font(TypeOfFont, Font.BOLD, 28));
        add(Lparam_a);

        TParam_a = new JTextField();
        TParam_a.setBounds(60, 210, 100, 40);
        TParam_a.setFont(new Font(TypeOfFont, Font.BOLD, 18));
        add(TParam_a);

        Lparam_b = new JLabel("B");
        Lparam_b.setBounds(180, 197, 30, 70);
        Lparam_b.setFont(new Font(TypeOfFont, Font.BOLD, 28));
        add(Lparam_b);

        TParam_b = new JTextField();
        TParam_b.setBounds(210, 210, 100, 40);
        TParam_b.setFont(new Font(TypeOfFont, Font.BOLD, 18));
        add(TParam_b);

        Lparam_c = new JLabel("C");
        Lparam_c.setBounds(330, 197, 30, 70);
        Lparam_c.setFont(new Font(TypeOfFont, Font.BOLD, 28));
        add(Lparam_c);

        TParam_c = new JTextField();
        TParam_c.setBounds(360, 210, 100, 40);
        TParam_c.setFont(new Font(TypeOfFont, Font.BOLD, 18));
        add(TParam_c);

        B_OK = new JButton("OK");
        B_OK.setBounds(20, 300, 510, 50);
        B_OK.setFont(new Font(TypeOfFont, Font.BOLD, 18));
        B_OK.addActionListener(this);
        add(B_OK);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source.equals(MExit)) {
            dispose();
        } else if (source.equals(MInfo)) {
            String info = "The program is used to check the course of the changeability of the function. \n";
            info += "User writing appropriate rates of the quadratic function down is approving his choice, with button \"OK \". \n";
            info += " Rate \"A\" cannot be equal of the zero.\n";

            JOptionPane.showMessageDialog(null, info + "Programe created by Albert Piekielny.", "Informations", JOptionPane.INFORMATION_MESSAGE);
        } else if (source.equals(B_OK))
        /*Jeśli ktoś wprowadza parametr "A" jako "0" automatycznie mamy komunikat o błędzie.
          Jeśli jest przeciwnie, to zajmujemy się przypadkami w których pozostałe prametry zostały podane niepoprawnie (jako ciągi znaków)
         */
        {
            if (TParam_a.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Parameter \"A\" cannot be equal 0!", "Warring!", JOptionPane.ERROR_MESSAGE);
                TParam_a.setText("");
            } else {
                    try {
                        a = Double.parseDouble(TParam_a.getText());
                        b = Double.parseDouble(TParam_b.getText());
                        c = Double.parseDouble(TParam_c.getText());
                        Guard = true;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "You wrote incorrect parameters down!", "Warring", JOptionPane.ERROR_MESSAGE);
                        TParam_a.setText("");
                        TParam_b.setText("");
                        TParam_c.setText("");
                    }
                if (Guard){
                    dispose();
                    ResultWindow  ResultWindow = new ResultWindow(a, b, c);
                }
            }
        }
    }
}
