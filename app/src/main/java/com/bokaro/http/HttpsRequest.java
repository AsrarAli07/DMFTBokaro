package com.bokaro.http;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bokaro.Interfacess.Consts;
import com.bokaro.Interfacess.Helper;
import com.bokaro.jsonparser.JSONParser;
import com.bokaro.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.Map;

/**
 * Created by Asrar Ali on 21/07/2022.
 */

public class HttpsRequest {
    private String match;
    private Map<String, String> params;
    private Map<String, File> fileparams;
    private Context ctx;
    private JSONObject jObject;
    public String TOKENTYPE;
    public String TOKEN;

    public HttpsRequest(String match, Map<String, String> params, Context ctx) {
        this.match = match;
        this.params = params;
        this.ctx = ctx;
    }

    public HttpsRequest(String match, Map<String, String> params, Map<String, File> fileparams, Context ctx) {
        this.match = match;
        this.params = params;
        this.fileparams = fileparams;
        this.ctx = ctx;
    }

    public HttpsRequest(String match, Context ctx) {
        this.match = match;
        this.ctx = ctx;
    }

    public HttpsRequest(String match, Context ctx, String TOKENTYPE, String TOKEN) {
        this.match = match;
        this.ctx = ctx;
        this.TOKENTYPE=TOKENTYPE;
        this.TOKEN=TOKEN;
    }

    public HttpsRequest(String match, JSONObject jObject, Context ctx) {
        this.match = match;
        this.jObject = jObject;
        this.ctx = ctx;
    }

    public void stringPostJson(final String TAG, Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addJSONObjectBody(jObject)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                //.addHeaders(Consts.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LmhvbmV5bGlhZW1hcnQuY29tXC9hcGlcL2xvZ2luIiwiaWF0IjoxNjA1NjM4Mjk4LCJleHAiOjE2MDcxMzgyOTgsIm5iZiI6MTYwNTYzODI5OCwianRpIjoibzdZc290ajhjWktOWlNnaiIsInN1YiI6MjAsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEiLCJtb2JpbGUiOm51bGx9.qMaaA7AeBiTqcRR7UfUWIFrJjJ9UlKucn7EIuyMBzxw")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " response body --->" + jObject.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                    }
                });
    }

    public void stringPostWithToken(final String TAG, final Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addBodyParameter(params)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringPut(final String TAG, final Helper h) {
        AndroidNetworking.put(Consts.BASE_URL + match)
                .addBodyParameter(params)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringDel(final String TAG, final Helper h) {
        AndroidNetworking.delete(Consts.BASE_URL + match)
                .addBodyParameter(params)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        //Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringPost(final String TAG, final Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addBodyParameter(params)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void stringPostWithHeader(final String TAG, final Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .addBodyParameter(params)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringPostRegistration(final String TAG, final Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addBodyParameter(params)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringGetPagination(final String TAG, final Helper h) {
        AndroidNetworking.get(match)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Params",Consts.TOKENBREAR+""+Consts.TOKEN );
                        Log.e(TAG, " response body --->" + response.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringGet(final String TAG, final Helper h) {
        Log.e(TAG, "stringGet: "+Consts.BASE_URL + match );
        AndroidNetworking.get(Consts.BASE_URL + match)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Params",Consts.TOKENBREAR+""+Consts.TOKEN );
                        Log.e(TAG, " response body --->" + response.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void imagePost(final String TAG, final Helper h) {
        AndroidNetworking.upload(Consts.BASE_URL + match)
                .addMultipartFile(fileparams)
                .addMultipartParameter(params)
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                .setTag("uploadTest")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        Log.e("Byte", bytesUploaded + "  !!! " + totalBytes);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " param --->" + params.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);

                        if (jsonParser.RESULT) {

                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /*public void gettingValue(VariableGetInter variableGetInter){
        variableGetInter.setNumber();
    }*/
    public void stringPostJsonWish(final String TAG, Helper h) {
        AndroidNetworking.post(Consts.BASE_URL + match)
                .addJSONObjectBody(jObject)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,Consts.TOKENBREAR+" "+Consts.TOKEN)
                //.addHeaders(Consts.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LmhvbmV5bGlhZW1hcnQuY29tXC9hcGlcL2xvZ2luIiwiaWF0IjoxNjA1NjM4Mjk4LCJleHAiOjE2MDcxMzgyOTgsIm5iZiI6MTYwNTYzODI5OCwianRpIjoibzdZc290ajhjWktOWlNnaiIsInN1YiI6MjAsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEiLCJtb2JpbGUiOm51bGx9.qMaaA7AeBiTqcRR7UfUWIFrJjJ9UlKucn7EIuyMBzxw")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        Log.e(TAG, " response body --->" + jObject.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void stringGetCart(final String TAG, final Helper h) {
        AndroidNetworking.get(Consts.BASE_URL + match)
                .setTag("test")
                .addHeaders(Consts.AUTHORIZATION,TOKENTYPE+" "+TOKEN)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Params",Consts.TOKENBREAR+""+Consts.TOKEN );
                        Log.e(TAG, " response body --->" + response.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        ProjectUtils.pauseProgressDialog();
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getResponse().message());
                        try {
                            String errorCase=new JSONObject(anError.getErrorBody()).getString("message");
                            if (anError.getErrorCode()==400){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==401){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==403){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==404){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==405){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==409){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==411){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==412){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==429){
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==500){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else if (anError.getErrorCode()==503){
                                h.backResponse(false, errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            else {
                                h.backResponse(false,errorCase, new JSONObject(anError.getErrorBody()));
                            }
                            Log.e(TAG, "onError: "+anError.getErrorCode() );
                            Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
