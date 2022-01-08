package apps;

import controllers.OMController;
import controllers.PMController;
import entities.Product;
import entities.Shop;
import models.ProductManager;
import utilities.FileServices;
import views.MainFrame;

public class Apps {
    private static Shop shop;
    private static MainFrame mainFrame;
    private final static String DEFAULT_FILENAME = "shop.pdo";
    public static void main(String[] args) {
        startApp();
    }

    private static void startApp(){
        //load Data Shop
        shop = (Shop) FileServices.readObjectFromFile(DEFAULT_FILENAME);
        if (shop == null){
            shop = new Shop();
        }
        mainFrame = new MainFrame(shop);
        mainFrame.setVisible(true);
        mainFrame.initial();
    }

    public static String getDefaultFilename() {
        return DEFAULT_FILENAME;
    }
    public static Shop getShop() {
        return shop;
    }
}
