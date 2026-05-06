package com.daclink;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Validator {
    private HashMap<String, List<String>> wordBank;
    private Random random;

    public Validator() {
        wordBank = new HashMap<>();
        random = new Random();
        loadWordsFromFile();
    }

    public String getRandomLetter() {
        ArrayList<String> letters = new ArrayList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        letters.add("J");
        letters.add("K");
        letters.add("L");
        letters.add("M");
        letters.add("N");
        letters.add("O");
        letters.add("P");
        letters.add("R");
        letters.add("S");
        letters.add("T");
        letters.add("U");
        letters.add("V");
        letters.add("W");
        letters.add("X");
        letters.add("Y");
        int randomIndex = random.nextInt(letters.size());
        return letters.get(randomIndex);
    }

    private void loadWordsFromFile() {
        try {
            FileReader fr = new FileReader("src/main/resources/words.json");
            Gson gson = new Gson();
            wordBank = gson.fromJson(fr, HashMap.class);
            System.out.println("Complete!");
        } catch (Exception e) {
            System.out.println("Error" + e + "!");
            System.out.println("Using backup now!");
            BackUpWords();
        }
    }

    private void BackUpWords() {
        wordBank = new HashMap<>();
        ArrayList<String> animals = new ArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("bird");
        animals.add("fish");
        animals.add("lion");
        wordBank.put("animals", animals);

        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
        colors.add("green");
        colors.add("purple");
        colors.add("brown");
        colors.add("orange");
        wordBank.put("colors", colors);

        ArrayList<String> foods = new ArrayList<>();
        foods.add("pizza");
        foods.add("taco");
        foods.add("burrito");
        foods.add("cookie");
        foods.add("salad");
        foods.add("burger");
        foods.add("soup");
        foods.add("cereal");
        wordBank.put("foods", foods);

        ArrayList<String> badHabits = new ArrayList<>();
        badHabits.add("smoking");
        badHabits.add("gambling");
        badHabits.add("lying");
        badHabits.add("cheating");
        badHabits.add("gossiping");
        wordBank.put("badHabits", badHabits);

        ArrayList<String> politicians = new ArrayList<>();
        politicians.add("trump");
        politicians.add("biden");
        politicians.add("obama");
        politicians.add("clinton");
        politicians.add("bush");
        wordBank.put("politicians", politicians);

        ArrayList<String> countries = new ArrayList<>();
        countries.add("canada");
        countries.add("mexico");
        countries.add("france");
        countries.add("germany");
        countries.add("japan");
        countries.add("unitedstates");
        wordBank.put("countries", countries);

        ArrayList<String> sports = new ArrayList<>();
        sports.add("soccer");
        sports.add("tennis");
        sports.add("golf");
        sports.add("boxing");
        sports.add("swimming");
        wordBank.put("sports", sports);


        ArrayList<String> movies = new ArrayList<>();
        movies.add("titanic");
        movies.add("avatar");
        movies.add("matrix");
        movies.add("frozen");
        movies.add("jaws");
        wordBank.put("movies", movies);

        ArrayList<String> celebrities = new ArrayList<>();
        celebrities.add("beyonce");
        celebrities.add("taylorswift");
        celebrities.add("adele");
        celebrities.add("kanye");
        wordBank.put("celebrities", celebrities);

        ArrayList<String> cars = new ArrayList<>();
        cars.add("ford");
        cars.add("toyota");
        cars.add("honda");
        cars.add("bmw");
        cars.add("tesla");
        wordBank.put("cars", cars);
    }

    public boolean isValid(String category, String answer, String letter) {
        if (answer == null || answer.equals("")) {
            return false;
        }

        String cleanAnswer = answer.toLowerCase();
        cleanAnswer = cleanAnswer.replace(" ", "");

        if (cleanAnswer.equals("")) {
            return false;
        }

        String firstLet = cleanAnswer.substring(0, 1);
        if (!firstLet.equals(letter.toLowerCase())) {
            return false;
        }
        List<String> validWords = wordBank.get(category.toLowerCase());
        if (validWords == null) {
            return false;
        }
        for (int i = 0; i < validWords.size(); i++) {
            String word = validWords.get(i);
            if (word.equals(cleanAnswer)) {
                return true;
            }
        }
        return false;
    }
}

