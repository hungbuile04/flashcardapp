package project.flashcardapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * CardList model for keeping sorted state
 *
 * @author Bartlomiej Gladys
 * @Date 01/11/2018
 * @version 1.0
 */

public class CardList {
    /**
     * Sort cards and return them
     *
     * @return sorted list of cards
     */
    List<Card> cards = new ArrayList<>();

    public CardList(Card...cards){
        for(Card card : cards){
            this.cards.add(card);
        }
    }

    public List<Card> getAll() {
        cards.sort(new SortBySelector());
        return cards;
    }

    public Card getCard(int index){
        return cards.get(index);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public int getSize() {
        return cards.size();
    }
}
