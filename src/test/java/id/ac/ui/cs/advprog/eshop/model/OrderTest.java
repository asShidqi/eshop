package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    private List<Product> products;
    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product products = new Product();
        products.setProductId("a95586ef-1e39-46fe-9865-71afaafa3bda");
        products.setProductName("Sampo Cap Bambang");
        products.setProductQuantity(2);
        Product products2 = new Product();
        products2.setProductId("a92e3293-4e37-46e4-83c7-f32db4624615");
        products2.setProductName("Sabun Cap Usep");
        products2.setProductQuantity(1);
        this.products.add(products);
        this.products.add(products2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                    this.products, "orderName:17085ad001", "author:Safira Sudrajat");
        });
    }
    Copy@Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                this.products, "orderName:17085A6D001", "author:Safira Sudrajat");

        assertEquals(this.products, order.getProducts());
        assertEquals(expected: 2, order.getProducts().size());
        assertEquals(expected: "Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals(expected: "Sabun Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals(expected: "0f14d554-d12d-4c97-b65d-feeb17bfd7bb", order.getId());
        assertEquals(expected: "17085A6D001", order.getOrderTime());
        assertEquals(expected: "Safira Sudrajat", order.getAuthor());
        assertEquals(expected: "WAITING", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                this.products, "orderName:17085A6D001", "author:Safira Sudrajat", status: "SUCCESS");
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                    this.products, "orderName:17085A6D001", "author:Safira Sudrajat", status: "ERROR");
        });
    }

    @Test
    void testSetStatusIfCancelled() {
        Order order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                this.products, "orderName:17085A6D001", "author:Safira Sudrajat");
        order.setStatus("CANCELLED");
        assertEquals(expected: "CANCELLED", order.getStatus());
    }
    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(id: "0f14d554-d12d-4c97-b65d-feeb17bfd7bb",
                this.products, orderTime: 17085A6D001L, author: "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}
