package com.example.kawak.math_picker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kawak.math_picker.Model.Step;
import com.example.kawak.math_picker.Utilities.DetectedVal;
import com.example.kawak.math_picker.Utilities.GlobalUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class tab2 extends Fragment {

    LinearLayout view;
    public tab2() {
        // Required empty public constructor
    }
    private DetectedVal detectedValue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detectedValue=new DetectedVal();
        //sendRequest();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);
        view = v.findViewById(R.id.insert_point);
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            sendRequest();
        }
    }
    public void sendRequest(){

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, GlobalUtils.URL_EXP+detectedValue.getValue(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    public void handleResponse(String response){
        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
        JSONArray array;
        List<Step> steps = new ArrayList<>();

        Step step;
        try {
            array = new JSONArray(response);
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                step=new Step(obj.getString("before"), obj.getString("change"), obj.getString("after"));
                steps.add(step);
            }
        } catch (JSONException e) { e.printStackTrace(); }
        LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i=0; i<steps.size(); i++) {
            View v = vi.inflate(R.layout.step_item, null);
            TextView before = (TextView) v.findViewById(R.id.tv_before);
            TextView change = (TextView) v.findViewById(R.id.tv_change);
            TextView after = (TextView) v.findViewById(R.id.tv_after);
            before.setText(steps.get(i).getBrfore());
            change.setText(steps.get(i).getChange());
            after.setText(steps.get(i).getAfter());
            if(v.getParent() != null) {
                ((ViewGroup)v.getParent()).removeView(v); // <- fix
            }
            view.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Log.v("adding_views: ", "here: "+steps.get(i).getChange());
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
