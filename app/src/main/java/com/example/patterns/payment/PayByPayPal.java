package com.example.patterns.payment;

class PayByPayPal implements PaymentStrategy {
    private String email;
    private String password;
    private boolean signedIn;


    @Override
    public void collectPaymentDetails() {

        //ask for username and password for pay pal
    }

    @Override
    public boolean pay(int paymentAmount) {
        boolean orderCreated= false;

        // create an order object with the payment method type of paypal and store it in the data base
        return orderCreated;
    }
}
