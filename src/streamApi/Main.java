package streamApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //read sample.txt and split sentences by '.' and stopWord.txt
        List<String> sentences = readSentencesFile("/Users/nic/IdeaProjects/market/Stream/src/streamApi/sample.txt");
        List<String> stopWordList = readStopWord("/Users/nic/IdeaProjects/market/Stream/src/streamApi/stopWord.txt");

        //count of sentences
        int sentencesCount = sentences.size();


        //count of word in each sentences
        List<Long> countEachSentence = sentences.stream()
                .map(sentence -> Arrays.stream((sentence.split(" ")))
                        .filter(word -> !stopWordList.contains(word)).count()).toList();

        //count of all word
        long countAllWord = sentences.stream()
                .map(sentence -> Arrays.stream((sentence.split(" ")))
                        .filter(word -> !stopWordList.contains(word)).count())
                .mapToLong(Long::longValue)
                .sum();



        //Number of repetitions of each word
        Map<String,Long> countOfRepetitionWord = sentences.stream()
                .map(sentence -> sentence.split(" "))
                .flatMap(Stream::of)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));


        //
        List<String> listOfWord = new ArrayList<>(countOfRepetitionWord.keySet());
        Map<String,List<Integer>> listSentencesUsedEachWord = new HashMap<>();
        listOfWord.forEach(word -> listSentencesUsedEachWord.put(word,
                sentences.stream()
                        .filter(sentence->sentence.contains(word))
                        .map(sentences::indexOf)
                        .collect(Collectors.toList())));
        //
        Map<String,List<String>> listSentencesStartWithEachWord = new HashMap<>();
        listOfWord.forEach(word -> listSentencesStartWithEachWord.put(word,
                sentences.stream()
                        .filter(sentence->sentence.contains(word))
                        .collect(Collectors.toList())));

        System.out.println("Count of sentences : " + sentencesCount);
        System.out.println("Count of word each sentences :\n" + countEachSentence.toString());
        System.out.println("Count of all the word in text: " + countAllWord);
        System.out.println("List Count of repetition word : \n" + countOfRepetitionWord.toString());
        System.out.println(listSentencesUsedEachWord.toString());
        System.out.println(listSentencesStartWithEachWord.toString());
    }

    public static List<String> readSentencesFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return Arrays.stream(reader.lines().toList()
                    .stream().filter(line -> !line.isEmpty())
                    .map(String::trim).collect(Collectors.joining()).split("\\.")).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readStopWord(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            System.out.println(reader.lines());
            return reader.lines().toList()
                    .stream().filter(line -> !line.isEmpty())
                    .map(String::trim).toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}