package models;

import entities.Product;

import java.io.Serial;
import java.io.Serializable;

public class SaleLineItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 4313216952418967063L;
    private Product product;
    private int jumlah;

    public SaleLineItem(Product product, int jumlah) {
        this.product = product;
        this.jumlah = jumlah;
    }
    public SaleLineItem(){
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public double getSubTotal(){
        return product.getPrice()*jumlah;
    }
    @Override
    public String toString() {
        return "SaleItem{" +
                "product=" + product +
                ", jumlah=" + jumlah +
                '}';
    }
}
