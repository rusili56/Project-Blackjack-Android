package nyc.c4q.rusili.project_blackjack_android.Visuals;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.RecyclerView.CardRVAdapter;
import nyc.c4q.rusili.project_blackjack_android.RecyclerView.RVDecorator;

/**
 * Created by rusili on 11/6/16.
 */

public class PrintPlayer {
    private RecyclerView fRecyclerView;

    public PrintPlayer(RecyclerView rvInput) {
        fRecyclerView = rvInput;
    }

    public void Cards(final ArrayList<Cards> inputCard) {
        CardRVAdapter pCardAdapter = new CardRVAdapter(inputCard);
        RVDecorator decorator = new RVDecorator(-50);
        fRecyclerView.addItemDecoration(decorator);
        fRecyclerView.setAdapter(pCardAdapter);
    }
}
