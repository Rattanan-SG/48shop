package com.sit.int202.backend.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

}