import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankGUI extends JFrame implements ActionListener {

    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton balanceButton;

    public BankGUI() {
        account = new BankAccount();

        setTitle("Bank System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        balanceLabel = new JLabel("Balance: $0.00", JLabel.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));

        amountField = new JTextField();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        balanceButton = new JButton("Show Balance");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        balanceButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceButton);

        add(balanceLabel);
        add(amountField);
        add(buttonPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Show Balance")) {
            updateBalanceLabel();
            return;
        }

        String input = amountField.getText();

        try {
            double amount = Double.parseDouble(input);

            if (command.equals("Deposit")) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit successful.");
            } else if (command.equals("Withdraw")) {
                boolean success = account.withdraw(amount);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal failed. Check balance.");
                }
            }

            updateBalanceLabel();
            amountField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }
}