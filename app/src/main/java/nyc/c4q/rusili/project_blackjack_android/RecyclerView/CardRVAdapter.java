package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;

public class CardRVAdapter extends RecyclerView.Adapter {
    List<Cards> alCards = new ArrayList<>();

    public CardRVAdapter(ArrayList<Cards> alInput) {
        this.alCards = alInput;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardRViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardRViewHolder cardHolder = (CardRViewHolder) holder;
        Cards holderCards = alCards.get(position);
        cardHolder.bind(holderCards);

        //put animation here. use position to determine timing
    }

    @Override
    public int getItemCount() {
        return alCards.size();
    }

}
