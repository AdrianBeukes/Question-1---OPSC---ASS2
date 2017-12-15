package adrianbeukes.question1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //gets information from MenuMain activity to display on create
        String Results = getIntent().getStringExtra("results");
        TextView edtResults = (TextView) findViewById(R.id.txtReport);
        edtResults.setText(Results);
    }
}
