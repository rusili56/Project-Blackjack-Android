package nyc.c4q.rusili.project_blackjack_android.Visuals;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.R;

/**
 * Created by rusili on 11/6/16.
 */

public class PrintDealer {

    TextView tvDealerBackgroundNum, tvDealerBackgroundNum2, tvDealerNum1, tvDealerNum2;
    ImageView ivDealerSuit1, ivDealerSuit2, ivOverlay;
    private Activity fActivity;
    RecyclerView fRecyclerView;
    private int round = 0;

    public PrintDealer(Activity aInput, RecyclerView rvInput) {
        fActivity = aInput;
        fRecyclerView = rvInput;
    }

    public void Cards(int iStart, int iEnd, Cards[] inputCard) {
        int iCardstoDraw = 1;
        if (iEnd == 3) iCardstoDraw = 2;
        FrameLayout frame = (FrameLayout) fActivity.findViewById(R.id.idFullLayout);

        for (int i = iStart; i <= iEnd; i++) {
            String frameID = "idDealerFrame" + (i - 1);
            int resID = fActivity.getResources().getIdentifier(frameID, "id", fActivity.getPackageName());
            final CardView flTemp = (CardView) fActivity.findViewById(resID);
            LayoutInflater li = LayoutInflater.from(fActivity);
            final View v = li.inflate(R.layout.card_template, frame, false);
            this.findView(v);

            final float scale = fActivity.getResources().getDisplayMetrics().density;
            int height = (int) (160 * scale + 0.5f);
            int width = (int) (120 * scale + 0.5f);
            final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            flTemp.setVisibility(View.GONE);

            if (round == 0 && i == 2) {
                tvDealerBackgroundNum.setText("X");
                cardAnimation(iCardstoDraw, flTemp, v, layoutParams);
                iCardstoDraw++;
                round++;
            } else {
                this.setWidgets(inputCard, i);
                cardAnimation(iCardstoDraw, flTemp, v, layoutParams);
                iCardstoDraw++;
            }
        }
    }

    private void findView(View v) {
        tvDealerBackgroundNum = (TextView) v.findViewById(R.id.idtvBackNum);
        tvDealerBackgroundNum2 = (TextView) v.findViewById(R.id.idtvBackNum2);
        tvDealerNum1 = (TextView) v.findViewById(R.id.idtvLeftNum);
        tvDealerNum2 = (TextView) v.findViewById(R.id.idtvRightNum);
        ivDealerSuit1 = (ImageView) v.findViewById(R.id.idivLeftSuit);
        ivDealerSuit2 = (ImageView) v.findViewById(R.id.idivRightSuit);
        ivOverlay = (ImageView) v.findViewById(R.id.idCardOverlay);
    }

    private void setWidgets(Cards[] inputCard, int i) {
        int red = ContextCompat.getColor(fActivity, R.color.my_dark_red);

        if (inputCard[i].getsCardValue().equals("10")) {
            tvDealerBackgroundNum2.setText("0");
        }
        ivOverlay.setImageResource(R.drawable.card_overlay);
        tvDealerBackgroundNum.setText(inputCard[i].getsCardValue());
        tvDealerNum1.setText(inputCard[i].getsCardValue());
        tvDealerNum2.setText(inputCard[i].getsCardValue());
        if (inputCard[i].getCardSuit().toString().equals("Hearts")) {
            ivDealerSuit1.setImageResource(R.drawable.card_suit_hearts);
            ivDealerSuit2.setImageResource(R.drawable.card_suit_hearts);
            tvDealerNum1.setTextColor(red);
            tvDealerNum2.setTextColor(red);
            ivDealerSuit1.setColorFilter(red);
            ivDealerSuit2.setColorFilter(red);
        } else if (inputCard[i].getCardSuit().toString().equals("Diamonds")) {
            ivDealerSuit1.setImageResource(R.drawable.card_suit_diamonds);
            ivDealerSuit2.setImageResource(R.drawable.card_suit_diamonds);
            tvDealerNum1.setTextColor(red);
            tvDealerNum2.setTextColor(red);
            ivDealerSuit1.setColorFilter(red);
            ivDealerSuit2.setColorFilter(red);
        } else if (inputCard[i].getCardSuit().toString().equals("Clubs")) {
            ivDealerSuit1.setImageResource(R.drawable.card_suit_clubs);
            ivDealerSuit2.setImageResource(R.drawable.card_suit_clubs);
        } else if (inputCard[i].getCardSuit().toString().equals("Spades")) {
            ivDealerSuit1.setImageResource(R.drawable.card_suit_spades);
            ivDealerSuit2.setImageResource(R.drawable.card_suit_spades);
        }
    }

    public void clear(int i, Activity fieldActivity) {
        View v = fieldActivity.findViewById(R.id.idDealerFrame1);
        tvDealerBackgroundNum = (TextView) v.findViewById(R.id.idtvBackNum);
        tvDealerBackgroundNum.setText("");
    }

    public void cardAnimation(int iInput, final CardView cvInput, final View v, final FrameLayout.LayoutParams layoutParams){
        final Animation player_card = AnimationUtils.loadAnimation(fActivity, R.anim.dealer_offscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player_card.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        cvInput.addView(v, layoutParams);
                        cvInput.setVisibility(View.VISIBLE);
                    }
                });
                cvInput.startAnimation(player_card);
            }
        }, 1000 * iInput);
        cvInput.startAnimation(player_card);
    }

}
