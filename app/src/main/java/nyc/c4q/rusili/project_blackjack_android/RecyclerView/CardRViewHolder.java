package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.R;

public class CardRViewHolder extends RecyclerView.ViewHolder {
    private Context ctxt;
    private View v;
    private TextView tvPlayerBackgroundNum, tvPlayerBackgroundNum2, tvPlayerNum1, tvPlayerNum2;
    private ImageView ivPlayerSuit1, ivPlayerSuit2, ivOverlay;

    public CardRViewHolder(ViewGroup parent){
        super(inflateView(parent));
        this.ctxt = parent.getContext();
        v = itemView;
        this.findView(v);
    }

    private static View inflateView(ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.card_template, parent, false);
    }

    public void bind(Cards holderCards) {
        int grey = ContextCompat.getColor(ctxt, R.color.my_dark_grey);
        int red = ContextCompat.getColor(ctxt, R.color.my_dark_red);

        if (holderCards.getCardValue().equals("10")) {
            tvPlayerBackgroundNum2.setText("0");
        }
        ivOverlay = (ImageView) v.findViewById(R.id.idCardOverlay);
        ivOverlay.setImageResource(R.drawable.card_overlay);
        tvPlayerBackgroundNum.setText(holderCards.getCardValue());
        tvPlayerNum1.setText(holderCards.getCardValue());
        tvPlayerNum2.setText(holderCards.getCardValue());
        if (holderCards.getCardSuit().toString().equals("Hearts")) {
            ivPlayerSuit1.setImageResource(R.drawable.card_suit_hearts); ivPlayerSuit2.setImageResource(R.drawable.card_suit_hearts);
            tvPlayerNum1.setTextColor(red); tvPlayerNum2.setTextColor(red);
            ivPlayerSuit1.setColorFilter(red); ivPlayerSuit2.setColorFilter(red);
        } else if (holderCards.getCardSuit().toString().equals("Diamonds")) {
            ivPlayerSuit1.setImageResource(R.drawable.card_suit_diamonds); ivPlayerSuit2.setImageResource(R.drawable.card_suit_diamonds);
            tvPlayerNum1.setTextColor(red); tvPlayerNum2.setTextColor(red);
            ivPlayerSuit1.setColorFilter(red); ivPlayerSuit2.setColorFilter(red);
        } else if (holderCards.getCardSuit().toString().equals("Clubs")) {
            ivPlayerSuit1.setImageResource(R.drawable.card_suit_clubs); ivPlayerSuit2.setImageResource(R.drawable.card_suit_clubs);
        } else if (holderCards.getCardSuit().toString().equals("Spades")) {
            ivPlayerSuit1.setImageResource(R.drawable.card_suit_spades); ivPlayerSuit2.setImageResource(R.drawable.card_suit_spades);
        }

        Animation player_card = AnimationUtils.loadAnimation(ctxt, R.anim.player_offscreen);
        v.startAnimation(player_card);
    }

    private void findView(View v){
        tvPlayerBackgroundNum = (TextView) v.findViewById(R.id.idtvBackNum);
        tvPlayerBackgroundNum2 = (TextView) v.findViewById(R.id.idtvBackNum2);
        tvPlayerNum1 = (TextView) v.findViewById(R.id.idtvLeftNum);
        tvPlayerNum2 = (TextView) v.findViewById(R.id.idtvRightNum);
        ivPlayerSuit1 = (ImageView) v.findViewById(R.id.idivLeftSuit);
        ivPlayerSuit2 = (ImageView) v.findViewById(R.id.idivRightSuit);
        ivOverlay = (ImageView) v.findViewById(R.id.idCardOverlay);
    }
}
