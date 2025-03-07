package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Order testOrder;
    private Payment testPayment;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        // Setup test order
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("a95586ef-1e39-46fe-9865-71afaafa3bda");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        testOrder = new Order("order123", products, 1708573000L, "Safira Sudrajat");

        // Setup test payment
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABCD");
        testPayment = new Payment("payment123", testOrder, "VOUCHER_CODE", paymentData);
    }
    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(testPayment);

        assertEquals(testPayment.getId(), savedPayment.getId());
        assertEquals(1, paymentRepository.findAll().size());
    }
    @Test
    void testFindById() {
        paymentRepository.save(testPayment);

        Payment foundPayment = paymentRepository.findById("payment123");

        assertNotNull(foundPayment);
        assertEquals("payment123", foundPayment.getId());
        assertEquals("VOUCHER_CODE", foundPayment.getMethod());
    }

    @Test
    void testFindByIdNotFound() {
        Payment foundPayment = paymentRepository.findById("nonexistent");

        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        // Setup additional payment
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP87654321ABCD");
        Payment testPayment2 = new Payment("payment456", testOrder, "VOUCHER_CODE", paymentData2);

        paymentRepository.save(testPayment);
        paymentRepository.save(testPayment2);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
        assertTrue(allPayments.stream().anyMatch(p -> p.getId().equals("payment123")));
        assertTrue(allPayments.stream().anyMatch(p -> p.getId().equals("payment456")));
    }

    @Test
    void testFindAllEmpty() {
        List<Payment> allPayments = paymentRepository.findAll();

        assertTrue(allPayments.isEmpty());
    }
}
