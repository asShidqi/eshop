package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public  Product create(Product product){
        product.setProductId(UUID.randomUUID().toString()); // Set a unique ID
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product getProduct(String id){
        Iterator<Product> data = this.findAll();
        while (data.hasNext()) {
            Product product = data.next();
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product edit(String productId, String productName, int productQuantity) {
        Product oldProduct = getProduct(productId);
        if(oldProduct == null) return null;
        oldProduct.setProductName(productName);
        oldProduct.setProductQuantity(productQuantity);
        return oldProduct;
    }
}
