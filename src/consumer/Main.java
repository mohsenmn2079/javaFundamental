//package consumer;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Consumer;
//
//// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
//// then press Enter. You can now see whitespace characters in your code.
//public class Main {
//    public static void main(String[] args) {
//        List<String> nameList = new ArrayList<>();
//        nameList.add("afaf1");
//        nameList.add("Afaf");
//        nameList.add("aDaf1");
//        nameList.add("aAf>");
//        nameList.add("afsag");
//
//        Consumer<List<String>> printAll = list ->{
//            System.out.println("all name:");
//            list.forEach(System.out::println);
//        };
//        Consumer<List<String>> modify = list ->{
//            list.forEach(item -> {
//                System.out.println(" modify names:");
//                if (item.matches("[a-zA-Z]+"))
//                    System.out.println(item);
//            });
//        };
//        printAll.andThen(modify).accept(nameList);
//    }
//}