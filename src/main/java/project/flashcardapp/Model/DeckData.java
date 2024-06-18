package project.flashcardapp.Model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * CategoryList model for keeping all Categories
 *
 * @author Bartlomiej Gladys
 * @Date 01/11/2018
 * @version 1.0
 */

public class DeckData {

       public static DeckData instance = new DeckData();
       public static DeckData getInstance() {return instance;}
       public static ObservableList<Deck> decks = FXCollections.observableArrayList();;
       public static Deck deck;
       public static int studiedCardsToday=0;
       public static void setDecks(ObservableList<Deck> decks) {
              DeckData.decks = decks;
       }


       public ObservableList<Deck> getDecks() {return decks;}

//       Card card1 = new Card("hello","chào");
//       Card card2 = new Card("bye", "tạm biệt");
//       Card card3 = new Card("study","học");
//       CardList cl1 = new CardList(card1,card2,card3);
//
//       Card card4 = new Card("sleep","ngủ");
//       Card card5 = new Card("eat", "ăn");
//       CardList cl2 = new CardList(card4,card5);
//
//       Deck deck1 = new Deck("Deck1",cl1);
//       Deck deck2 = new Deck("Deck2",cl2);


       //nạp dữ liệu khi bật chương trình
       public void loadDeck() {
           Gson gson = new Gson();
           try (FileReader reader = new FileReader("decks.json")) {
               Type listType = new TypeToken<List<Deck>>() {}.getType();
               List<Deck> list = gson.fromJson(reader, listType); // Deserialize
               decks = FXCollections.observableArrayList(list);
               System.out.println("loaded decks:"+decks.size());
           } catch (IOException e) {
               e.printStackTrace();
           }
           for (Deck d : decks) {
               d.counting();
           }
       }
       //lưu dữ liệu khi tắt
       public void storeDeck() {
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
           String json = gson.toJson(decks);
           try (FileWriter writer = new FileWriter("decks.json")) {
               writer.write(json);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
}


