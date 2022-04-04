package com.example.runqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UsernameListActivity extends AppCompatActivity {

    ListView userNameListView;
    ArrayAdapter<String> userNameAdapter;
    ArrayList<String> userNameList;
    String newSearchQuery;
    CollectionReference collectionReference;
    ProgressBar progressBar;
    LinearLayout mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Accounts");


        userNameListView = findViewById(R.id.usernameList);
        userNameList = new ArrayList<String>();
        userNameAdapter = new ArrayAdapter<String>(this, R.layout.username_content, userNameList);
        userNameListView.setAdapter(userNameAdapter);

        userNameListView.setEmptyView(findViewById(R.id.noResultsView));

        progressBar = findViewById(R.id.loadingBar);
        mainList = findViewById(R.id.mainView);

        getUsernames(getIntent());



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getUsernames(intent);
    }
    public void getUsernames(Intent intent){

        //Google. (n.d.). Retrieved April 3, 2022 from the Android Developer Website: https://developer.android.com/training/search/setup
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            newSearchQuery = intent.getStringExtra(SearchManager.QUERY);
            userNameList.clear();
            userNameAdapter.notifyDataSetChanged();

            collectionReference
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            progressBar.setVisibility(View.GONE);
                            mainList.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    Log.v("Username", doc.getId());
                                    if (doc.getId().contains(newSearchQuery)) {
                                        userNameList.add(doc.getId());
                                        userNameAdapter.notifyDataSetChanged();
                                    }
                                }
                            } else {
                                Log.e("Error", "Error fetching documents from database");
                            }
                        }
                    });
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;

    }*/

    public void onBackPressed(){
        super.onBackPressed();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.search_bar:
                // cite https://www.youtube.com/watch?time_continue=216&v=8q-4AJFlraI&feature=emb_title
                MenuItem searchIcon = item;
                SearchView searchView = (SearchView) searchIcon.getActionView();
                searchView.setQueryHint("Search Username");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        getUsernames(getIntent());


                        return false;

                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        newSearchQuery = s;
                        return true;
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}