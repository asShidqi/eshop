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
        return paymentRepository.findAll();
    }

    private void processVoucherCodePayment(Payment payment) {
        String voucherCode = payment.getPaymentData().get("voucherCode");

        if (isValidVoucherCode(voucherCode)) {
            setStatus(payment, "SUCCESS");
        } else {
            setStatus(payment, "REJECTED");
        }
    }


    private boolean isValidVoucherCode(String code) {
        if (code == null || code.length() != 16) {
            return false;
        }

        if (!code.startsWith("ESHOP")) {
            return false;
        }

        // Check if contains 8 numerical characters
        int numCount = 0;
        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }

        return numCount == 8;
    }

    private void processCashOnDeliveryPayment(Payment payment) {
        Map<String, String> data = payment.getPaymentData();
        String address = data.get("address");
        String deliveryFee = data.get("deliveryFee");

        if (address == null || address.isEmpty() ||
                deliveryFee == null || deliveryFee.isEmpty()) {
            setStatus(payment, "REJECTED");
        } else {
            setStatus(payment, "SUCCESS");
        }
    }

    private void processBankTransferPayment(Payment payment) {
        Map<String, String> data = payment.getPaymentData();
        String bankName = data.get("bankName");
        String referenceCode = data.get("referenceCode");

        if (bankName == null || bankName.isEmpty() ||
                referenceCode == null || referenceCode.isEmpty()) {
            setStatus(payment, "REJECTED");
        } else {
            setStatus(payment, "SUCCESS");
        }
    }
}
