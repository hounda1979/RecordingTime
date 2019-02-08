package tw.hd.com.recodtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView = findViewById(R.id.about_text);
        TextView abouthb = findViewById(R.id.abouthb_text);
        String str = getResources().getString(R.string.about);
        String strhb = getResources().getString(R.string.hb);
        textView.setText(str);
        abouthb.setText(strhb);
        ImageView cancel = findViewById(R.id.imagecancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
