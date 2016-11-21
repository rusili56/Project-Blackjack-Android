package nyc.c4q.rusili.project_blackjack_android.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rusili on 10/28/16.
 */

public class Storage {
    private Activity fieldActivity;
    SharedPreferences sharedPrefs;

    public Storage(Activity a) {
        this.fieldActivity = a;
        sharedPrefs = fieldActivity.getSharedPreferences("BlackjackPrefs", Context.MODE_PRIVATE);
    }

    public int getDealerWins() {
        return sharedPrefs.getInt("DealerWins", 0);
    }

    public int getPlayerWins() {
        return sharedPrefs.getInt("PlayerWins", 0);
    }

    public int getTies() {
        return sharedPrefs.getInt("Ties", 0);
    }

    public void set(int dealer, int player, int ties) {
        android.content.SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("DealerWins", dealer);
        editor.putInt("PlayerWins", player);
        editor.putInt("Ties", ties);
        editor.commit();
    }
}
