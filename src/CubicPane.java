import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CubicPane extends JFrame {
    private JPanel panel1;
    private JTextField textField_B;
    private JTextField textField_C;
    private JTextField textField_D;
    private JTextField RESULT_X1;
    private JTextField RESULT_X2;
    private JTextField RESULT_X3;
    private JButton calculateButton;
    private JTextField textField_A;
    private JLabel X3_Label;
    private JLabel ResultTextFiels;
    private JLabel Label_A;
    private JLabel Label_B;
    private JLabel Label_C;
    private JLabel Label_D;
    private JLabel X1_Label;
    private JLabel X2_Label;


    public Cubic cubic;

    public CubicPane(String title){
        super(title);

        setResizable(true);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        cubic = new Cubic();
        RESULT_X1.setToolTipText("Wynik X1 JEJJEEJEJ");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(textField_A.getText());
                double b = Double.parseDouble(textField_B.getText());
                double c = Double.parseDouble(textField_C.getText());
                double d = Double.parseDouble(textField_D.getText());
                cubic.solve(a,b,c,d);
                RESULT_X1.setText("");
                if(cubic.getX1()!=null) {
                    RESULT_X1.setText(cubic.getX1().toString());
                }
                RESULT_X2.setText("");
                if(cubic.getX2()!=null) {
                    RESULT_X2.setText(cubic.getX2().toString());
                }
                RESULT_X3.setText("");
                if(cubic.getX3()!=null) {
                    RESULT_X3.setText(cubic.getX3().toString());
                }
            }
        });
    }

    public static void main(String[] args){
        CubicPane cubicPane = new CubicPane("A");
        //cubic = new Cubic();
    }

}

