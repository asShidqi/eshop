package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, order, method, paymentData);

        switch (method) {
            case "VOUCHER_CODE":
                processVoucherCodePayment(payment);
                break;
            case "CASH_ON_DELIVERY":
                processCashOnDeliveryPayment(payment);
                break;
            case "BANK_TRANSFER":
                processBankTransferPayment(payment);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);

        if ("SUCCESS".equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), "SUCCESS");
        } else if ("REJECTED".equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), "FAILED");
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
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
