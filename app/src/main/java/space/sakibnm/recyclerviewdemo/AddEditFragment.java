package space.sakibnm.recyclerviewdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USER = "userobejct";
    private static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    private User mUser;
    private int position;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextAge;
    private Button buttonAddEdit;
    private Boolean isEdit = false;

    private IaddButtonAction mListener;

    public AddEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment EditFragment.
     */
    //Factory for activating Add.......
    public static AddEditFragment newInstance() {
        AddEditFragment fragment = new AddEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    //Factory for activating Edit.......
    public static AddEditFragment newInstance(User user, int position) {
        AddEditFragment fragment = new AddEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            if(args.containsKey(ARG_POSITION)){
                mUser = (User) args.getSerializable(ARG_USER);
                position = args.getInt(ARG_POSITION);
                isEdit = true;
            }else{
                isEdit = false;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_edit, container, false);
        editTextName = rootView.findViewById(R.id.editTextName);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextAge = rootView.findViewById(R.id.editTextAge);
        buttonAddEdit = rootView.findViewById(R.id.buttonAddEdit);

//      Changing the button names based on Edit from Recycler Clicked....
        if(isEdit){
            //      Setting the current values to EditTexts...
            editTextName.setText(mUser.getName());
            editTextEmail.setText(mUser.getEmail());
            editTextAge.setText(mUser.getAge()+"");
            buttonAddEdit.setText("Edit");
        }
        else buttonAddEdit.setText("Add");

        buttonAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit){
                    String name = String.valueOf(editTextName.getText());
                    String email = String.valueOf(editTextEmail.getText());
                    int age = Integer.parseInt(String.valueOf(editTextAge.getText()));
                    mListener.editButtonClicked(new User(name, email, age), position);

                }else{
                    String name = String.valueOf(editTextName.getText());
                    String email = String.valueOf(editTextEmail.getText());
                    int age = Integer.parseInt(String.valueOf(editTextAge.getText()));
                    mUser = new User(name, email, age);

                    mListener.addButtonClicked(mUser);
                }
            }
        });

        return rootView;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IaddButtonAction){
            mListener = (IaddButtonAction) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IaddButtonAction");
        }
    }

    public interface IaddButtonAction{
        void addButtonClicked(User user);
        void editButtonClicked(User user, int position);
    }
}

//REFERENCE/JUNK code...
//buttonAddEdit.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        String name = editTextName.getText().toString();
//        String email = editTextEmail.getText().toString();
//        String ageStr = editTextAge.getText().toString();
//        if(!name.equals("")
//        && !email.equals("")
//        && !ageStr.equals("")){
//        users.add(new User(name, email, Integer.parseInt(ageStr)));
//        userAdapter.notifyDataSetChanged();
//
////                    Cleaning the edittexts.....
//        editTextName.setText("");
//        editTextEmail.setText("");
//        editTextAge.setText("");
//        }
//        }
//        });