/**
 * Deck class responsible for creating a standard 52 card deck as well as assigning random cards
 *
 * @author Milosz A. Wudarczyk
 * @version 1.0 - 21/01/2020
 */
package oblig1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Deck {

    // Global variables
    private ArrayList<Card> cards;
    private ArrayList<Character> suits;

    /* Class constructor */
    public Deck()
    {
        cards = new ArrayList<Card>();
        suits = new ArrayList<Character>();

    }

    public static void main(String[] args)
    {
        Deck app = new Deck();
        app.createDeck();
        app.assign(5);
    }

    /**
     * Creates a standard deck of 52 cards
     */
    public void createDeck()
    {
        suits.add('S');
        suits.add('H');
        suits.add('D');
        suits.add('C');
        for(int i = 0; i < suits.size(); i++)
        {
            for (int j = 1; j < 14; j++)
            {
                cards.add(new Card(suits.get(i), j));
            }
        }
    }

    /**
     * Assigns n cards to a collection checking for duplicates
     * @param n amount of cards to pick
     */
    public void assign(int n)
    {
        ArrayList<Integer> cardsPicked = new ArrayList<Integer>();
        ArrayList<Card> assigned = new ArrayList<Card>();
        int i = 0;
        while(i < n)
        {
            int pick = getRandom(0, 51);
            boolean picked = false;
            for (int a = 0; a < cardsPicked.size(); a++) // Checks if card at index pick has already been picked
            {
                if (cardsPicked.get(a) == pick){
                    picked = true;
                }
            }

            if (picked == false)
            {
                assigned.add(cards.get(pick));
                cardsPicked.add(pick);
                i++;
            }
        }

        // UI tweaks
        if (n > 1)
        {
            System.out.println("The " + n + " cards picked at random: \n");
        }
        else {
            System.out.println("The card picked at random: \n");
        }

        assigned.stream().forEach(s -> System.out.println(s.getDetails()));

        assigned.forEach((x) -> System.out.println(x));

        // c)
        System.out.println("\n c)");
        assigned.stream()
                .filter(s -> s.getSuit() == 'S')
                .forEach(s -> System.out.println(s.getDetails()));
        // d)
        System.out.println("\n d)");
        List<Card> hearts = assigned.stream()
                .filter(s -> s.getSuit() == 'H')
                .collect(Collectors.toList());
        hearts.stream().forEach(s -> System.out.println(s.getDetails()));
        // e)
        System.out.println("\n e)");
        List<Character> characters = assigned.stream()
                .map(s -> s.getSuit())
                .collect(Collectors.toList());
        characters.stream()
                .forEach(System.out::println);
        // f)
        System.out.println("\n f)");
        int a = assigned.stream()
                .map(s -> s.getFace())
                .reduce(0, (s, d) -> s+d);
        System.out.print(a + "\n");
        // g)
        System.out.println("\n g)");
        boolean Spar12 = assigned.stream()
                .anyMatch(s -> s.getFace() == 12 && s.getSuit() == 'S');
        System.out.println("Does Spar 12 exist in this hand: " + Spar12);
        // h)
        System.out.println("\n h)");
        characters.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((s, d) ->
                {
                    if ((d > 5)) {
                        System.out.println(s + " Poker flush");
                    } else {
                        System.out.println("No " + s + " poker flush");
                    }
                });

    }


    /**
     * Function that returns a random int on a specific interval
     * @param min the minimum number
     * @param max the maximum number
     * @return int ranging from min to max
     */
    public static int getRandom(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

}