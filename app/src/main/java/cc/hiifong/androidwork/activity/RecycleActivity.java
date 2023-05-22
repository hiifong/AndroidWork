package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.module.Member;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;

    private class MemberAdapter extends RecyclerView.Adapter<RecycleActivity.ViewHolder>{
        private Context context;
        private LayoutInflater layoutInflater;
        private List<Member> memberList;

        public MemberAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);

            memberList = new ArrayList<>();

            memberList.add(new Member("中国", R.drawable.cn));
            memberList.add(new Member("美国", R.drawable.us));
            memberList.add(new Member("英国", R.drawable.en));
            memberList.add(new Member("瑞士", R.drawable.sw));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.rcv_cv_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Member member = memberList.get(position);
            holder.getImageView().setImageResource(member.getImgId());
            holder.getTextView().setText(member.getName());
        }

        @Override
        public int getItemCount() {
            return memberList.size();
        }
    }

   public class ViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       private TextView textView;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.cv_iv);
           textView = itemView.findViewById(R.id.cv_tv);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

               }
           });
       }

       public ImageView getImageView() {
           return imageView;
       }

       public TextView getTextView() {
           return textView;
       }
   }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView = findViewById(R.id.rcv);
        textView = findViewById(R.id.tvr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MemberAdapter(this));
    }
}