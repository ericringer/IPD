package com.ericringer.sfindiefest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.FacebookHelper;
import com.ericringer.sfindiefest.types.ParseCommentAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CommentsFragment extends BaseFragment {
	private ListView listComments;
	private List<ParseObject> comments;
	FacebookHelper fbHelper;
	public CommentsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		fbHelper = new FacebookHelper(getActivity());
		fbHelper.checkForFacebook(savedInstanceState);
		return prepareAndSetView(inflater.inflate(R.layout.fragment_comments,container, false));
	}

	@Override
	public void onStart() {
		fbHelper.handleOnStart();
		super.onStart();
	}

	@Override
	public void onStop() {
		fbHelper.handleOnStop();
		super.onStop();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e("TAG", "Activity Reuslt");
		fbHelper.hanldeActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		fbHelper.handleOnSaveInstance(outState);
		super.onSaveInstanceState(outState);
	}


	@Override
	public void prepareLayout() {
		comments = new ArrayList<ParseObject>();
		listComments = (ListView)findViewById(R.id.listComments);
		listComments.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
					long arg3) {
				showComment(pos);
			}});
		requestComments();
	}

	
	protected void showComment(int pos) {
		ParseObject comment = comments.get(pos);
		final String filmTitle = comment.getString("filmTitle");
		final String filmComment = comment.getString("filmComment");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(filmTitle);
		builder.setMessage(filmComment);
		
		String userID = comment.getString("userID");
		if(!TextUtils.isEmpty(userID) && ParseInstallation.getCurrentInstallation().getObjectId().contentEquals(userID)){
			builder.setPositiveButton("Share", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					shareToFacebook(filmTitle+"\r\n"+filmComment);		
				}});
		}
		
		builder.setNegativeButton("OK", null);
		builder.create().show();
	}

	private void requestComments() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("filmComments");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> parseComments, ParseException e) {
				if(e!=null){
					return;
				}
				comments = parseComments;
				listComments.setAdapter(new ParseCommentAdapter(getActivity(),comments));
			}
		});		
	}

	private void shareToFacebook(String comment) {
		fbHelper.shareToFacebook(comment);
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
