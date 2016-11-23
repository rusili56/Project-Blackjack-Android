package nyc.c4q.rusili.project_blackjack_android.Blackjack;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import nyc.c4q.rusili.project_blackjack_android.R;
import nyc.c4q.rusili.project_blackjack_android.Visuals.PrintDealer;
import nyc.c4q.rusili.project_blackjack_android.Visuals.PrintPlayer;

public class Blackjack extends AppCompatActivity {

    private static Random randomGenerator = new Random();
    private static Context fContext;
    private static Activity fActivity;
    private static RecyclerView fRecyclerView;
    private static TextView tvDealerTotal, tvPlayerTotal;
    private static ImageButton ibHit, ibStand;
    private static Deck deck1;
    private static Cards[] dealerCards;
    private static ArrayList<Cards> playerCards;
    private static int iDealerTotal, iPlayerTotal, iPlayerCards, iDealerCards, iAnimLength;

    PrintDealer dPrint;
    PrintPlayer pPrint;

    public Blackjack(Context cInput, Activity aInput, RecyclerView rvInput) {
        fContext = cInput;
        fRecyclerView = rvInput;
        fActivity = aInput;

        tvDealerTotal = (TextView) fActivity.findViewById(R.id.idDealerTotal);
        tvPlayerTotal = (TextView) fActivity.findViewById(R.id.idPlayerTotal);
        ibHit = (ImageButton) fActivity.findViewById(R.id.idibHit);
        ibStand = (ImageButton) fActivity.findViewById(R.id.idibStand);
        iAnimLength = fActivity.getResources().getInteger(R.integer.dealer_anim_length);
    }

    public void deal() {
        deck1 = new Deck();
        dPrint = new PrintDealer(fActivity, R.integer.dealer_anim_length);
        pPrint = new PrintPlayer(fRecyclerView, fContext);

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
        }, iAnimLength * 3);

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
        }, iAnimLength * 3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ibHit.setVisibility(View.VISIBLE);
                        ibStand.setVisibility(View.VISIBLE);
                    }
                }, iAnimLength * 2);
            }
        }, iAnimLength * 4);
    }

    public void stand(View v) {
        dPrint.clear(fActivity);
        iDealerTotal = dealerCards[1].getBlackJackValue() + dealerCards[2].getBlackJackValue();
        lessThan16();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, iAnimLength);

        //check win/lose
    }

    public void hit(View v) {
        dPrint.clear(fActivity);
        dPrint.Cards(2, 2, dealerCards);

        lessThan16();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvDealerTotal.setText(Integer.toString(iDealerTotal));
            }
        }, iAnimLength);

        playerCards.add(deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn())));
        iPlayerCards++;
        iPlayerTotal += playerCards.get(iPlayerCards - 1).getBlackJackValue();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pPrint.Cards(playerCards);
                tvPlayerTotal.setText(Integer.toString(iPlayerTotal));
            }
        }, iAnimLength * 2);

        //check win/lose
    }

    public static int getNumDealerCards() {
        return iDealerCards;
    }

    public void lessThan16() {

        if (iDealerTotal < 16) {
            dealerCards[3] = deck1.drawCard(randomGenerator.nextInt(52 - deck1.cardsDrawn()));
            dPrint.Cards(2, 3, dealerCards);
            iDealerTotal += dealerCards[3].getBlackJackValue();
            iDealerCards = 3;
        } else {
            dPrint.Cards(2, 2, dealerCards);
            iDealerTotal = dealerCards[1].getBlackJackValue() + dealerCards[2].getBlackJackValue();
        }
    }
}
