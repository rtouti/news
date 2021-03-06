package com.reddit.client.redditclient2.controllers.listeners;

import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.reddit.client.redditclient2.api.endpoints.SubredditLinksEndpoint;
import com.reddit.client.redditclient2.controllers.activities.MainActivity;

/**
 * Created by raouf on 17-03-27.
 */

public class SortingTabsOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
    private MainActivity activity;

    public SortingTabsOnTabSelectedListener(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String sorting = "";
        switch (tab.getText().toString()) {
            case "Hot":
                sorting = SubredditLinksEndpoint.SORITNG_HOT;
                break;
            case "New":
                sorting = SubredditLinksEndpoint.SORITNG_NEW;
                break;
            case "Top":
                sorting = SubredditLinksEndpoint.SORITNG_TOP;
                break;
            default:
                break;
        }

        Toast.makeText(activity, sorting, Toast.LENGTH_LONG).show();

        activity.changeCurrentSubreddit(
                activity.getCurrentSubreddit(),
                sorting,
                SubredditLinksEndpoint.TIME_SORTING_ALL_TIME
        );
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
