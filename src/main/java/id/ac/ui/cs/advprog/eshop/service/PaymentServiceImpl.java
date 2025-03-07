package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private OrderService orderService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }

    private void processVoucherCodePayment(Payment payment) {

    }

    private boolean isValidVoucherCode(String code) {
        return false;
    }

    private void processCashOnDeliveryPayment(Payment payment) {

    }

    private void processBankTransferPayment(Payment payment) {
    }
}
