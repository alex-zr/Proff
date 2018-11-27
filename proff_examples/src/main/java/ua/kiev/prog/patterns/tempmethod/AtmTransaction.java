package ua.kiev.prog.patterns.tempmethod;

public class AtmTransaction extends TransactionMethod {
    @Override
    public void enterPinCode() {
        System.out.println("ATM: entered pin code");
    }

    @Override
    public void updateBalance() {
        System.out.println("ATM: updated balance");
    }

    @Override
    public void checkBalance() {
        System.out.println("ATM: checked balance");
    }
}