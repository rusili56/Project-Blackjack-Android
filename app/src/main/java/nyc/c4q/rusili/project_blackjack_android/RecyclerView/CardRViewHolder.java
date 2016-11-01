package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.R;

public class CardRViewHolder extends RecyclerView.ViewHolder {
    private View v;
    private TextView tvPlayerBackgroundNum, tvPlayerNum1, tvPlayerNum2;
    private ImageView ivPlayerSuit1, ivPlayerSuit2;

    public CardRViewHolder(ViewGroup parent){
        super(inflateView(parent));
        v = itemView;
        tvPlayerBackgroundNum = (TextView) v.findViewById(R.id.idtvBackNum);
        tvPlayerNum1 = (TextView) v.findViewById(R.id.idtvLeftNum);
        tvPlayerNum2 = (TextView) v.findViewById(R.id.idtvRightNum);
        ivPlayerSuit1 = (ImageView) v.findViewById(R.id.idivLeftSuit);
        ivPlayerSuit2 = (ImageView) v.findViewById(R.id.idivRightSuit);
    }

    private static View inflateView(ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.card_template, parent, false);
    }

    public void bind(Cards holderCards) {
        tvPlayerBackgroundNum.setText(holderCards.getCardValue());
        tvPlayerNum1.setText(holderCards.getCardValue());
        tvPlayerNum2.setText(holderCards.getCardValue());
        //ivPlayerSuit1.setImageResource();
        //ivPlayerSuit2.setImageResource();
    }
}
