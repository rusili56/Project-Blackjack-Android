package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    int iAnimLength;

    public CardRVAdapter(ArrayList<Cards> alInput, Context c) {
        this.alCards = alInput;
        fContext = c;
        iAnimLength = fContext.getResources().getInteger(R.integer.dealer_anim_length);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardRViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            final CardRViewHolder cardHolder = (CardRViewHolder) holder;
            Cards holderCards = alCards.get(position);
            cardHolder.bind(holderCards);

            animate(cardHolder);
            }
        }, iAnimLength * (position+1));
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
