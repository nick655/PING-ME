package com.chating.chat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.chating.CheckoutActivity;
import com.chating.R;
import com.chating.chat.adapter.SectionsPagerAdapter;
import com.chating.login.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.main_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.main_tabPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.email)
    ImageView email;
    @BindView(R.id.search_page)
    ImageView searchPage;
    @BindView(R.id.payment)
    ImageView payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setToolBar();
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }

            protected void search() {
                Toast.makeText(ChatActivity.this, "You Clicked On Search : ", Toast.LENGTH_SHORT).show();

                Intent intent = getIntent();
                if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                    //   String query = intent.getStringExtra(SearchManager.QUERY);


                }
            }
        });

         payment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent i= new Intent(ChatActivity.this, CheckoutActivity.class);
                 startActivity(i);
             }
         });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ChatActivity.this, setting);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.logout, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ChatActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChatActivity.this, SignUpActivity.class);
                        startActivity(intent);
                        return true;

                    }
                });//here we need to set flag for not open next time same account when close.

                popup.show();
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }

            protected void sendEmail() {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email.", "");
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(ChatActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void setToolBar() {
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
