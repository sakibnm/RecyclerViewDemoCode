package space.sakibnm.recyclerviewdemo.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import space.sakibnm.recyclerviewdemo.R;
import space.sakibnm.recyclerviewdemo.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = "demo";

    private ArrayList<User> users;

    private IeditButtonAction mListener;

    public UserAdapter() {
    }

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        if(context instanceof IeditButtonAction){
            this.mListener = (IeditButtonAction) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IeditButtonAction");
        }

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewName;
        private final TextView textViewEmail;
        private final TextView textViewAge;
        private final Button buttonEdit;
        private final Button buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewEmail = itemView.findViewById(R.id.textViewEmail);
            this.textViewAge = itemView.findViewById(R.id.textViewAge);
            this.buttonEdit = itemView.findViewById(R.id.buttonEdit);
            this.buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewEmail() {
            return textViewEmail;
        }

        public TextView getTextAge() {
            return textViewAge;
        }

        public Button getButtonEdit() {
            return buttonEdit;
        }

        public Button getButtonDelete() {
            return buttonDelete;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_row,parent, false);

        return new ViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User curUser = this.getUsers().get(position);

        holder.getTextViewName().setText(curUser.getName());
        holder.getTextViewEmail().setText(curUser.getEmail());
        holder.getTextAge().setText(curUser.getAge()+"");

        holder.getButtonEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Edit clicked on: "+ users.get(holder.getAdapterPosition()).toString());
                mListener.editButtonClickedFromRecyclerView(holder.getAdapterPosition());
            }
        });

        holder.getButtonDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Delete clicked on: "+ users.get(holder.getAdapterPosition()).toString());
                users.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.getUsers().size();
    }

    public interface IeditButtonAction{
        void editButtonClickedFromRecyclerView(int position);
    }
}
