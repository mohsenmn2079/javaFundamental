//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//class Account {
//    int id;
//    String name;
//    long balance;
//    private final Object lock = new Object(); // Lock for synchronization
//
//    public Account(int id, String name, long balance) {
//        this.id = id;
//        this.name = name;
//        this.balance = balance;
//    }
//
//    public void withdraw(long amount) {
//        synchronized (lock) {
//            balance -= amount;
//        }
//    }
//
//    public void deposit(long amount) {
//        synchronized (lock) {
//            balance += amount;
//        }
//    }
//}
//
//class Transaction {
//    int fromAccount;
//    int toAccount;
//    long amount;
//
//    public Transaction(int fromAccount, int toAccount, long amount) {
//        this.fromAccount = fromAccount;
//        this.toAccount = toAccount;
//        this.amount = amount;
//    }
//
//}
//
//
//class FraudDetection implements Runnable {
//    private final Map<Integer, Account> accounts;
//    private final List<Transaction> transactions;
//
//    public FraudDetection(Map<Integer, Account> accounts, List<Transaction> transactions) {
//        this.accounts = accounts;
//        this.transactions = transactions;
//    }
//
//    @Override
//    public void run() {
//        for (Transaction transaction : transactions) {
//            Account fromAccount = accounts.get(transaction.fromAccount);
//            Account toAccount = accounts.get(transaction.toAccount);
//            if (fromAccount != null && toAccount != null) {
//                fromAccount.withdraw(transaction.amount);
//                toAccount.deposit(transaction.amount);
//                System.out.println(fromAccount.name + " " + fromAccount.balance);
//            }
//        }
//    }
//}
//
//
//public class Main {
//    private static final String ACCOUNT_FILE_PATH = "/Users/nic/IdeaProjects/market/multhithreading/src/account.csv";
//    private static final String TRANSACTION_FILE_PATH = "/Users/nic/IdeaProjects/market/multhithreading/src/transaction.csv";
//    private static final String CSV_DELIMITER = ",";
//
//    public static void main(String[] args) {
//        List<Account> accountsList = readAccountsFromFile(ACCOUNT_FILE_PATH);
//        Map<Integer, Account> accounts = new HashMap<>();
//        for (Account account : accountsList) {
//            accounts.put(account.id, account);
//        }
//
//        List<Transaction> transactions = readTransactionsFromFile(TRANSACTION_FILE_PATH);
//
//        int numThreads = Runtime.getRuntime().availableProcessors();
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//
//        int batchSize = (int) Math.ceil((double) transactions.size() / numThreads);
//        for (int i = 0; i < numThreads; i++) {
//            int startIndex = i * batchSize;
//            int endIndex = Math.min((i + 1) * batchSize, transactions.size());
//            List<Transaction> batch = transactions.subList(startIndex, endIndex);
//
//            Runnable task = new FraudDetection(accounts, batch);
//            executor.submit(task);
//        }
//
//        executor.shutdown();
//    }
//
//    public static void writeToArray(int[] array, int index, int value) {
//        array[index] = value;
//        System.out.println("Writing " + value + " to index " + index);
//    }
//
//    public static void readFromArray(int[] array, int index) {
//        int value = array[index];
//        System.out.println("Reading " + value + " from index " + index);
//    }
//    private static List<raceConditions.Account> readAccountsFromFile(String filename) {
//        List<raceConditions.Account> accounts = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            boolean header = false;
//            while ((line = reader.readLine()) != null) {
//                if (!header) {
//                    header = true;
//                    continue;
//                }
//
//                String[] parts = line.split(",",3);
//                if (parts.length == 3) {
//                    int id = Integer.parseInt(parts[0]);
//                    String name = parts[1];
//                    long balance = parseBalance(parts[2]);
//                    accounts.add(new raceConditions.Account(id, name, balance));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return accounts;
//    }
//
//    private static long parseBalance(String balanceStr) {
//        return Long.parseLong(balanceStr.replace(",", "").replace("\"",""));
//    }
//
//    private static List<raceConditions.Transaction> readTransactionsFromFile(String filename) {
//        List<raceConditions.Transaction> transactions = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            boolean headerSkipped = false;
//            while ((line = reader.readLine()) != null) {
//                if (!headerSkipped) {
//                    headerSkipped = true;
//                    continue;
//                }
//
//                String[] parts = line.split(",",3);
//                if (parts.length == 3) {
//                    int fromAccount = Integer.parseInt(parts[0]);
//                    int toAccount = Integer.parseInt(parts[0]);
//                    long amount = parseBalance(parts[2]);
//
//                    transactions.add(new raceConditions.Transaction(fromAccount, toAccount, amount));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return transactions;
//    }
//
//}
//
