//package function;
//
//import java.util.function.Function;
//
//// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
//// then press Enter. You can now see whitespace characters in your code.
//public class Main {
//    public static void main(String[] args) {
//        Function<Integer, Double> half = a -> a / 2.0;
//
//        half = half.compose(a -> 3 + a);// first half run and then compose
////        half = half.andThen(a -> 3 + a);first andThen run and then half
//
//        System.out.println(half.apply(10));
//    }
//}