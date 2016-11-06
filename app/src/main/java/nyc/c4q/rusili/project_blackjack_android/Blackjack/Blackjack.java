package nyc.c4q.rusili.project_blackjack_android.Blackjack;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import nyc.c4q.rusili.project_blackjack_android.R;
import nyc.c4q.rusili.project_blackjack_android.Visuals.PrintDealer;
import nyc.c4q.rusili.project_blackjack_android.Visuals.PrintPlayer;

public class Blackjack extends AppCompatActivity{

    static Random randomGenerator = new Random();
    Context fieldContext; static Activity fieldActivity; static RecyclerView fieldRecyclerView;
    static TextView tvDealerTotal, tvPlayerTotal;
    private static Deck deck1; private static Cards[] dealerCards; private static ArrayList<Cards> playerCards;
    static int iDealerTotal, iPlayerTotal, iPlayerCards, iDealerCards;

    PrintDealer dPrint;
    PrintPlayer pPrint;

    public Blackjack(Context ctx, Activity a, RecyclerView rvInput){
        fieldContext = ctx;
        fieldRecyclerView = rvInput;
        fieldActivity = a;

        tvDealerTotal = (TextView) fieldActivity.findViewById(R.id.idDealerTotal);
        tvPlayerTotal = (TextView) fieldActivity.findViewById(R.id.idPlayerTotal);
    }

    public void deal(){
        deck1 = new Deck();
        dPrint = new PrintDealer(fieldActivity, fieldRecyclerView);
        pPrint = new PrintPlayer(fieldRecyclerView);

        // Dealer draw:
        dealerCards = new Cards[4];

        // "Fake" draw:
        dealerCards[0] = deck1.drawCard(0);
        // Dealer draw:
        dealerCards[1] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
        dealerCards[2] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
        iDealerTotal = dealerCards[1].getBlackJackValue();
        iDealerCards = 2;

        dPrint.Cards(1, 2, dealerCards);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, 3000);

        // Player draw:
        playerCards = new ArrayList<>();

        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        iPlayerTotal = playerCards.get(0).getBlackJackValue() + playerCards.get(1).getBlackJackValue();
        iPlayerCards = 2;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pPrint.Cards(playerCards);
            }
        }, 4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
            }
        }, 5000);
    }

    public void stand(View v){
        dPrint.Cards(2, 2, dealerCards);

        iDealerTotal = dealerCards[1].getBlackJackValue() + dealerCards[2].getBlackJackValue();
        lessThan16();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, 1000);

        //check win/lose
    }

    public void hit(View v){
        dPrint.Cards(2, 2, dealerCards);

        iDealerTotal = dealerCards[1].getBlackJackValue() + dealerCards[2].getBlackJackValue();
        lessThan16();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, 1000);

        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        iPlayerCards++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pPrint.Cards(playerCards);
                iPlayerTotal += playerCards.get(iPlayerCards-1).getBlackJackValue();
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
            }
        }, 2000);

        //check win/lose
    }

    public static int getNumDealerCards(){
        return iDealerCards;
    }

    public void lessThan16(){

        if (iDealerTotal < 16) {
            dealerCards[3] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
            dPrint.Cards(3, 3, dealerCards);
            iDealerTotal += dealerCards[3].getBlackJackValue();
            iDealerCards = 3;
        }
    }

    public static void main(String[] args) throws IOException {

    }
}
