package com.example.patterns.payment;

public class PayByCard implements PaymentStrategy {
    String cardNumber, ccv, date;


    @Override
    public void collectPaymentDetails() {
        //ask for card number, expiry date and ccv and address
     //this would be done using edit texts eg cardNumber= findById(R.id.editTextCardNumberInput)
    }

    @Override
    public boolean pay(int paymentAmount) {
        boolean orderCreated= false;

        // create an order object with the payment method type of card and store it in the data base

        return orderCreated;
    }
}
