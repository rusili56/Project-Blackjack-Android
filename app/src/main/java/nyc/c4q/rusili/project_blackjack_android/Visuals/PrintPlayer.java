package nyc.c4q.rusili.project_blackjack_android.Visuals;

import android.app.Activity;
import android.content.Context;
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
    private Context fContext;

    public PrintPlayer(RecyclerView rvInput, Context c) {
        this.fRecyclerView = rvInput;
        this.fContext = c;
    }

    public void Cards(final ArrayList<Cards> inputCard) {
        CardRVAdapter pCardAdapter = new CardRVAdapter(inputCard, fContext);
        RVDecorator decorator = new RVDecorator(-80);
        fRecyclerView.addItemDecoration(decorator);
        fRecyclerView.setAdapter(pCardAdapter);
    }
}
