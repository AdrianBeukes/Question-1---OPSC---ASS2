package adrianbeukes.question1;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MenuMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
    }

    //*******************************************************************************************
    public String readDataAssetsFolder()
    {
        //read text file
        BufferedReader infoRead = null;
        String textData = "";
        String readLine; 
        try
        {
            infoRead = new BufferedReader(new InputStreamReader(openFileInput("Information.txt")));     //txt directory
            while((readLine = infoRead.readLine()) != null)                                             //check for all information till null
            {
                textData += readLine + "\n\n";
            }
            Toast.makeText(this, "File Loaded Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return textData;
    }

    //*******************************************************************************************
    public void saveInformation(String name, String contactNumber, String email, String information)
    {
        //saves information to file
        try
        {
            FileOutputStream outputStr = openFileOutput("Information.txt", MODE_APPEND);                //txt save directory
            OutputStreamWriter outputWri = new OutputStreamWriter(outputStr);

            //Write to text file
            outputWri.write(name + ", " + contactNumber + ", " + email + ", " + information + "\n");
            outputWri.flush();
            outputWri.close();

            //display that file saved
            Toast.makeText(getBaseContext(),"File has been saved", Toast.LENGTH_SHORT).show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //*******************************************************************************************
    public  void SaveButtonClick(View view)
    {
        //pulls information from activity and gives assigned values
        EditText nameEdt = (EditText) findViewById(R.id.edtName);
        EditText contactNumberEdt = (EditText) findViewById(R.id.edtNumber);
        EditText emailEdt = (EditText) findViewById(R.id.edtEmail);
        EditText informationEdt = (EditText) findViewById(R.id.edtInfo);

        assert nameEdt != null;
        assert contactNumberEdt != null;
        assert emailEdt != null;
        assert informationEdt != null;

        //saves all information as 1 string in txt file
        saveInformation(nameEdt.getText().toString(), contactNumberEdt.getText().toString(), emailEdt.getText().toString(), informationEdt.getText().toString());

        //clean up after save
        ((EditText) findViewById(R.id.edtName)).getText().clear();
        ((EditText) findViewById(R.id.edtNumber)).getText().clear();
        ((EditText) findViewById(R.id.edtEmail)).getText().clear();
        ((EditText) findViewById(R.id.edtInfo)).getText().clear();
    }

    //*******************************************************************************************
    public  void ReadValuesButtonClick(View view)
    {
        //opens new activity
        //sends read information to new activity to display on create
        String Results = readDataAssetsFolder();
        Intent i = new Intent(this, Report.class);
        i.putExtra("results", Results);
        startActivity(i);
    }

    //*******************************************************************************************
    public void aboutClick(View view)
    {
        //opens about activity
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }
}
