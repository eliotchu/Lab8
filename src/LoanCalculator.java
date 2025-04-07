import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanCalculator extends JFrame {
    private double annualInterestRate;
    private int years;
    private double loanAmount;

    public LoanCalculator(){
        super("Loan Calculator");
        setSize(800, 800);
        setLayout(new FlowLayout());
        add(new JLabel("Enter loan amount, interest rate, and year"));
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JTextField jTextField1 = new JTextField(10);
        JTextField jTextField2 = new JTextField(10);
        JTextField jTextField3 = new JTextField(10);
        JTextField jTextField4 = new JTextField(10);
        JTextField jTextField5 = new JTextField(10);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jPanel1.add(new JLabel("Annual Interest Rate"), BorderLayout.WEST);
        jPanel1.add(jTextField1, BorderLayout.EAST);
        jPanel2.add(new JLabel("Number of Years"), BorderLayout.WEST);
        jPanel2.add(jTextField2, BorderLayout.EAST);
        jPanel3.add(new JLabel("Loan Amount"), BorderLayout.WEST);
        jPanel3.add(jTextField3, BorderLayout.EAST);
        jPanel4.add(new JLabel("Monthly Payment"), BorderLayout.WEST);
        jPanel4.add(jTextField4, BorderLayout.EAST);
        jPanel5.add(new JLabel("Total Payment"), BorderLayout.WEST);
        jPanel5.add(jTextField5, BorderLayout.EAST);
        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
        add(jPanel4);
        add(jPanel5);
        JButton jButton = new JButton("Compute Payment");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    annualInterestRate = Double.parseDouble(jTextField1.getText());
                    years = Integer.parseInt(jTextField2.getText());
                    loanAmount = Double.parseDouble(jTextField3.getText());
                    double total = loanAmount * Math.pow((1 + annualInterestRate / 1200.0), years * 12);
                    jTextField4.setText("" + total / 48.0);
                    jTextField5.setText("" + total);
                }catch(NumberFormatException numberFormatException){
                    JFrame jFrame = new JFrame("Error");
                    jFrame.setSize(200, 200);
                    JTextField error = new JTextField("Please enter a number. Error thrown: " + numberFormatException);
                    error.setEditable(false);
                    jFrame.add(error);
                    jFrame.setVisible(true);
                    System.out.println(numberFormatException + " thrown");
                }

            }
        };
        jButton.addActionListener(actionListener);
        add(jButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        LoanCalculator a = new LoanCalculator();
        a.setVisible(true);
    }
}
