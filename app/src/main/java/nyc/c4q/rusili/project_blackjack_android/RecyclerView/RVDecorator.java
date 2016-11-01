package nyc.c4q.rusili.project_blackjack_android.RecyclerView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by rusili on 10/25/16.
 */

public class RVDecorator extends RecyclerView.ItemDecoration {

    private int iOverlap = 0;

    public RVDecorator(int iInput){
        this.iOverlap = iInput;
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == 0) {
            return; }
        outRect.left = iOverlap;
    }
}
