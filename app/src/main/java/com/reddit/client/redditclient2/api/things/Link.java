package com.reddit.client.redditclient2.api.things;

import android.util.Log;

import com.reddit.client.redditclient2.api.endpoints.Endpoint;
import com.reddit.client.redditclient2.http.HttpRequestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by raouf on 17-02-25.
 */

public class Link extends Thing implements Votable, Created {

    //Variables from "votable" interface
    public int ups;
    public int downs;
    public Boolean likes;

    //Variables from "created" interface
    public long created;
    public long created_utc;


    public String author;
    public boolean is_self;
    public int numComments;
    public String permalink;
    public int score;
    public String selftext;        //Can be empty
    public String subreddit;
    public String thumbnail;       //"self" if self post
    public String title;
    public String url;             //Link of this post, permalink if self post



    public Link(){

    }

    public ArrayList<Comment> comments(){
        String url = Endpoint.REDDIT_BASE_URL + this.permalink + ".json";
        HttpRequestUtil http = new HttpRequestUtil(url);
        http.makeRequest();
        String body = http.getBodyString();

        ArrayList<Comment> comments = new ArrayList<>();

        Log.i("DEBUG", "body : "+body);
        Log.i("DEBUG", "url : "+url);
        try {
            JSONArray children = new JSONArray(body)
                    .getJSONObject(1)
                    .getJSONObject("data")
                    .getJSONArray("children");
            JSONObject childData;
            for(int i = 0; i < children.length()-1; i++){
                childData = children.getJSONObject(i).getJSONObject("data");
                Comment comment = new Comment();
                comment.ups = childData.getInt("ups");
                comment.downs = childData.getInt("downs");

                //"likes" peut être null
                try {
                    comment.likes = childData.getBoolean("likes");
                }
                catch(JSONException e){
                    comment.likes = null;
                }

                comment.created = childData.getLong("created");
                comment.created_utc = childData.getLong("created_utc");
                comment.author = childData.getString("author");
                comment.body = childData.getString("body");
                comment.gilded = childData.getInt("gilded");
                comment.score = childData.getInt("score");
                comment.subreddit = childData.getString("subreddit");

                //S'il n'y a pas de replies, replies == ""
                try {
                    comment.replies = childData.getJSONObject("replies")
                            .getJSONObject("data")
                            .getJSONArray("children");
                }
                catch(JSONException e){
                    comment.replies = null;
                }

                comments.add(comment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return comments;
    }

}
