package space.sakibnm.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import space.sakibnm.recyclerviewdemo.model.UserAdapter;

public class MainActivity extends AppCompatActivity implements AddEditFragment.IaddButtonAction, UserAdapter.IeditButtonAction {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
//        INITIAL DATA..............
        users.add(new User("Alice","alice@abc.com",25));
        users.add(new User("Bob","bob@abc.com",20));
        users.add(new User("Trudy","trudy@abc.com",21));
        users.add(new User("Smith","smith@abc.com",35));
        users.add(new User("Greg","greg@abc.com",28));
        users.add(new User("Nate","nate@abc.com",37));
        users.add(new User("Charles","charles@abc.com",38));
        users.add(new User("Fernando","nando@abc.com",35));
        users.add(new User("Lando","lando@abc.com",20));
        users.add(new User("Carlos","carlos@abc.com",32));
        users.add(new User("Mick","mick@abc.com",21));
        users.add(new User("Sebastian","seb@abc.com",33));
        users.add(new User("Lewis","lewis@abc.com",35));
        users.add(new User("Daniel","dan@abc.com",31));

//      Setting up RecyclerView........
        recyclerView = findViewById(R.id.recyclerReview);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapter(users, this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(userAdapter);

//      Populating The First Add Fragment....
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerAddEdit, AddEditFragment.newInstance(),"addFragment")
                .commit();

    }

    @Override
    public void addButtonClicked(User user) {
        users.add(user);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void editButtonClicked(User user, int position) {
        users.set(position, user);
        userAdapter.notifyDataSetChanged();

//      Resetting to Add Fragment.....
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerAddEdit, AddEditFragment.newInstance(),"addFragment")
                .commit();
    }

    @Override
    public void editButtonClickedFromRecyclerView(int position) {
        User user = users.get(position);
//      Populating Edit Fragment....
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerAddEdit, AddEditFragment.newInstance(user, position))
                .commit();
    }
}