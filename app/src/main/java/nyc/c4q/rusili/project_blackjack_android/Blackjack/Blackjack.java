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
import nyc.c4q.rusili.project_blackjack_android.Visuals.Draw;

public class Blackjack extends AppCompatActivity{

    static Random randomGenerator = new Random();
    private static Draw draw;
    Context fieldContext; static Activity fieldActivity; RecyclerView fieldRecyclerView;
    static TextView tvDealerTotal, tvPlayerTotal;
    private static Deck deck1; private static Cards[] dealerCards; private static ArrayList<Cards> playerCards;
    static int iDealerTotal, iPlayerTotal, iPlayerCards, iDealerCards;

    public Blackjack(Context ctx, Activity a, RecyclerView rvInput){
        fieldContext = ctx;
        fieldRecyclerView = rvInput;

        tvDealerTotal = (TextView) fieldActivity.findViewById(R.id.idDealerTotal);
        tvPlayerTotal = (TextView) fieldActivity.findViewById(R.id.idPlayerTotal);
    }

    public void deal(){
        deck1 = new Deck();
        // Dealer draw:
        dealerCards = new Cards[4];

        // "Fake" draw:
        dealerCards[0] = deck1.drawCard(0);
        // Dealer draw:
        dealerCards[1] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
        dealerCards[2] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
        iDealerTotal = dealerCards[1].getBlackJackValue();
        iDealerCards = 2;

        draw = new Draw(fieldActivity, fieldRecyclerView);
        draw.printdCard(1, 2, dealerCards);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, 2000);

        // Player draw:
        playerCards = new ArrayList<>();

        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        iPlayerTotal = playerCards.get(0).getBlackJackValue() + playerCards.get(1).getBlackJackValue();
        iPlayerCards = 2;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                draw.printpCard(2, playerCards);
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
            }
        }, 3000);
    }

    public void stand(View v){
        draw.printdCard(2, 2, dealerCards);
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

    public static void hit(View v){
        draw.printdCard(2, 2, dealerCards);
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
                draw.printpCard(iPlayerCards, playerCards);
                iPlayerTotal += playerCards.get(iPlayerCards-1).getBlackJackValue();
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
            }
        }, 2000);

        //check win/lose
    }

    public static int getNumDealerCards(){
        return iDealerCards;
    }

    public static void lessThan16(){
        if (iDealerTotal < 16) {
            dealerCards[3] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
            draw.printdCard(3, 3, dealerCards);
            iDealerTotal += dealerCards[3].getBlackJackValue();
            iDealerCards = 3;
        }
    }

    public static void main(String[] args) throws IOException {

        /*
        String sInput = "";
        boolean loop = true;


        while (loop) {



            // Player draw:
            ArrayList<Cards> playerCards = new ArrayList<>();

            playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
            playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
            int iPlayerTotal = playerCards.get(0).getBlackJackValue() + playerCards.get(1).getBlackJackValue();
            iPlayerCards = 2;

            draw.printCard(1, playerCards);

            Print.pln("\n" + "Please press Enter:");
            Shared.i();

            temp = dealerCards[2];
            dealerCards[2] = dealerCards[0];
            //draw.printCard(2, dealerCards); // hole Card is face down
            //draw.printCard(2, playerCards);
            dealerCards[2] = temp;

            // while loop
            while (iPlayerTotal < 21 && iDealerTotal < 21) {

                Print.pln("What do you want to do? Stand or Hit?:" + "\n");
                sInput = Shared.i();
                if (sInput.equalsIgnoreCase("stand")) {
                    if (iDealerTotal < 16) {
                        dealerCards[3] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
                        //draw.printCard(3, dealerCards);
                        iDealerTotal += dealerCards[3].getBlackJackValue();
                    } else {
                        //draw.printCard(2, dealerCards);
                    }
                    draw.printCard(iPlayerCards, playerCards);
                    break;
                } else if (sInput.equalsIgnoreCase("hit")) {
                    playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
                    iPlayerCards++;
                    iPlayerTotal += playerCards.get(iPlayerCards - 1).getBlackJackValue();
                    if (iDealerTotal < 16) {
                        dealerCards[3] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
                        //draw.printCard(3, dealerCards);
                        iDealerTotal += dealerCards[3].getBlackJackValue();
                    } else {
                        //draw.printCard(2, dealerCards);
                    }
                    draw.printCard(iPlayerCards, playerCards);
                } else {
                    Print.pln("Please choose Stand or Hit" + "\n");
                }

            }

            Print.pln("Dealer: " + iDealerTotal + " || Player: " + iPlayerTotal + "\n");

            if (iDealerTotal > 21 || iPlayerTotal == 21) {
                Print.pln("Player wins!" + "\n");
                iPlayerWins++;
            } else if (iPlayerTotal > 21 || iDealerTotal == 21) {
                Print.pln("Dealer wins!" + "\n");
                iDealerWins++;
            } else {
                if (iPlayerTotal > iDealerTotal) {
                    Print.pln("Player wins!" + "\n");
                    iPlayerWins++;
                } else if (iPlayerTotal < iDealerTotal) {
                    Print.pln("Dealer wins!" + "\n");
                    iDealerWins++;
                } else {
                    Print.pln("Tie!" + "\n");
                    iTies++;
                }
            }

            Print.pln("Would you like to play again? (y/n:)");
            Print.p("\n");
            sInput = Shared.i();

            if (sInput.equalsIgnoreCase("y") || sInput.equalsIgnoreCase("yes")) {
                Print.pln("Player: " + iPlayerWins + " || Dealer: " + iDealerWins + " || Ties: " + iTies + "\n");
                loop = true;
            } else if ((sInput.equalsIgnoreCase("n") || sInput.equalsIgnoreCase("no"))) {
                Print.pln("Thanks for playing!");
                loop = false;
            }
        }
        */

    }
}
