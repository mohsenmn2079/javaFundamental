package raceConditions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Account {
    private int id;
    private String name;
    private Long balance;

    public Account(int id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBalance() {
        return balance;
    }

    public synchronized void withdraw(long amount) {
        balance -= amount;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }
}

record Transaction(int fromAccount, int toAccount, long amount) {
}


class FraudDetection implements Runnable {
    private final Map<Integer, Account> accounts;
    private final List<Transaction> transactions;

    public FraudDetection(Map<Integer, Account> accounts, List<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        for (Transaction transaction : transactions) {
            Account fromAccount = accounts.get(transaction.fromAccount());
            Account toAccount = accounts.get(transaction.toAccount());
            if (fromAccount != null && toAccount != null) {
                fromAccount.withdraw(transaction.amount());
                toAccount.deposit(transaction.amount());
            }
        }
    }
}


public class Main {
    private static final String ACCOUNT_FILE_PATH = "/Users/nic/IdeaProjects/market/Stream/src/raceConditions/account.csv";
    private static final String TRANSACTION_FILE_PATH = "/Users/nic/IdeaProjects/market/Stream/src/raceConditions/transaction.csv";

    public static void main(String[] args) {
        Map<Integer, Account> accounts = readAccountsFromFile(ACCOUNT_FILE_PATH);
        Map<Integer, Account> primaryAccounts = readAccountsFromFile(ACCOUNT_FILE_PATH);
        List<Transaction> transactions = readTransactionsFromFile(TRANSACTION_FILE_PATH);

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int batchSize = (int) Math.ceil((double) transactions.size() / numThreads);


        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * batchSize;
            int endIndex = Math.min((i + 1) * batchSize, transactions.size());
            List<Transaction> batch = transactions.subList(startIndex, endIndex);

            Callable<Void> task = () -> {
                new FraudDetection(accounts, batch).run();
                return null;
            };
            tasks.add(task);
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        System.out.println("primary account:");
        primaryAccounts.forEach((integer, account) -> System.out.println("id: " + integer + " balance: " + account.getBalance()));
        System.out.println();
        System.out.println("real account");
        accounts.forEach((integer, account) -> System.out.println("id: " + integer + " balance: " + account.getBalance()));

    }

    public static void writeToArray(int[] array, int index, int value) {
        array[index] = value;
        System.out.println("Writing " + value + " to index " + index);
    }

    public static void readFromArray(int[] array, int index) {
        int value = array[index];
        System.out.println("Reading " + value + " from index " + index);
    }

    private static Map<Integer, raceConditions.Account> readAccountsFromFile(String filename) {
        Map<Integer, raceConditions.Account> accounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = false;
            while ((line = reader.readLine()) != null) {
                if (!header) {
                    header = true;
                    continue;
                }

                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    long balance = parseBalance(parts[2]);
                    accounts.put(id, new raceConditions.Account(id, name, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private static long parseBalance(String balanceStr) {
        return Long.parseLong(balanceStr.replace(",", "").replace("\"", ""));
    }

    private static List<raceConditions.Transaction> readTransactionsFromFile(String filename) {
        List<raceConditions.Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    int fromAccount = Integer.parseInt(parts[0]);
                    int toAccount = Integer.parseInt(parts[1]);
                    long amount = parseBalance(parts[2]);

                    transactions.add(new raceConditions.Transaction(fromAccount, toAccount, amount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}

