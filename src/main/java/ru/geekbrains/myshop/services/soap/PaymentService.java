package ru.geekbrains.myshop.services.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.paymentservice.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public List<Payment> getPayments(String country) {

        PaymentPort paymentPort = new PaymentPortService().getPaymentPortSoap11();

        GetPaymentRequest request = new GetPaymentRequest();
        request.setCountry(country);
        GetPaymentResponse response;

        try {
            response = paymentPort.getPayment(request);
        } catch (Exception ex) {
            response = null;
        }

        return response == null ? new ArrayList<>() : response.getPayments();

    }

}