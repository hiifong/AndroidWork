package cc.hiifong.androidwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.module.Member;

public class MemberAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Member> memberList;

    public MemberAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);

        memberList = new ArrayList<>();

        memberList.add(new Member("中国", R.drawable.cn));
        memberList.add(new Member("美国", R.drawable.us));
        memberList.add(new Member("英国", R.drawable.en));
        memberList.add(new Member("瑞士", R.drawable.sw));
    }

    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int i) {
        return memberList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.list, viewGroup, false);
        Member member = memberList.get(i);

        ImageView imageView = view.findViewById(R.id.list_img);
        TextView textView = view.findViewById(R.id.list_tv);

        imageView.setImageResource(member.getImgId());
        textView.setText(member.getName());
        return view;
    }
}
