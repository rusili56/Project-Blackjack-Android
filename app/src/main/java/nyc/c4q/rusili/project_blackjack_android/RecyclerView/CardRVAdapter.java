package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.R;

public class CardRVAdapter extends RecyclerView.Adapter {
    List<Cards> alCards = new ArrayList<>();
    static Context fContext;

    public CardRVAdapter(ArrayList<Cards> alInput, Context c) {
        this.alCards = alInput;
        fContext = c;
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

        animate(holder);
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(fContext, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        return alCards.size();
    }

}
