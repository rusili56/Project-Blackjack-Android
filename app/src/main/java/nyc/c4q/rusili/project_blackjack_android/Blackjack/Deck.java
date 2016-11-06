package nyc.c4q.rusili.project_blackjack_android.Blackjack;

import java.util.ArrayList;

public class Deck {
    ArrayList<Cards> cardsArrayList = new ArrayList<>();
    int iCardsDrawn = 0;

    public Deck() {
        this.cardsArrayList.add(new Cards(null, 0));
        for (Cards.Suit suit : Cards.Suit.values()) { //Enhanced "For" Loop
            for (int j = 1; j <= 13; j++) this.cardsArrayList.add(new Cards(suit, j));
        }
    }

    public Cards drawCard(int input) {
        Cards tempCard = new Cards(cardsArrayList.get(input).getCardSuit(), cardsArrayList.get(input).getInputValue());
        this.cardsArrayList.remove(input); // third card, not third in input!!
        iCardsDrawn++;

        return tempCard;
    }

    public int cardsDrawn() {
        return iCardsDrawn;
    }

}
