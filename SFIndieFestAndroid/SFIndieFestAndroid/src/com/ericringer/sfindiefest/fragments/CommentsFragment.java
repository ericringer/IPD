package com.ericringer.sfindiefest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.ParseCommentAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CommentsFragment extends BaseFragment {
	private ListView listComments;
	private List<ParseObject> comments;

	public CommentsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_comments,container, false));
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
		String filmTitle = comment.getString("filmTitle");
		final String filmComment = comment.getString("filmComment");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(filmTitle);
		builder.setMessage(filmComment);
		builder.setPositiveButton("Share", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				shareToFacebook(filmComment);		
			}});
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
		Intent facebookShare = new Intent(Intent.ACTION_SEND);
		setFacebookShareComponent(facebookShare);
		facebookShare.setType("text/plain");
		facebookShare.putExtra(Intent.EXTRA_TEXT, comment);
		try{
			getActivity().startActivity(facebookShare);
		}catch(Exception e){
			Toast.makeText(getActivity(), "This feature requires Facebook to be installed", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void setFacebookShareComponent(Intent facebookShare){
		facebookShare.setComponent(new ComponentName("com.facebook.katana","com.facebook.katana.activity.composer.ImplicitShareIntentHandler"));
		if(facebookShare.resolveActivityInfo(getActivity().getPackageManager(), 0) != null)return;

		facebookShare.setComponent(new ComponentName("com.facebook.katana","com.facebook.composer.shareintent.ImplicitShareIntentHandler"));
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
