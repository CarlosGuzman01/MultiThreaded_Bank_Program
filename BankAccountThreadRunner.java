public class BankAccountThreadRunner{

    public static void main(String[] args) {

        BankAccount account = new BankAccount();
        final double AMOUNT = 100;
        final int REPETITIONS = 100;
        final int THREADS = 100;

        for(int i = 1; i <= THREADS; i++){
           
            DepositRunnable deposit = new DepositRunnable(account, AMOUNT, REPETITIONS);

            WithdrawRunnable withdraw = new WithdrawRunnable(account, AMOUNT, REPETITIONS);

            Thread depositThreat = new Thread(deposit);
            Thread withdrawThread = new Thread(withdraw);

            depositThreat.start();
            withdrawThread.start();
        }
        
    }
}