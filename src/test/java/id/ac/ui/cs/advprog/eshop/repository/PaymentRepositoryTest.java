package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
