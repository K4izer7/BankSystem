public class BankAccount {
    private double balance;
    public BankAccount(){
        balance = 0.00;
    }
    public void deposit(double amount){
        if(amount > 0){
            balance = balance + amount;
        }
    }
    public boolean withdraw(double amount){
        if(amount >0 && amount <= balance){
            balance = balance - amount;
            return true;
        }
        return false;
    }
    public double getBalance(){
        return balance;
    }
}