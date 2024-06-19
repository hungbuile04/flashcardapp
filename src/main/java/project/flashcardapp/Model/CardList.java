package project.flashcardapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * CardList model for keeping sorted state
 */

public class CardList {
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
