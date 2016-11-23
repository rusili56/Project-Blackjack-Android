package nyc.c4q.rusili.project_blackjack_android.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Blackjack;
import nyc.c4q.rusili.project_blackjack_android.Fragments.EndFragment;
import nyc.c4q.rusili.project_blackjack_android.R;
import nyc.c4q.rusili.project_blackjack_android.Utility.Storage;

public class cMain extends AppCompatActivity{

    private Blackjack Game;
    private EndFragment endFrag = new EndFragment();
    private Storage storage;
    private RecyclerView rvPlayer; private RecyclerView.Adapter rvAdapter; private RecyclerView.LayoutManager rvLayoutManager;
    private static int iDealerTotal, iPlayerTotal;
    private static int iDealerWins, iPlayerWins, iTies = 0;
    private TextView tvPlayerScore, tvDealerScore;
    private ImageButton ibHit, ibStand;
    public Bundle bundle = new Bundle();
    private int iAnimLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        iAnimLength = getResources().getInteger(R.integer.dealer_anim_length);
        tvDealerScore = (TextView) findViewById(R.id.idDealerTotal);
        tvPlayerScore = (TextView) findViewById(R.id.idPlayerTotal);
        ibHit = (ImageButton) findViewById(R.id.idibHit);
        ibStand = (ImageButton) findViewById(R.id.idibStand);
        ibHit.setVisibility(View.GONE);
        ibStand.setVisibility(View.GONE);

        storage = new Storage(this);
        iDealerWins = storage.getDealerWins();
        iPlayerWins = storage.getPlayerWins();
        iTies = storage.getTies();

        rvPlayer = (RecyclerView) findViewById(R.id.idPlayerRecyclerView);
        rvPlayer.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        rvPlayer.setItemAnimator(itemAnimator);
        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvPlayer.setLayoutManager(rvLayoutManager);
        rvPlayer.setAdapter(rvAdapter);

        Game = new Blackjack(this, this, rvPlayer);
        Game.deal();
    }

    public void onHit(View view) {
        Toast toast = Toast.makeText(this, "Hit", Toast.LENGTH_SHORT); toast.show();
        Game.hit(view);

        if (iDealerTotal > 21 || iPlayerTotal == 21) {
            iPlayerWins++;
            bundle.putString("result", "PLAYER WINS!");
        } else if (iPlayerTotal > 21 || iDealerTotal == 21) {
            iDealerWins++;
            bundle.putString("result", "DEALER WINS!");
        }
        checkWinner();

        this.toEnd(Blackjack.getNumDealerCards());
    }

    public void onStand(View view) {
        Toast toast = Toast.makeText(this, "Stand", Toast.LENGTH_SHORT); toast.show();
        Game.stand(view);

        iDealerTotal = Integer.parseInt(tvDealerScore.getText().toString());
        iPlayerTotal = Integer.parseInt(tvPlayerScore.getText().toString());

        checkWinner();
        this.toEnd(Blackjack.getNumDealerCards());
    }

    public void toEnd(int iNumofCards) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                endFrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.idFullLayout, endFrag).commit();
            }
        }, iAnimLength * (iNumofCards + 1));
    }

    public void onClickQuit(View view) {
        this.finishAffinity();
    }

    public void onClickPlayAgain(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().remove(endFrag).commit();
                recreate();
            }
        }, iAnimLength);
    }

    public void checkWinner() {
        if (iDealerTotal > 21 || iPlayerTotal == 21) {
            iPlayerWins++;
            bundle.putString("result", "PLAYER WINS!");
        } else if (iPlayerTotal > 21 || iDealerTotal == 21) {
            iDealerWins++;
            bundle.putString("result", "DEALER WINS!");
        } else {
            if (iPlayerTotal > iDealerTotal) {
                iPlayerWins++;
                bundle.putString("result", "PLAYER WINS!");
            } else if (iPlayerTotal < iDealerTotal) {
                iDealerWins++;
                bundle.putString("result", "DEALER WINS!");
            } else {
                iTies++;
                bundle.putString("result", "TIE!");
            }
        }
    }

    @Override
    protected void onStop() {
        storage.set(iDealerTotal, iPlayerTotal, iTies);
        super.onStop();
    }
}
