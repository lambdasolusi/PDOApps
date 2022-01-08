package enums;

public enum ProductCategory {
    Pizza,Pasta_Rice,Drink,Appetizer;
    @Override
    public String toString() {
        switch (ProductCategory.this){
            case Pizza: return "Pizza";
            case Pasta_Rice: return "Pasta & Rice";
            case Drink: return "Drink";
            case Appetizer: return "Appetizer";
            default: return null;
        }
    }
}
