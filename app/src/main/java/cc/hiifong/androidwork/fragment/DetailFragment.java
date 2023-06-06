package cc.hiifong.androidwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.model.Resort;

public class DetailFragment extends Fragment {
    private int position;

    public DetailFragment(){}

    public DetailFragment(int position) {
        this.position = position;
    }

    public int getIndex() {
        return position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (container == null){
            return null;
        }
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ImageView imageView = view.findViewById(R.id.sv_img);
        imageView.setImageResource(Resort.COUNTRIES[position].getImgId());
        TextView textView = view.findViewById(R.id.sv_tv);
        textView.setText(Resort.COUNTRIES[position].getDesc());
        return view;
    }
}
