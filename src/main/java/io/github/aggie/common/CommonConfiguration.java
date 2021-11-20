package io.github.aggie.common;

import io.github.aggie.common.profiler.Profiler;
import io.github.aggie.common.retry.MethodExecutor;
import io.github.aggie.common.validator.ModelValidator;
import io.github.aggie.common.validator.ValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class CommonConfiguration {

    @Bean
    public Profiler profiler() {
        return new Profiler();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatorService validatorService(Validator validator) {
        return new ValidatorService(validator);
    }

    @Bean
    public ModelValidator modelValidator(ValidatorService validatorService) {
        return new ModelValidator(validatorService);
    }

    @Bean
    public MethodExecutor methodExecutor() {
        return new MethodExecutor();
    }
}
