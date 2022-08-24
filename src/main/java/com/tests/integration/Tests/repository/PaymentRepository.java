package com.tests.integration.Tests.repository;

import com.tests.integration.Tests.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long > {
}
