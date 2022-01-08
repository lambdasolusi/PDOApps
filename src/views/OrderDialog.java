package views;

import entities.Customer;
import entities.Product;
import models.Order;
import models.SaleLineItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Random;

public class OrderDialog extends JDialog {
    private JPanel contentPane;
    private JButton checkOutButton;
    private JButton buttonCancel;
    private JButton addItem;
    private JTable tableSaleItem;
    private JButton customerButton;
    private JLabel customerLabel;
    private JLabel totalLabel;
    private JButton removeItem;
    private JTextField searchCustomer;
    private final MainFrame parent;
    private final Order order = new Order();

    public OrderDialog(MainFrame parent) {
        this.parent = parent;
        setTitle("Order Form");
        order.setInvoice(getInvoice());
        setContentPane(contentPane);
        setModal(true);
        setSize(500,450);
        setLocation(parent.getX()+200, parent.getY()+100);
        getRootPane().setDefaultButton(checkOutButton);
        buttonCancel.addActionListener(e -> onCancel());
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
        //add Sale Item
        addItem.addActionListener(e -> {
            SaleLineItem item = new SaleLineItem();
            Product product = new Product();
            String id = JOptionPane.showInputDialog(this,"Masukkan id?");
            product = parent.getPmController().findProductById(id);
            if(product != null){
                try {
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog(this, "Jumlah ?"));
                    if (jumlah > 0) {
                        item.setProduct(product);
                        item.setJumlah(jumlah);
                        order.addItem(item);
                        //totalLabel.setText("Total : "+order.getTotal());
                        showSaleItemTable();
                        parent.sendMessage("Item " + id + " ditambahkan");
                    }
                }catch (NumberFormatException ex){
                    parent.sendMessage("Tipe data Input jumlah tidak benar");
                }
            }
        });
        //checkOUt
        checkOutButton.addActionListener(e->{
            order.checkOut();
            parent.getOmController().getOrderManager().addOrder(order);
            parent.showOrderTable();
            dispose();
        });
        //remove item
        removeItem.addActionListener(e ->{

            int row = tableSaleItem.getSelectedRow();
            String idProduct = (String) tableSaleItem.getValueAt(row,0);
            if(JOptionPane.showConfirmDialog(this,"Yakin"+idProduct+" akan dihapus?")==0){
            //hindari remove item pada proses iterasi karena akan membangkitakan concurremtmodificationException
                SaleLineItem itemtobeRemoved = null;
                for(SaleLineItem item : order.getItems()) {
                if (item.getProduct().getId().equals(idProduct)) {
                    //order.getItems().remove(item);
                    itemtobeRemoved = item;
                    //showSaleItemTable();
                }
            }
                order.getItems().remove(itemtobeRemoved);
                showSaleItemTable();
            }
        });
        searchCustomer.addActionListener(e -> {
            String id = searchCustomer.getText();
            Customer customer = parent.getCmController().findCustomerById(id);
            if(customer != null){
                order.setCustomer(customer);
                searchCustomer.setText(id);
                searchCustomer.setEnabled(false);
            }
        });
        customerButton.addActionListener(e -> {
            //add New Customer
            RegisterCustomerDialog dialog = new RegisterCustomerDialog(parent);
            dialog.setModal(true);
            dialog.setVisible(true);
        });
    }
    private void onCancel() {
        dispose();
    }
    private String getInvoice(){
        Random rnd = new Random();
        Integer invoice = rnd.nextInt(1000);
        return "PDO-"+invoice;
    }
    private void showSaleItemTable(){

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
        tableSaleItem.getColumnModel().getColumn(1).setPreferredWidth(150);
        totalLabel.setText("Total : "+order.getTotal());
    }
}
