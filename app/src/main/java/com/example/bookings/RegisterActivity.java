package com.example.bookings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookings.connection.Connection;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    EditText name,age, email, phone, password;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout nameError, emailError, phoneError, passError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        age= (EditText) findViewById(R.id.surname);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameS = name.getText().toString();
                String ageS = age.getText().toString();
                String emailS = email.getText().toString();
                String phoneS = phone.getText().toString();
                String passwordS = password.getText().toString();

               //new InsertData().execute(nameS,ageS,emailS,phoneS,passwordS);

                SetValidation();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        }
        else if (!passwordValid(password.getText().toString())) {
            passError.setError("Password must contain upper and lower case letters, numbers and one special character");
        }
        else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), MainNavigation.class);
            intent.putExtra("Name", name.getText().toString());
            intent.putExtra("Email",email.getText().toString());
            intent.putExtra("Phone", phone.getText().toString());
            intent.putExtra("Pass", password.getText().toString());
            startActivity(intent);
        }

    }

    public static boolean passwordValid(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!*_]).{0,20})").matcher(password);
        return matcher.matches();
    }

    class InsertData extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... strings) {
            try {
                String link = Connection.API + "signup.php";

                String data = URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(strings[0],"UTF-8");
                data += "&" + URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(strings[1],"UTF-8");
                data += "&" + URLEncoder.encode("age","UTF-8") + "=" + URLEncoder.encode(strings[2],"UTF-8");
                data += "&" + URLEncoder.encode("phone","UTF-8") + "=" + URLEncoder.encode(strings[3],"UTF-8");
                data += "&" + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(strings[4],"UTF-8");

                URL url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(data);
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                return  reader.readLine();


            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "Error" + e.getMessage();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error" + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject();

                if(jsonObject.getString("response").equals("success")){

                    Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainNavigation.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

}