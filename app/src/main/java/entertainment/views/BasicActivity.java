package entertainment.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import entertainment.simplyandroid.R;

public class BasicActivity extends AppCompatActivity {
    private static final String TAG = BasicActivity.class.getSimpleName();
    private CustumView custumView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        custumView = (CustumView)findViewById(R.id.custumView);

        Button swapColorButton = findViewById(R.id.btnChangeColor);
        swapColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              custumView.swapColor();
            }
        });
    }
}
