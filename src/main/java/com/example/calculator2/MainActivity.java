package com.example.calculator2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;

    ImageView backSpace;

    EditText editTxt;

    float mValueOne, mValueTwo;

    boolean first, used, mult, div, sub, add,dUsed;

    int index;

    String f,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.zero);
        button1 = (Button) findViewById(R.id.one);
        button2 = (Button) findViewById(R.id.two);
        button3 = (Button) findViewById(R.id.three);
        button4 = (Button) findViewById(R.id.four);
        button5 = (Button) findViewById(R.id.five);
        button6 = (Button) findViewById(R.id.six);
        button7 = (Button) findViewById(R.id.seven);
        button8 = (Button) findViewById(R.id.eight);
        button9 = (Button) findViewById(R.id.nine);
        button10 = (Button) findViewById(R.id.decimal);
        buttonAdd = (Button) findViewById(R.id.plus);
        buttonSub = (Button) findViewById(R.id.sub);
        buttonMul = (Button) findViewById(R.id.mult);
        buttonDivision = (Button) findViewById(R.id.division);
        buttonC = (Button) findViewById(R.id.C);
        buttonEqual = (Button) findViewById(R.id.equal);
        editTxt = (EditText) findViewById(R.id.txt);
        backSpace = (ImageView) findViewById(R.id.backSpace);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "1");
                used = true;

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "2");
                used = true;

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "3");
                used = true;

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "4");
                used = true;

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "5");
                used = true;
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "6");
                used = true;

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "7");
                used = true;

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "8");
                used = true;

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "9");
                used = true;
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText(editTxt.getText() + "0");


            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (used) {

                    editTxt.setText(editTxt.getText() + "+");

                    f = editTxt.getText().toString();

                    index = f.indexOf("+");
                    System.out.println(f + " 1st add " + index);

                    add = true;

                    used = false;
                } else if (!used && (add || sub || mult || div)) {
                    f = editTxt.getText().toString();

                    f = f.substring(0, f.length() - 1);


                    editTxt.setText(f + "+");
                    System.out.println(f + " 2st add " + index);

                    sub = false;

                    mult = false;

                    div = false;

                    add = true;

                }

            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (used) {

                    editTxt.setText(editTxt.getText() + "-");

                    f = editTxt.getText().toString();

                    index = f.indexOf("-");
                    System.out.println(f + " 1st sub " + index);

                    sub = true;

                    used = false;
                } else if (!used && (add || sub || mult || div)) {
                    f = editTxt.getText().toString();

                    f = f.substring(0, f.length() - 1);


                    editTxt.setText(f + "-");
                    System.out.println(f + " 2nd sub  " + index);

                    add = false;

                    mult = false;

                    div = false;

                    sub = true;
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (used) {

                    editTxt.setText(editTxt.getText() + "x");

                    f = editTxt.getText().toString();

                    index = f.indexOf("x");
                    System.out.println(f + " 1st mult " + index);

                    mult = true;

                    used = false;
                } else if (!used && (add || sub || mult || div)) {
                    f = editTxt.getText().toString();

                    f = f.substring(0, f.length() - 1);


                    editTxt.setText(f + "x");
                    System.out.println(f + " 2st mult " + index);

                    sub = false;

                    add = false;

                    div = false;

                    mult = true;

                }


            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (used) {

                    editTxt.setText(editTxt.getText() + "÷");

                    f = editTxt.getText().toString();

                    index = f.indexOf("÷");
                    System.out.println(f + " 1st div " + index);

                    div = true;

                    used = false;
                } else if (!used && (add || sub || mult || div)) {
                    f = editTxt.getText().toString();

                    f = f.substring(0, f.length() - 1);

                    editTxt.setText(f + "÷");
                    System.out.println(f + " 2st div " + index);

                    sub = false;

                    add = false;

                    mult = false;

                    div = true;
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = editTxt.getText().toString();
                System.out.println(f);
                Calculations calculations = new Calculations();
                if(used) {
                    editTxt.setText(calculations.answer(f) + "");
                }
                else{
                    showToast(v);
                }
                used = false;
            }
        });

        backSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = editTxt.getText().toString();
                if (f.length() >0 ) {
                    t = f;
                    f = f.substring(0, f.length() - 1);
                    editTxt.setText(f);
                    if(t.indexOf("x") == -1){
                        mult = false;
                    }
                    if(t.indexOf("÷") == -1){
                        div = false;
                    }
                    if(t.indexOf("-") == -1){
                        sub = false;
                    }
                    if(t.indexOf("+") == -1){
                        add = false;
                    }
                    System.out.println(f + "string" + f.length());
                }
                if (f.length() <1 ) {

                    used = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setText("");
                mult = sub = div = add = used = false;
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(used){
                    editTxt.setText(editTxt.getText() + ".");
                    used = false;
                }

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.inval,
                Toast.LENGTH_SHORT);
        toast.show();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_Calculator) {
            Intent randomIntent = new Intent(this, MainActivity.class);


            startActivity(randomIntent);
        } else if (id == R.id.nav_AllFormulas) {
            Intent randomIntent = new Intent(this, Formulas.class);


            startActivity(randomIntent);
        } else if (id == R.id.nav_EditFormulas) {
            Intent randomIntent = new Intent(this, EditFormulas.class);


            startActivity(randomIntent);
        } else if (id == R.id.nav_UseFormula) {
            Intent randomIntent = new Intent(this, UseFormula.class);


            startActivity(randomIntent);
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
