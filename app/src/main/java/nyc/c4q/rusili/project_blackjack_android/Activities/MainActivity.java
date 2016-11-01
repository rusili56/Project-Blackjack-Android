package nyc.c4q.rusili.project_blackjack_android.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.rusili.project_blackjack_android.Blackjack.Blackjack;
import nyc.c4q.rusili.project_blackjack_android.R;
import nyc.c4q.rusili.project_blackjack_android.Fragments.EndFragment;
import nyc.c4q.rusili.project_blackjack_android.Utility.Storage;

public class MainActivity extends AppCompatActivity {

    Blackjack Game;
    EndFragment endFrag = new EndFragment();
    private Storage storage;
    private RecyclerView rvPlayer;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    static int iDealerTotal, iPlayerTotal;
    static int iDealerWins, iPlayerWins, iTies = 0;
    private TextView tvPlayerScore, tvDealerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        storage = new Storage(this);

        iDealerWins = storage.getDealerWins();
        iPlayerWins = storage.getPlayerWins();
        iTies = storage.getTies();

        tvDealerScore = (TextView) findViewById(R.id.idDealerTotal); tvPlayerScore = (TextView) findViewById(R.id.idPlayerTotal);

        rvPlayer = (RecyclerView) findViewById(R.id.idPlayerRecyclerView);
        rvPlayer.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvPlayer.setLayoutManager(rvLayoutManager);
        rvPlayer.setAdapter(rvAdapter);

        Game = new Blackjack(this, this, rvPlayer);
        Game.deal();
    }

    public void onHit(View view) {
        Game.hit(view);
    }

    public void onStand(View view) {
        Game.stand(view);
        iDealerTotal = Integer.parseInt(tvDealerScore.getText().toString()); iPlayerTotal = Integer.parseInt(tvPlayerScore.getText().toString());

        if (iDealerTotal > 21 || iPlayerTotal == 21) {
            iPlayerWins++;
        } else if (iPlayerTotal > 21 || iDealerTotal == 21) {
            iDealerWins++;
        } else {
            if (iPlayerTotal > iDealerTotal) {
                iPlayerWins++;
            } else if (iPlayerTotal < iDealerTotal) {
                iDealerWins++;
            } else {
                iTies++;
            }
        }
        this.toEnd(Blackjack.getNumDealerCards());
    }

    public void toEnd(int iNumofCards){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().add(R.id.idFullLayout, endFrag).commit();
                }
        }, 1000 * (iNumofCards + 1));
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
        }, 500);
    }

    @Override
    protected void onStop() {
        storage.set(iDealerTotal, iPlayerTotal, iTies);
        super.onStop();
    }
}
