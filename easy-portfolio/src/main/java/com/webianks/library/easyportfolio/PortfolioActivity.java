package com.webianks.library.easyportfolio;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by R Ankit on 03-05-2017.
 */

public class PortfolioActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private FrameLayout githubContainer;
    private FrameLayout playStoreContainer;
    private FrameLayout linkedInContainer;
    private String github_link;
    private String play_store_link;
    private String linked_in_link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_activity);

        init();
        initSocialLinks();
    }

    private void initSocialLinks() {
        github_link = "http://www.github.com/webianks";
        play_store_link = "http://www.github.com/webianks";
        linked_in_link = "http://www.github.com/webianks";
    }

    private void init() {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        githubContainer = (FrameLayout) findViewById(R.id.githubContainer);
        playStoreContainer = (FrameLayout) findViewById(R.id.playStoreContainer);
        linkedInContainer = (FrameLayout) findViewById(R.id.linkedInContainer);

        githubContainer.setOnClickListener(this);
        playStoreContainer.setOnClickListener(this);
        linkedInContainer.setOnClickListener(this);

        Project project = new Project();
        project.setProjectName("Pollstap");
        project.setProjectDesc("Polling based social networking app.");

        Project project2 = new Project();
        project2.setProjectName("ScrollChoice");
        project2.setProjectDesc("Create scrollable choice view in android.");

        List<Project> projectList = new ArrayList<>();
        projectList.add(project);
        projectList.add(project2);

        ProjectsRecyclerAdapter projectsRecyclerAdapter = new ProjectsRecyclerAdapter(this, projectList);
        recyclerView.setAdapter(projectsRecyclerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.githubContainer)
            openLink(github_link);
        else if (id == R.id.playStoreContainer)
            openLink(play_store_link);
        else if (id == R.id.linkedInContainer)
            openLink(linked_in_link);
    }


    private void openLink(String link) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.cardview_dark_background));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(link));

    }
}
