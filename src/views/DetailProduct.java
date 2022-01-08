package views;

import entities.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DetailProduct extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton closeButton;
    private JLabel imageLabel;
    private JLabel txtName;
    private JLabel txtKatagori;
    private JLabel txtHarga;
    private JLabel txtStock;
    private JLabel txtID;
    private JButton uploadButton;
    private JButton saveButton;
    private Product product;
    private ImageIcon productImage;

    public DetailProduct(MainFrame parent,Product product) {
        this.product = product;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);
        displayProduct(product);
        setTitle("Product ID "+product.getId());
        closeButton.addActionListener(e -> {
            dispose();
        });
        uploadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            if(file != null) {
                String filename = file.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filename);
                Image imgObject = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                productImage = new ImageIcon(imgObject);
                imageLabel.setIcon(new ImageIcon(productImage.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH)));
            }
        });
        saveButton.addActionListener(e -> {
            parent.getPmController().getProductManager().getKatalog().remove(product);
            product.setImage(productImage);
            parent.getPmController().registerProduct(product);
        });
    }
    void displayProduct(Product product){
        txtID.setText("ID : "+product.getId());
        txtName.setText("Name  \t: "+product.getName());
        txtKatagori.setText("Katagori \t: "+product.getCategory());
        imageLabel.setIcon(product.getImage());
        txtHarga.setText("Harga : "+String.valueOf(product.getPrice()));
        if(product.getImage() == null){
            ImageIcon icon = new ImageIcon(getClass().getResource("../icons/no_image.png"));
            Image img = icon.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        }
    }
}
