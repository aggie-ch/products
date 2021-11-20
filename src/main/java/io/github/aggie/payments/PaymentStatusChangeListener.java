package io.github.aggie.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log
public class PaymentStatusChangeListener {

    @Async
    @EventListener
    public void onPaymentStatusChanged(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment status was changed to: " + statusChangedEvent.getPayment());
    }
}
