package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTest {
    private PaymentService paymentService;
    private OrderService orderService;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        paymentRepository = new PaymentRepository();
        orderService = new OrderServiceImpl(orderRepository);
        paymentService = new PaymentServiceImpl(paymentRepository, orderService);

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("a95586ef-1e39-46fe-9865-71afaafa3bda");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        testOrder = orderService.createOrder(new Order("order123", products, 1708570000L, "Safira Sudrajat"));
    }
    @Test
    void testAddPaymentWithValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABCD");

        Payment payment = paymentService.addPayment(testOrder, "VOUCHER_CODE", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testAddPaymentWithInvalidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID123");

        Payment payment = paymentService.addPayment(testOrder, "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());
    }
    @Test
    void testAddPaymentWithValidCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Kebon Jeruk No.15");
        paymentData.put("deliveryFee", "15000");

        Payment payment = paymentService.addPayment(testOrder, "CASH_ON_DELIVERY", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testAddPaymentWithInvalidCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "15000");

        Payment payment = paymentService.addPayment(testOrder, "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());
    }
    @Test
    void testAddPaymentWithValidBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF1234567890");

        Payment payment = paymentService.addPayment(testOrder, "BANK_TRANSFER", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testAddPaymentWithInvalidBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        Payment payment = paymentService.addPayment(testOrder, "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());
    }
}
