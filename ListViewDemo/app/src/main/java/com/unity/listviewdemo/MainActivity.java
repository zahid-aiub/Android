package com.unity.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private String[] countryName;
    private int[] flags = {R.drawable.afghanistan,R.drawable.bangladesh,R.drawable.bhutan,R.drawable.chin,
                                R.drawable.denmark,R.drawable.finland,R.drawable.india,R.drawable.maldiv,
                                R.drawable.pakistan,R.drawable.srilanka};

    ArrayAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        countryName = getResources().getStringArray(R.array.country_name);
        CustomAdapter adapter = new CustomAdapter(this,countryName,flags);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String value = countryName[i];
                /*if (value.equals("Bangladesh")){
                    Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                }*/
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.searchId);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                //--->>> Here is adapter problem....................................................
                countryName=getResources().getStringArray(R.array.country_name);

                ad=new ArrayAdapter<String>(MainActivity.this,R.layout.sample_layout,R.id.countryTextView,countryName);
                ad.getFilter().filter(s);

                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.settingId){
            Toast.makeText(MainActivity.this, "Settings is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.infoId){
            Toast.makeText(MainActivity.this, "Information is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.selectId){
            Toast.makeText(MainActivity.this, "Select is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.viewId){
            Toast.makeText(MainActivity.this, "View is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.feedBackId){
            Toast.makeText(MainActivity.this, "Feedback is selected", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,FeedBack.class);
            startActivity(intent);



        }
        if (item.getItemId()==R.id.helpId){
            Toast.makeText(MainActivity.this, "Help is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.aboutusId){
            Toast.makeText(MainActivity.this, "About Us is selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.shareId){
            Toast.makeText(MainActivity.this, "Share Us is selected", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "Country details";
            String child = "\nYou can see here about more information those country";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,child);

            startActivity(Intent.createChooser(intent,"Share with"));

        }


        return super.onOptionsItemSelected(item);
    }
}
