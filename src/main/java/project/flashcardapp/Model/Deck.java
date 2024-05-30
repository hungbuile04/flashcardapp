package project.flashcardapp.Model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Category model for keeping name and cards
 *
 * @author Bartlomiej Gladys
 * @Date 01/11/2018
 * @version 1.0
 */

public class Deck {
    /**
     * Category's name
     */
    private String deckName;
    private String labelDescription;

    /**
     * Category's cards
     */
    private CardList cards = new CardList();
    private int newCards=0;
    private int learnedCards=0;
    private int dueCards=0;
    private int easyCard=3;
    private int mediumCard=2;
    private int hardCard=1;


    public Deck() {
        this.deckName = deckName;
    }

    public Deck(String name, CardList cards) {
        this.deckName = name;
        this.cards = cards;
    }

    public Deck(String deckName, String labelDesciption) {
        this.deckName = deckName;
        this.labelDescription = labelDesciption;
    }
    /**
     * Category's name getter
     *
     * @return category's name
     */
    public String getDeckName() {
        return deckName;
    }

    /**
     * Category's name setter
     *
     * @param name passed by user
     * @return name if passed correctly
     * @throws //NameFormatException if incorrect data
     */
    public Deck setDeckName(String name) throws Exception {
        if (name == null) {
            Exception e = new Exception("Category's name can't be blank") {
                // Khối khởi tạo của anonymous class
                {
                    System.out.println("Exception: " + getMessage());
                }
            };
            e.printStackTrace();
            throw e;
        }
        if (name.length() > 20) {
            Exception e = new Exception("Category's name has to be up to 15 characters") {
                // Khối khởi tạo của anonymous class
                {
                    System.out.println("Exception: " + getMessage());
                }
            };
            e.printStackTrace();
            throw e;
        }
        if (name.length() < 2) {
            Exception e = new Exception("Category's name has to have at least 2 characters") {
                // Khối khởi tạo của anonymous class
                {
                    System.out.println("Exception: " + getMessage());
                }
            };
        }
        this.deckName = name;
        return this;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public int getNewCards() {
        return newCards;
    }

    public int getLearnedCards() {
        return learnedCards;
    }

    public int getDueCards() {
        return dueCards;
    }

    public void setNewCards(int newCards) {
        this.newCards = newCards;
    }

    public void setLearnedCards(int learnedCards) {
        this.learnedCards = learnedCards;
    }

    public void setDueCards(int dueCards) {
        this.dueCards = dueCards;
    }

    public int getEasyCard() {
        return easyCard;
    }

    public void setEasyCard(int easyCard) {
        this.easyCard = easyCard;
    }

    public int getMediumCard() {
        return mediumCard;
    }

    public void setMediumCard(int mediumCard) {
        this.mediumCard = mediumCard;
    }

    public int getHardCard() {
        return hardCard;
    }

    public void setHardCard(int hardCard) {
        this.hardCard = hardCard;
    }

    /**
     * Category's getter
     *
     * @return Category's cards
     */
    public CardList getCards() {
        return cards;
    }
    public void counting(){
        Date now = new Date();
        this.dueCards=0;
        this.learnedCards=0;
        this.newCards=0;
        for(int i=0; i<cards.getAll().size(); i++){
            if(cards.getAll().get(i).getSelector().getDeadlineAt().compareTo(now)<=0 && cards.getAll().get(i).getSelector().getCycle()!=0){
                this.dueCards++;
            }
            if(cards.getAll().get(i).getSelector().getCycle()==0){
                this.newCards++;
            }
            if(cards.getAll().get(i).getSelector().getDeadlineAt().compareTo(now)>0 && cards.getAll().get(i).getSelector().getCycle()!=0){
                this.learnedCards++;
            }
        }
    }

    public void randomCard(CardList cards){
        Collections.shuffle(cards.getAll());
    }

    @Override
    public String toString() {
        return deckName;
    }
}
