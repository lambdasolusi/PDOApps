package views;

import entities.Customer;
import entities.Location;

import javax.swing.*;
import java.awt.event.*;

public class RegisterCustomerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idInput;
    private JTextField nameInput;
    private JTextField addressInput;
    private JTextField longInput;
    private JTextField latInput;
    private JTextField phoneInput;
    private JTextField emailInput;
    private final MainFrame parent;

    public RegisterCustomerDialog(MainFrame parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setTitle("Customer Registration");
        setModal(true);
        setLocation(parent.getX()+300,parent.getY()+200);
        pack();
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener( e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        Customer customer = new Customer();
        customer.setId(idInput.getText());
        customer.setName(nameInput.getText());
        customer.setLocation(new Location(addressInput.getText(), Double.parseDouble(longInput.getText()),
                Double.parseDouble(latInput.getText())));
        customer.setPhone(phoneInput.getText());
        customer.setEmail(emailInput.getText());
        parent.getCmController().registerCustomer(customer);
        parent.sendMessage("Customer id "+idInput.getText()+" ditambahkan");
        parent.showCustomerTable();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
