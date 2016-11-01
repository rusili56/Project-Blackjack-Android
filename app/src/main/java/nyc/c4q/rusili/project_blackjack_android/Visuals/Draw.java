package nyc.c4q.rusili.project_blackjack_android.Visuals;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nyc.c4q.rusili.project_blackjack_android.Activities.MainActivity;
import nyc.c4q.rusili.project_blackjack_android.Activities.StartScreen;
import nyc.c4q.rusili.project_blackjack_android.Blackjack.Cards;
import nyc.c4q.rusili.project_blackjack_android.R;
import nyc.c4q.rusili.project_blackjack_android.RecyclerView.CardRVAdapter;
import nyc.c4q.rusili.project_blackjack_android.RecyclerView.RVDecorator;

public class Draw {

    TextView tvDealerBackgroundNum, tvDealerBackgroundNum2, tvDealerNum1, tvDealerNum2;
    ImageView ivDealerSuit1, ivDealerSuit2;
    private Activity fActivity; RecyclerView fRecyclerView;
    private int round = 0;

    public Draw(Activity aInput, RecyclerView rvInput) {
        fActivity = aInput;
        fRecyclerView = rvInput;
    }

    // Dealer Draw: Array
    public void printdCard(int iStart, int iEnd, Cards[] inputCard) {
        int iCardstoDraw = 1;
        if (iEnd == 3) iCardstoDraw = 2;
        for (int i = iStart; i <= iEnd; i++) {
            String frameID = "idDealerFrame" + (i-1);
            int resID = fActivity.getResources().getIdentifier(frameID, "id", fActivity.getPackageName());
            final FrameLayout flTemp = (FrameLayout) fActivity.findViewById(resID);
            LayoutInflater li = LayoutInflater.from(fActivity);
            final View v = li.inflate(R.layout.card_template,null,false);
                tvDealerBackgroundNum = (TextView) v.findViewById(R.id.idtvBackNum);
                tvDealerBackgroundNum2 = (TextView) v.findViewById(R.id.idtvBackNum2);
                tvDealerNum1 = (TextView) v.findViewById(R.id.idtvLeftNum);
                tvDealerNum2 = (TextView) v.findViewById(R.id.idtvRightNum);
                ivDealerSuit1 = (ImageView) v.findViewById(R.id.idivLeftSuit);
                ivDealerSuit2 = (ImageView) v.findViewById(R.id.idivRightSuit);
            final float scale = fActivity.getResources().getDisplayMetrics().density;
            int height = (int) (160 * scale + 0.5f);
            int width = (int) (120 * scale + 0.5f);
            final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            if (round == 0 && i == 2){
                tvDealerBackgroundNum.setText("X");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flTemp.addView(v, layoutParams);
                    }
                }, 1000*iCardstoDraw);
                iCardstoDraw++;
                round++;
            } else {
                if (inputCard[i].getCardValue().equals("10")) {
                    tvDealerBackgroundNum2.setText("0");
                }
                tvDealerBackgroundNum.setText(inputCard[i].getCardValue());
                tvDealerNum1.setText(inputCard[i].getCardValue());
                tvDealerNum2.setText(inputCard[i].getCardValue());
                if (inputCard[i].getCardSuit().equals("")) {
                    //ivDealerSuit1.setImageResource();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flTemp.addView(v);
                    }
                }, 1000*iCardstoDraw);
                iCardstoDraw++;
            }
        }
    }

    // Player Draw: ArrayList
    public void printpCard(int iInput, final ArrayList<Cards> inputCard) {
        CardRVAdapter pCardAdapter = new CardRVAdapter(inputCard);
        RVDecorator decorator = new RVDecorator(-50);
        fRecyclerView.addItemDecoration(decorator);
        fRecyclerView.setAdapter(pCardAdapter);
        round++;
    }

}