package com.example.patterns.payment;

public class PayByCard implements PaymentStrategy {


    @Override
    public void collectPaymentDetails() {
        //ask for card number, expiry date and ccv
    }

    @Override
    public boolean pay(int paymentAmount) {
        boolean orderCreated= false;

        // create an order object with the payment method type of card and store it in the data base

        return orderCreated;
    }
}
