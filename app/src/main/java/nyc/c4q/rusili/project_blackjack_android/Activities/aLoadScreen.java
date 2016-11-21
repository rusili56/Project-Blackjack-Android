package nyc.c4q.rusili.project_blackjack_android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import nyc.c4q.rusili.project_blackjack_android.R;

public class aLoadScreen extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toStart = new Intent(aLoadScreen.this, bStartScreen.class);
                startActivity(toStart);
            }
        }, 3000);
    }

    public void onClickStart(View view) {
        Intent toStart = new Intent(aLoadScreen.this, bStartScreen.class);
        startActivity(toStart);
    }
}
