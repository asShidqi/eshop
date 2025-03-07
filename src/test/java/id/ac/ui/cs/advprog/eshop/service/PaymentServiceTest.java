package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

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
}
