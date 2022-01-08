package models;

import entities.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 7731362323670083475L;
    private final List<Product> katalog = new ArrayList<>();
    public void addProduct(Product product){
        katalog.add(product);
    }
    public Product findProductById(String id){
        for(Product p : katalog){
            if(p.getId().equals(id))
                return p;
        }
        return null;
    }
    public List<Product> getKatalog() {
        return katalog;
    }
}
