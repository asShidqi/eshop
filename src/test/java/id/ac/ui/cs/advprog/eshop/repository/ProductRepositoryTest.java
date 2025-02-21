package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("aof9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Updated");
        product.setProductQuantity(150);
        Product updatedProduct = productRepository.edit(product.getProductId(),product.getProductName(),product.getProductQuantity());

        assertNotNull(updatedProduct);
        assertEquals("Sampo Cap Updated", updatedProduct.getProductName());
        assertEquals(150, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditProduct_Failure_ProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("unknown-id");
        nonExistentProduct.setProductName("Non-existent");
        nonExistentProduct.setProductQuantity(200);
        assertNull(productRepository.edit(nonExistentProduct.getProductId(),nonExistentProduct.getProductName(),nonExistentProduct.getProductQuantity()));
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        boolean deleted = productRepository.delete(product);
        assertTrue(deleted);
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("unknown-id");
        nonExistentProduct.setProductName("Non-existent");
        nonExistentProduct.setProductQuantity(200);
        boolean deleted = productRepository.delete(nonExistentProduct);
        assertFalse(deleted);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        productRepository.create(product);
        product.setProductId("1234");

        Product foundProduct = productRepository.getProduct("1234");

        assertNotNull(foundProduct);
        assertEquals("1234", foundProduct.getProductId());
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());
    }

    @Test
    void testGetProductById_NotFound() {
        Product foundProduct = productRepository.getProduct("non-existent-id");
        assertNull(foundProduct);
    }
}
