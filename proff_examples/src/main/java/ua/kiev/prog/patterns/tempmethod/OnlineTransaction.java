package ua.kiev.prog.patterns.tempmethod;

public class OnlineTransaction extends TransactionMethod {
    @Override
    public void enterPinCode() {
        System.out.println("Online: entered pin code");
    }

    @Override
    public void updateBalance() {
        System.out.println("Online: updated balance");
    }

    @Override
    public void checkBalance() {
        System.out.println("Online: checked balance");
    }
}
