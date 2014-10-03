package com.ericringer.sfindiefest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.SimpleAdapter;

public class CommentsFragment extends BaseFragment {
	private ListView listComments;
	private List<String> comments;
	private EditText txtComment;

	public CommentsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		return prepareAndSetView(inflater.inflate(R.layout.fragment_comments,container, false));
	}

	@Override
	public void prepareLayout() {
		comments = new ArrayList<String>();
		
		txtComment = (EditText) findViewById(R.id.txtComment);
		listComments = (ListView)findViewById(R.id.listComments);
		populateList();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.comments, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_share:
			if(!TextUtils.isEmpty(txtComment.getText())){
				addComment();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void addComment() {
		comments.add(txtComment.getText().toString());
		populateList();
		
		Intent facebookShare = new Intent(Intent.ACTION_SEND);
		facebookShare.setComponent(new ComponentName("com.facebook.katana","com.facebook.katana.activity.composer.ImplicitShareIntentHandler"));
		facebookShare.setType("text/plain");
		facebookShare.putExtra(Intent.EXTRA_TEXT, txtComment.getText().toString());
		try{

			getActivity().startActivity(facebookShare);

			}catch(Exception e){

			Toast.makeText(getActivity(), "This feature requires Facebook to be installed", Toast.LENGTH_SHORT).show();

			}
	}

	private void populateList(){
		listComments.setAdapter(new SimpleAdapter(getActivity(),comments));
	}
	
	public String getTitle(){
		return getString(R.string.comments);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		CommentsFragment frag = new CommentsFragment();
		frag.setCallback(callback);
		return frag;
	}

}
