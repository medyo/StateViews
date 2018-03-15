package sakout.mehdi.StateViews.Samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sakout.mehdi.StateViews.StateView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    StateView mStatusPage;
    Button mLoading, mError, mCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoading = findViewById(R.id.button_loading);
        mError = findViewById(R.id.button_error);
        mCustom = findViewById(R.id.button_custom);

        mLoading.setOnClickListener(this);
        mError.setOnClickListener(this);
        mCustom.setOnClickListener(this);

        mStatusPage = findViewById(R.id.status_page);
        mStatusPage.setOnStateButtonClicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_loading) {
            mStatusPage.displayLoadingState();
        } else if (view.getId() == R.id.button_error) {
            mStatusPage.displayState("error");
        } else if (view.getId() == R.id.button_custom) {
            mStatusPage.displayState("archive");
        }
    }
}
