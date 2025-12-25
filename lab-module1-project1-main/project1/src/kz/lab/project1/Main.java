import kz.lab.project1.TransactionInterface;
import kz.lab.project1.TransactionMode;
import kz.lab.project1.TransactionService;

void main() {
    IO.println("Project1...");
    // 1

    TransactionInterface transactionInterface = new TransactionService(TransactionMode.MODE1);

    String cardNumber = "4111111111111111";
    long amount = 100;
    String transactionId = transactionInterface.startTransaction(cardNumber, amount);

    IO.println(String.format("TransactionId: %s", transactionId));

    boolean success = true;
    String result = transactionInterface.completeTransaction(transactionId, success);
    IO.println(String.format("Result: %s", result));

    // 2

    TransactionInterface transactionInterfaceMode2 = new TransactionService(TransactionMode.MODE2);

    String cardNumberMode2 = "4111111111111111";
    long amountMode2 = 100;
    String transactionIdMode2 = transactionInterfaceMode2.startTransaction(cardNumberMode2, amountMode2);

    IO.println(String.format("TransactionIdMode2: %s", transactionIdMode2));

    boolean successMode2 = true;
    String resultMode2 = transactionInterfaceMode2.completeTransaction(transactionIdMode2, successMode2);
    IO.println(String.format("Result: %s", resultMode2));
}
