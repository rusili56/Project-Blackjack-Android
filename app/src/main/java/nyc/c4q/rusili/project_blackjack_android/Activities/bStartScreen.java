package nyc.c4q.rusili.project_blackjack_android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nyc.c4q.rusili.project_blackjack_android.Fragments.InstructionsFragment;
import nyc.c4q.rusili.project_blackjack_android.R;

public class bStartScreen extends AppCompatActivity {
    InstructionsFragment instructionsFragment = new InstructionsFragment();
    boolean bFragOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
    }

    public void onClickPlay(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toTable = new Intent(bStartScreen.this, cMain.class);
                startActivity(toTable);
            }
        }, 500);
    }

    public void onClickInstructions(View view) {
        if (!bFragOpen) {
            getSupportFragmentManager().beginTransaction().add(R.id.idStartFrame, instructionsFragment).commit();
            bFragOpen = true;
        } else {
            getSupportFragmentManager().beginTransaction().remove(instructionsFragment).commit();
            bFragOpen = false;
        }
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().remove(instructionsFragment).commit();
    }
}
