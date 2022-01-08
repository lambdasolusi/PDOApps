package views;

import entities.Customer;
import models.Order;
import models.SaleLineItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class SendOrderDialog extends JDialog {
    private JPanel contentPane;
    private JButton sendButton;
    private JButton buttonCancel;
    private JTabbedPane tabbedPane1;
    private JLabel invoiceNumberLabel;
    private JLabel customerLabel;
    private JTextField customerField;
    private JTextField longField;
    private JTextField latField;
    private JLabel addressLabel;
    private JTable tableSaleItem;
    private JLabel totalLabel;
    private JLabel invoiceLabel;
    private final MainFrame parent;
    private final Order order;

    public SendOrderDialog(MainFrame parent, Order order) {
        System.out.println(order);
        setTitle("Send order to Outlet");
        this.parent = parent;
        this.order = order;
        setContentPane(contentPane);
        setSize(600,400);
        setLocation(parent.getX()+130,parent.getY()+110);
        setModal(true);
        getRootPane().setDefaultButton(sendButton);
        showDetailOrder();
        sendButton.addActionListener(e -> {});

        buttonCancel.addActionListener(e -> dispose());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    private void showDetailOrder(){
        invoiceNumberLabel.setText(order.getInvoice());
        Customer customer = order.getCustomer();
        addressLabel.setText(customer.getLocation().getAddress());
        customerField.setText(customer.getName());
        longField.setText(""+customer.getLocation().getLongitude());
        latField.setText(""+customer.getLocation().getLatitude());
        Object [] colom = {"ID","Name","Price","Quantity","SubTotal"};
        DefaultTableModel model = new DefaultTableModel(null,colom);
        for(SaleLineItem item : order.getItems()){
            Object [] data = {
                    item.getProduct().getId(),item.getProduct().getName(),item.getProduct().getPrice(),
                    item.getJumlah(),item.getSubTotal()
            };
            model.addRow(data);
        }
        tableSaleItem.setModel(model);
        tableSaleItem.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableSaleItem.getColumnModel().getColumn(1).setPreferredWidth(160);
        totalLabel.setText(""+order.getTotal());
    }
    private void onCancel() {
        dispose();
    }
}
