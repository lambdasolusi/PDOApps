package controllers;

import entities.Product;
import models.ProductManager;

public class PMController {
    private ProductManager productManager;

    public PMController(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void registerProduct(Product product){
        productManager.addProduct(product);
    }
    public Product findProductById(String id){
        return productManager.findProductById(id);
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}
