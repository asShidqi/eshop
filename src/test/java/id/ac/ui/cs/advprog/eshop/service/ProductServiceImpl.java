package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.getProduct("1")).thenReturn(product);
        when(productRepository.delete(product)).thenReturn(true);

        productService.delete("1");

        verify(productRepository, times(1)).getProduct("1");
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testGetProduct() {
        when(productRepository.getProduct("1")).thenReturn(product);

        Product foundProduct = productService.getProduct("1");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
        verify(productRepository, times(1)).getProduct("1");
    }

    @Test
    void testEditProduct() {
        when(productRepository.edit(anyString(), anyString(), anyInt())).thenReturn(product);

        productService.edit(product);

        verify(productRepository, times(1)).edit(anyString(), anyString(), anyInt());
    }
}