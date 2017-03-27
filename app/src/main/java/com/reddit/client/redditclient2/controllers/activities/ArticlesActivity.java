package com.reddit.client.redditclient2.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.reddit.client.redditclient2.R;
import com.reddit.client.redditclient2.api.things.Link;
import com.reddit.client.redditclient2.views.adapters.CommentsAdapter;
import com.squareup.picasso.Picasso;

public class ArticlesActivity extends AppCompatActivity {
    private Link link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        this.link = (Link)intent.getSerializableExtra("link");


        ListView comments = (ListView)findViewById(R.id.article_comments);
        comments.setAdapter(new CommentsAdapter(this));
    }

    public Link getLink(){
        return link;
    }
}