package views;

import apps.Apps;
import controllers.CMController;
import controllers.OMController;
import controllers.OutletController;
import controllers.PMController;
import entities.Customer;
import entities.Outlet;
import entities.Product;
import entities.Shop;
import models.Order;
import utilities.FileServices;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JLabel messageLabel;
    private JTabbedPane tabbedPane1;
    private JButton registerProductButton;
    private JButton findProductButton;
    private JButton printAllProductButton;
    private JTextArea displayDataProduct;
    private JLabel clockLabel;
    private JTable tableOrders;
    private JButton makeOrderButton;
    private JButton sendOrderButton;
    private JTable table1;
    private JTable tableOutlets;
    private JButton registerOutlet;
    private JButton doActionButton;
    private JComboBox cbAction;
    private JButton addCustomer;
    private JList customerList;
    private JTextField txtAddress;
    private JTextField txtLong;
    private JTextField txtLat;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JPanel inventoryPanel;
    private JButton opnameButton;
    private final PMController pmController;
    private final OMController omController;
    private final OutletController outManController;
    private final CMController cmController;
    private Shop shop;

    public MainFrame(Shop shop){
        super();
        this.shop = shop;
        setTitle("PDO Study Case - A Practical Programming Learning In Java");
        this.pmController = new PMController(shop.getProductManager());
        this.omController = new OMController(shop.getOrderManager());
        this.outManController = new OutletController(shop.getOutletManager());
        this.cmController = new CMController(shop.getCustomerManager());
        setContentPane(mainPanel);
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        // add listener
        printAllProductButton.addActionListener(e -> {
            for(Product p : pmController.getProductManager().getKatalog())
                displayDataProduct.append(""+p+"\n");
        });
        findProductButton.addActionListener(e-> {
            String id = JOptionPane.showInputDialog(this,"Masukkan id?");
            Product p = pmController.findProductById(id);
            if(p != null){
                displayDataProduct.append("Result : "+p);
                sendMessage("Product id "+id+" ditemukan");
                showProductDetail(p);
            }
        });
        registerProductButton.addActionListener(e -> {
            RegisterProductDialog dialog = new RegisterProductDialog(this);
            dialog.setLocationRelativeTo(this);
            dialog.pack();
            dialog.setVisible(true);
        });
        doActionButton.addActionListener(e -> {
            switch (cbAction.getSelectedIndex()){
                case 0: {emptyDataShop();break;}
                case 1: {saveDataShop(Apps.getDefaultFilename());break;}
                case 2: {backupDataShop();break;}
                case 3: {restoreDataShop();break;}
                case 4: {importDataProduct();break;}
                default: break;
            }
        });
        makeOrderButton.addActionListener(e->{
            OrderDialog dialog = new OrderDialog(this);
            dialog.setVisible(true);
        });
        //send to Outlet
        sendOrderButton.addActionListener(e -> {
            try{
            int row = tableOrders.getSelectedRow();
            String invoice = (String) tableOrders.getValueAt(row,2);
            Order order = omController.findOrderByInvoice(invoice);
            SendOrderDialog dialog = new SendOrderDialog(this,order);
            dialog.setVisible(true);}
            catch (ArrayIndexOutOfBoundsException err){
                //err.printStackTrace();
                JOptionPane.showMessageDialog(this,"Silahkan pilih Order");
            }
        });
        //add Customer
        addCustomer.addActionListener(e -> {
            RegisterCustomerDialog dialog = new RegisterCustomerDialog(this);
            dialog.setVisible(true);
        });
        customerList.addListSelectionListener(e -> {
            Customer customer = (Customer) customerList.getModel().getElementAt(customerList.getSelectedIndex());
            displayCustomer(customer);
        });
    }

    private void showProductDetail(Product p) {
        DetailProduct detailProduct = new DetailProduct(this,p);
        detailProduct.setVisible(true);
    }

    // End of Constructor
    private void displayCustomer(Customer customer){
        txtAddress.setText(customer.getLocation().getAddress());
        txtLong.setText(String.valueOf(customer.getLocation().getLongitude()));
        txtLat.setText(String.valueOf(customer.getLocation().getLatitude()));
        txtPhone.setText(customer.getPhone());
        txtEmail.setText(customer.getEmail());
    }
    public PMController getPmController() {
        return pmController;
    }
    public OMController getOmController() {
        return omController;
    }
    public OutletController getOutManController() {
        return outManController;
    }
    public CMController getCmController() {
        return cmController;
    }
    public void initial() {
        Outlet centerOutlet = new Outlet();
        centerOutlet.setId("1");
        centerOutlet.setName("PDO OUTLET CENTRAL");
        getOutManController().getOutletManager().registerOutlet(centerOutlet);
        sendMessage("PDO Apps Ready for Action");
        showOrderTable();
        showCustomerTable();
        printAllCollection(shop);
        //comboAction
        cbAction.setSelectedIndex(1);
        //run clock
        while (true) {
            try {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
                String formatDateTime = now.format(formatter);
                clockLabel.setText(formatDateTime);
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void printAllCollection(Shop shop){
        System.out.println(shop.getProductManager().getKatalog().size());
        System.out.println(shop.getOrderManager().getOrderList().size());
        System.out.println(shop.getOrderManager().getOrderList());
        System.out.println(shop.getCustomerManager().getCustomerList().size());
        System.out.println(shop.getCustomerManager().getCustomerList());
        System.out.println(shop.getOutletManager().getOutletList().size());
    }
    public void sendMessage(String msg){
        messageLabel.setText(msg);
    }
    //Empty DAta Shop
    private void emptyDataShop(){
        if(JOptionPane.showConfirmDialog(this,"Yakin data dikosongkan?")==0) {
        this.shop = new Shop();
        pmController.setProductManager(shop.getProductManager());
        omController.setOrderManager(shop.getOrderManager());
        sendMessage("Data shop telah dikosongkan");
        displayDataProduct.setText("");
        showOrderTable();
        }
    }
    // SAVE
    private void saveDataShop(String filename){
        try {
            FileServices.saveObjectToFile(shop,filename);
            sendMessage("Data Shop berhasil disimpan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Backup
    private void backupDataShop(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDO file system","pdo");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(this);
        File file = chooser.getSelectedFile();
        String filename = file.getAbsolutePath();
        saveDataShop(filename);
        sendMessage("Backup File "+filename);
    }
    //Restore
    private void restoreDataShop() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDO file system","pdo");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        String filename = file.getAbsolutePath();
        Shop restoreDataShop = (Shop) FileServices.readObjectFromFile(filename);
        this.shop = restoreDataShop;
        pmController.setProductManager(shop.getProductManager());
        omController.setOrderManager(shop.getOrderManager());
        sendMessage("Restore File "+filename);
    }
    //Import data product
    private void importDataProduct(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files","txt");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        String filename = file.getAbsolutePath();
        List<String> parser = FileServices.readTxtFile(filename);
        for(String data : parser){
            Product product = new Product();
            String[] p = data.split("/");
            product.setId(p[0]);
            product.setName(p[1]);
            product.setCategory(p[2]);
            product.setPrice(Double.parseDouble(p[3]));
            product.setImage(null);
            pmController.getProductManager().addProduct(product);
        }
    }
    //Order Management
    public void showOrderTable(){
        Object [] colom= {"Tanggal","Customer","Invoice","Total","Outlet","STATUS"};
        DefaultTableModel model = new DefaultTableModel(null,colom);
        for(Order order : omController.getOrderManager().getOrderList()){
            Object [] data = {
                    order.getNow().toString(),order.getCustomer().getName(),order.getInvoice(),order.getTotal(),
                    order.getOutlet().getName(),order.getOrderStatus()
            };
            model.addRow(data);
        }
        tableOrders.setModel(model);
    }
    public void showCustomerTable(){
        customerList.removeAll();
        DefaultListModel<Customer> model = new DefaultListModel<>();
        for (Customer customer : cmController.getCustomerManager().getCustomerList()){
            model.addElement(customer);
        }
        customerList.setModel(model);
        //customerList.setSelectedIndex(0);
    }
}
