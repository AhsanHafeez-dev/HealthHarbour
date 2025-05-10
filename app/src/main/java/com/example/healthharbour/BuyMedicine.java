package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicine extends AppCompatActivity {
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    Button btnback,btnToCart;
    String[][] medicine = {
            {"Indrop D", "", "", "", "350"},
            {"Osnate D", "", "", "", "480"},
            {"Concor 2.5", "", "", "", "1506"},
            {"Xplendid", "", "", "", "480"},
            {"Lowplat", "", "", "", "280"},
            {"Risek", "", "", "", "770"},
            {"Mixtard Penfil", "", "", "", "450"},
            {"Covam 5/80", "", "", "", "400"},
            {"Galvusmet 50/1000", "", "", "", "2300"}
    };
String medicineDetails[]={
        "Vitamin D 2000iu for absorbing calcium excellent for joint and bones specially for people over 30\n Usual Dosage : 1/week",
        "MultiVitamin with added vitamin D.\nespecially beneficial for people above 40 years\n helps absorbing calcium\n provide sufficient calcium",
        "A: Concor 2.5 MG tablet is used to treat hypertension (high blood pressure) and angina (chest pain caused by a decrease in oxygen supply to the heart muscles) either alone or in combination with other medications. This medication relaxes the heart muscles and reduces the rate of your heart. This reduces blood pressure.",
        "Generic Formula :  Rosuvastin\n For cure of:cholestrol\nused on doctor prescription \navailable in 5,10 and 20mg\nmanufactured by pharmaevo",
        "Generic Formula : Clopridrogel\nLOWPLAT (Clopidogrel) is indicated to reduce the rate of myocardial infarction and stroke in patients with acute ST-elevation myocardial infarction (STEMI) (who are to be managed medically).\n It should be administered in conjunction with aspirin.",
        "Generic Formula : Omeprazole \nFor adults for the treatment of: Gastro-Esophageal Reflux Disease (GERD): treatment of erosive reflux esophagitis, long term management of patients with healed esophagitis to prevent relapse and symptomatic treatment of gastroesophageal reflux disease (GERD). Gastric and duodenal ulcer.\navailable in sachets and capsule\n20 and 40 mg available",
        "Mixtard® 30 and Protaphane® are human insulins used to treat diabetes. Diabetes mellitus is a disease where your body does not produce enough insulin to control the level of your blood sugar.",
        "Indication: For the treatment of hypertension in: Patients whose blood pressure is not adequately controlled on either monotherapy. Patients who are likely to need multiple drugs to achieve their blood pressure goals.\n",
        "Generic Formula : Vildagliptin Metformin HCL \nThe drug is indicated in the treatment of type 2 diabetes with the following specific uses: Galvus met is indicated as an adjunct to diet and exercise to help improve blood sugar control in humans. ",

};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnback=findViewById(R.id.btnback);
        btnToCart=findViewById(R.id.btnpurchase);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyMedicine.super.onBackPressed();
            }
        });

        btnToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BuyMedicine.this, MedicineCheckoutCart.class);
                startActivity(intent);

            }
        });

        list=new ArrayList();
        for(int i=0;i<medicine.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",medicine[i][0]);
            item.put("line2",medicine[i][1]);
            item.put("line3",medicine[i][2]);
            item.put("line4",medicine[i][3]);
            item.put("line5","Price : "+medicine[i][4]+" PKR /-");
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        ListView lst=findViewById(R.id.listviewlab);

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(BuyMedicine.this, CartBuyMedicine.class);
                intent1.putExtra("text1",medicine[position][0]);
                intent1.putExtra("text2",medicineDetails[position]);
                intent1.putExtra("text3",medicine[position][4]);


                startActivity(intent1);

            }
        });



    }
}