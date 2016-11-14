package com.example.tomi.firebasetest;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textView;
    private EditText editText;

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference reference = database.getReference("journalEntry2");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        textView = (TextView) findViewById(R.id.textView);


        final DatabaseReference journalReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("journals");
        final JournalEntry entry = new JournalEntry("seppo",
                "seppo loysi +5 vorpal swordin",
                "22-11-2016",
                "sepon loydokset 22.11",
                "12311");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                //databaseReference.setValue("This is a super entry in the journal");

                //String newEntry = editText.getText().toString();
                //databaseReference.child("journals").child("journalEntry3").setValue(newEntry);

                journalReference.push().setValue(entry);
            }
        });

        //listener journalentry 3:lle
        //databaseReference.addValueEventListener(new ValueEventListener() {
        /*databaseReference.child("journals").child("journalEntry3").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                Log.d(TAG, "value: " + text);
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "failed to read value: ", databaseError.toException());
            }
        });
        */

        journalReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                JournalEntry entry = dataSnapshot.getValue(JournalEntry.class);
                Log.d(TAG, "got new entry, title:" + entry.getTitle()
                        + ", body: " + entry.getBodyText());
                //textView.setText(entry.getTitle());
                //täällä pystys tekemään jonkun hieno notifikaation tai muuta kivaa jos haluaa
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "failed to read value: ", databaseError.toException());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new FirebaseRecyclerAdapter<JournalEntry, JournalEntryHolder>(
                        JournalEntry.class,
                        android.R.layout.two_line_list_item,
                        JournalEntryHolder.class,
                        journalReference) {


            @Override
            protected void populateViewHolder(JournalEntryHolder viewHolder, JournalEntry entry, int position) {
                //here we set the text to the fields
                viewHolder.setTitle(entry.getTitle());
                viewHolder.setBody(entry.getBodyText());
                viewHolder.setClickListener(entry);
            }
        };

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static class JournalEntryHolder extends RecyclerView.ViewHolder{

        View view;

        public JournalEntryHolder(View view){
            super(view);
            this.view = view;
        }

        public void setTitle(String title){
            //(TextView)view.findViewById(R.id.text1);
            ((TextView)view.findViewById(android.R.id.text1)).setText(title);
        }

        public void setBody(String body){
            ((TextView)view.findViewById(android.R.id.text2)).setText(body);
        }

        public void setClickListener(final JournalEntry entry){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "item was clicked:" + entry.getTitle());
                }
            });

            //jos halutaan, voidaan etsiä yksittäinen elementti koko kortista johon lisätään
            //teksti, kuten esmes alla:

            /*final TextView t = (TextView) view.findViewById(android.R.id.text2);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "item was clicked:" + t.getText());
                }
            });*/
        }
    }
}
