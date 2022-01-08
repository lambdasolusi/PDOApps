package views;

import entities.Product;
import enums.ProductCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class RegisterProductDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idInput;
    private JTextField nameInput;
    private JTextField priceInput;
    private JComboBox cbKatagori;
    private JButton uploadButton;
    private JLabel imageIconLabel;
    private final MainFrame parent;
    private ImageIcon defaultNoImageIcon;

    public RegisterProductDialog(MainFrame parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        //initial no_image
        ImageIcon image = new ImageIcon(getClass().getResource("../icons/no_image.png"));
        //fit to label
        Image img = image.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
        defaultNoImageIcon = new ImageIcon(img);
        imageIconLabel.setIcon(defaultNoImageIcon);
        //initial combo katagori
        DefaultComboBoxModel model = new DefaultComboBoxModel(ProductCategory.values());
        cbKatagori.setModel(model);
        cbKatagori.setSelectedIndex(0);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener( e-> onCancel());
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
        uploadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            if(file != null) {
                String filename = file.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(filename);
                defaultNoImageIcon = new ImageIcon(filename);
                Image imgObject = defaultNoImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                defaultNoImageIcon = new ImageIcon(imgObject);
                imageIconLabel.setIcon(new ImageIcon(defaultNoImageIcon.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH)));
            }
        });
    }
    private void onOK() {
        // add your code here
        Product product = new Product();
        product.setId(idInput.getText());
        product.setName(nameInput.getText());
        product.setCategory(cbKatagori.getSelectedItem().toString());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setImage(defaultNoImageIcon);
        if(product !=null){
            parent.getPmController().registerProduct(product);
            parent.sendMessage("Product id "+idInput.getText()+" is added to PM Collection");
        }
        dispose();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
