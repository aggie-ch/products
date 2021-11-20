package io.github.aggie.payments;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);
}
