package sg.edu.rp.c346.id20014518.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amt;
    EditText num;
    ToggleButton svs;
    ToggleButton gst;
    TextView bill;
    TextView pay;
    Button split;
    Button reset;
    EditText dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       amt = findViewById(R.id.editTextAmount);
       num = findViewById(R.id.editTextNum);
       svs = findViewById(R.id.toggleButton);
       gst = findViewById(R.id.toggleButton2);
       bill = findViewById(R.id.textViewBill);
       pay = findViewById(R.id.textViewPay);
       split = findViewById(R.id.buttonSplit);
       reset = findViewById(R.id.buttonReset);
       dis = findViewById(R.id.editTextNumDis);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                num.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                dis.setText("");
            }
        });

       split.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (amt.getText().toString().trim().length() != 0 && num.getText().toString().trim().length() != 0) {
                   double new_amt = 0.0;
                   if (!svs.isChecked() && !gst.isChecked()) {
                       new_amt = Double.parseDouble(amt.getText().toString());
                   } else if (svs.isChecked() && !gst.isChecked()) {
                       new_amt = Double.parseDouble(amt.getText().toString()) * 1.1;
                   } else if (!svs.isChecked() && gst.isChecked()) {
                       new_amt = Double.parseDouble(amt.getText().toString()) * 1.07;
                   } else {
                       new_amt = Double.parseDouble(amt.getText().toString()) * 1.17;
                   }
                   if (dis.getText().toString().trim().length() != 0) {
                       new_amt *= 1 - Double.parseDouble(dis.getText().toString()) / 100;
                   }
                   bill.setText("Total Bill: $" + String.format("%.2f", new_amt));
                   int numPeople = Integer.parseInt(num.getText().toString());
                   if (numPeople != 1)
                       pay.setText("Each Pays: $" + String.format("%.2f", new_amt / numPeople));
                   else
                       pay.setText("Each Pays: $" + new_amt);
               }
           }
       });
    }
}