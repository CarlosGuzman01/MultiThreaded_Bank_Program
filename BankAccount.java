import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    
    private double balance;
    private Lock balanceChangeLock;
    private Condition sufficientFundsCondition;
    
    public BankAccount(){
        balance = 0;
        balanceChangeLock =  new ReentrantLock();
        sufficientFundsCondition = balanceChangeLock.newCondition();
    }

    public void deposit(double amount){
        
        balanceChangeLock.lock();
        try{

        System.out.print("Depositing " + amount);
        double newBalance = balance + amount;
        System.out.println(", new balance is " + newBalance);
        balance = newBalance;
        sufficientFundsCondition.signalAll();

        }catch(Exception exception){

        }finally{
            balanceChangeLock.unlock();
        }
        
    }

    public void withdraw(double amount){

        balanceChangeLock.lock();
        try{

            while (balance < amount) {
                sufficientFundsCondition.await();
                
            }

        System.out.print("Withdrawing " + amount);
        double newBalance = balance - amount;
        System.out.println(", new balance is " +  newBalance);
        balance = newBalance;

        }catch(Exception exception){

        }finally{
            balanceChangeLock.unlock();
        }
        
    }

    public double getBalance(){
        return balance;
    }

}
