package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cc.hiifong.androidwork.R;

public class SpinnerActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner = findViewById(R.id.sp);
        String[] places = {
                "Australia",
                "Switzerland",
                "China",
                "American"
        };
        ArrayAdapter<String> adapterPlace = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, places
        );
        adapterPlace.setDropDownViewResource(R.layout.spinner_txt_layout);
        spinner.setAdapter(adapterPlace);
        spinner.setOnItemSelectedListener(listener);
    }
    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(adapterView.getContext(),adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(adapterView.getContext(), "Nothing Selected!", Toast.LENGTH_SHORT).show();
        }
    };
}