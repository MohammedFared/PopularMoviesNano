package com.popularmovies.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.presenter.adapters.MoviesAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements IMoviesAdapterView {
    private OnFragmentInteractionListener mListener;

    private RecyclerView mMovieRecyclerView;
    private TextView mFilterTextView;

    private MoviesAdapter mMoviesAdapter;

    public static SwipeRefreshLayout mSwipeRefreshLayout;

    private static String TAG = "MainFragmentLOG";

    public static String POPULAR = "Popular";
    public static String TOPRATED = "Top rated";

    private String currentFilter = POPULAR;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        Log.d(TAG, "newInstance: ");
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mMoviesAdapter = new MoviesAdapter(getContext(), currentFilter, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mFilterTextView = (TextView) view.findViewById(R.id.tv_filter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh);

        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.rv_movie);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFilterTextView.setText(currentFilter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        mMovieRecyclerView.setLayoutManager(gridLayoutManager);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setAdapter(mMoviesAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMoviesAdapter = new MoviesAdapter(getContext(), currentFilter, MainFragment.this);
                mMovieRecyclerView.setAdapter(mMoviesAdapter);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_filter){
            filterRecycler();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterRecycler() {
        if (currentFilter.equals(POPULAR)) {
            currentFilter = TOPRATED;
            mFilterTextView.setText(TOPRATED);
        }
        else {
            currentFilter = POPULAR;
            mFilterTextView.setText(POPULAR);
        }
        mMoviesAdapter = new MoviesAdapter(getContext(), currentFilter, this);
        mMovieRecyclerView.setAdapter(mMoviesAdapter);
    }

    public static void isRefreshing(boolean bool){
        mSwipeRefreshLayout.setRefreshing(bool);
    }

    /*
     * when a movie clicked in the Grid we call this function to notify the activity to switch to
     * the {@link: MovieDetailsFragment#}
     */
    @Override
    public void onMovieClicked(int movieId) {
        if (mListener != null) {
            mListener.onMovieClicked(movieId);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieDetailsFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onMovieClicked(int movieId);
    }
}
