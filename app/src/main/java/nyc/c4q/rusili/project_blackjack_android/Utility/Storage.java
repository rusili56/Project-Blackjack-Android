package nyc.c4q.rusili.project_blackjack_android.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rusili on 10/28/16.
 */

public class Storage {
    private Activity fActivity;
    SharedPreferences fSharedPrefs;

    public Storage(Activity a) {
        this.fActivity = a;
        fSharedPrefs = fActivity.getSharedPreferences("BlackjackPrefs", Context.MODE_PRIVATE);
    }

    public int getDealerWins() {
        return fSharedPrefs.getInt("DealerWins", 0);
    }

    public int getPlayerWins() {
        return fSharedPrefs.getInt("PlayerWins", 0);
    }

    public int getTies() {
        return fSharedPrefs.getInt("Ties", 0);
    }

    public void set(int dealer, int player, int ties) {
        android.content.SharedPreferences.Editor editor = fSharedPrefs.edit();
        editor.putInt("DealerWins", dealer);
        editor.putInt("PlayerWins", player);
        editor.putInt("Ties", ties);
        editor.commit();
    }
}
