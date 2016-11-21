package nyc.c4q.rusili.project_blackjack_android.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.rusili.project_blackjack_android.R;

/**
 * Created by rusili on 10/26/16.
 */

public class EndFragment extends Fragment {
    private TextView tvResult;
    private String result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        result = getArguments().getString("result");
        return inflater.inflate(R.layout.end_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tvResult = (TextView) view.findViewById(R.id.idEndFragTextOverlay);
        tvResult.setText(result);
        super.onViewCreated(view, savedInstanceState);
    }
}
