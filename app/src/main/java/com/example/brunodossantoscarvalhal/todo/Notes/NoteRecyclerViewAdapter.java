package com.example.brunodossantoscarvalhal.todo.Notes;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.brunodossantoscarvalhal.todo.Notes.NoteListFragment.OnListFragmentInteractionListener;
import com.example.brunodossantoscarvalhal.todo.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Note} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final OnListFragmentInteractionListener mListener;

    public NoteRecyclerViewAdapter(List<Note> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.checkBoxView.setChecked(mValues.get(position).getCompleted());
        holder.titleView.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.checkBoxView.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        holder.mItem.setCompleted(b);
                    }
                }

        );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleView;
        public final CheckBox checkBoxView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            checkBoxView = view.findViewById(R.id.completed_checkbox);
            titleView = view.findViewById(R.id.title_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
