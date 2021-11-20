package io.github.aggie.payments;

import io.github.aggie.common.profiler.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;

@Transactional
@Log
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @ExecutionTime
    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {

        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        eventPublisher.publishEvent(new PaymentStatusChangedEvent(this, payment));
        return paymentRepository.save(payment);
    }

    @PostConstruct
    public void init() {
        log.info("PaymentService initialized");
    }

    @PreDestroy
    public void destroy() {
        log.info("PaymentService is going down.");
    }
}
