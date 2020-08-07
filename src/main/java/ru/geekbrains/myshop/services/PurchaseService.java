package ru.geekbrains.myshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myshop.persistence.entities.Purchase;
import ru.geekbrains.myshop.persistence.repositories.PurchaseRepository;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public Purchase makePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
