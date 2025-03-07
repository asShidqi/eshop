package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("a95586ef-1e39-46fe-9865-71afaafa3bda");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("0f14d554-d12d-4c97-b65d-feeb17bfd7bb", products, 1708560000L, "Safira Sudrajat");
    }
    @Test
    void testCreatePaymentWithValidData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        Payment payment = new Payment("payment123", order, "VOUCHER_CODE", paymentData);

        assertEquals("payment123", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
        assertEquals("PENDING", payment.getStatus());
    }
    @Test
    void testCreatePaymentWithEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("payment123", order, "VOUCHER_CODE", null);
        });
    }
}
