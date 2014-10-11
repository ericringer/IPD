package com.ericringer.sfindiefest.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.KnownFor;
import com.ericringer.sfindiefest.types.NameSearch;
import com.ericringer.sfindiefest.types.NameSearch.NameSearchListener;
import com.ericringer.sfindiefest.types.NameSearchAdapter;
import com.ericringer.sfindiefest.types.NameSearchResult;

public class SearchFragment extends BaseFragment {
	private EditText txtSearch;
	private ListView listKnownFor;

	public SearchFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_search,container, false));
	}

	@Override
	public void prepareLayout() {
		listKnownFor = (ListView)findViewById(R.id.listKnownFor);
		txtSearch = (EditText) findViewById(R.id.txtSearch);
		findViewById(R.id.btnSearch).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String searchTerm = txtSearch.getText().toString();
				if(TextUtils.isEmpty(searchTerm))return;
				prepareList(new ArrayList<KnownFor>());
				performSearch(searchTerm);
			}});

	}
	
	protected void performSearch(String searchTerm) {
		final ProgressDialog pd = ProgressDialog.show(getActivity(), "Name Search", "Please wait...");
		pd.setCancelable(false);
		
		NameSearch search = new NameSearch();
		search.performSearch(searchTerm, new NameSearchListener(){
			@Override
			public void onResultRecieved(NameSearchResult result) {
				if(pd !=null && pd.isShowing())pd.dismiss();
				if(!result.isSuccess()){
					Toast.makeText(getActivity(), result.getDataMessage(), Toast.LENGTH_LONG).show();
				}
				prepareList(result.getData().getKnownFor());
				if(result.getData().getKnownFor().size() <=0)Toast.makeText(getActivity(), "No results!", Toast.LENGTH_LONG).show();
			}});
	}

	protected void prepareList(List<KnownFor> knownFor) {
		listKnownFor.setAdapter(new NameSearchAdapter(getActivity(),knownFor));
	}

	public String getTitle(){
		return getString(R.string.search);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		SearchFragment frag = new SearchFragment();
		frag.setCallback(callback);
		return frag;
	}

}
