package kz.lab.project1;

public class TransactionService implements TransactionInterface {
    TransactionMode transactionMode;
    public TransactionService(TransactionMode transactionMode) {
        this.transactionMode = transactionMode;
    }
    @Override
    public String startTransaction(String cardNumber, long amount) {
        return ApiService.getInstance().start(cardNumber, amount);
    }

    @Override
    public String completeTransaction(String transactionId, boolean success) {
        return switch (transactionMode){
            case MODE1 -> ApiService.getInstance().completeMode1(transactionId, success);
            case MODE2 -> ApiService.getInstance().completeMode2(transactionId, success);
        };
    }
}
