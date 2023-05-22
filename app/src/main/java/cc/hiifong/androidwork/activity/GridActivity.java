package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import cc.hiifong.androidwork.MainActivity;
import cc.hiifong.androidwork.R;

public class GridActivity extends AppCompatActivity {
    private GridView gridView;
    ImageSwitcher imageSwitcher;

    int imgId[] = {
            R.drawable.panda1,R.drawable.panda2,R.drawable.panda3,
            R.drawable.panda4,R.drawable.panda5,R.drawable.panda6,
            R.drawable.panda7,R.drawable.panda8,R.drawable.panda9,
    };

    class ImageAdapter extends BaseAdapter{
        LayoutInflater layoutInflater;
        Context context;
        public ImageAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return imgId.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = layoutInflater.inflate(R.layout.gdv_item, viewGroup, false);
            ImageView imageView = (ImageView) view;
            imageView.setImageResource(imgId[i]);
            return imageView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        gridView = findViewById(R.id.gdv);
        imageSwitcher  = findViewById(R.id.imsw);
        gridView.setAdapter(new ImageAdapter(this));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(GridActivity.this);
                return imageView;
            }
        });
        imageSwitcher.setInAnimation(this, android.R.anim.fade_in);
        imageSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        imageSwitcher.setImageResource(imgId[0]);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageSwitcher.setImageResource(imgId[i]);
            }
        });
    }
}