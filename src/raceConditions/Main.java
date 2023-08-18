package raceConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Account {
    int id;
    String name;
    long balance;

    public Account(int id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public synchronized void withdraw(long amount) {
        balance -= amount;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public static Account getAccountById(List<Account> accounts, int id) {
        return accounts.stream()
                .filter(account -> account.id == id)
                .findFirst()
                .orElse(null);
    }

}

class Transaction {
    int fromAccount;
    int toAccount;
    long amount;

    public Transaction(int fromAccount, int toAccount, long amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

}

class FraudDetection implements Runnable {
    private final List<Account> accounts;
    private final List<Transaction> transactions;

    public FraudDetection(List<Account> accounts, List<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        for (Transaction transaction : transactions) {
            Account fromAccount = Account.getAccountById(accounts, transaction.fromAccount);
            Account toAccount = Account.getAccountById(accounts, transaction.toAccount);
            if (fromAccount != null && toAccount != null) {
                fromAccount.withdraw(transaction.amount);
                toAccount.deposit(transaction.amount);
            }
        }
    }
}

public class Main {
    private static final String ACCOUNT_FILE_PATH = "/Users/nic/IdeaProjects/market/Stream/src/raceConditions/account.csv";
    private static final String TRANSACTION_FILE_PATH = "/Users/nic/IdeaProjects/market/Stream/src/raceConditions/transaction.csv";
    private static final String CSV_DELIMITER = ",";

    public static void main(String[] args) {
        List<Account> accounts = readAccountsFromFile(ACCOUNT_FILE_PATH);
        List<Transaction> transactions = readTransactionsFromFile(TRANSACTION_FILE_PATH);
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int batchSize = (int) Math.ceil((double) transactions.size() / numThreads);
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * batchSize;
            int endIndex = Math.min((i + 1) * batchSize, transactions.size());
            List<Transaction> batch = transactions.subList(startIndex, endIndex);

            Runnable task = new FraudDetection(accounts, batch);
            executor.submit(task);
        }
        executor.shutdown();
    }

    private static List<Account> readAccountsFromFile(String filename) {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = false;
            while ((line = reader.readLine()) != null) {
                if (!header) {
                    header = true;
                    continue;
                }

                String[] parts = line.split(",",3);
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    long balance = parseBalance(parts[2]);
                    accounts.add(new Account(id, name, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private static long parseBalance(String balanceStr) {
        return Long.parseLong(balanceStr.replace(",", "").replace("\"",""));
    }

    private static List<Transaction> readTransactionsFromFile(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split(",",3);
                if (parts.length == 3) {
                    int fromAccount = Integer.parseInt(parts[0]);
                    int toAccount = Integer.parseInt(parts[0]);
                    long amount = parseBalance(parts[2]);

                    transactions.add(new Transaction(fromAccount, toAccount, amount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
