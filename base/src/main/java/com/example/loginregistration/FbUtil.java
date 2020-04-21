package com.example.loginregistration;

import android.widget.Toast;

import com.example.loginregistration.fbclass.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FbUtil {

    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private FirebaseUser user;


    public FbUtil() {
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    // This will write 'obj' as json under /root/{path}/{key}
    // the key generated by firebase will be returned by this function
    public String pushObject(Object obj, String path) {
        DatabaseReference dbRef = db.getReference(path);
        String key = dbRef.push().getKey();
        dbRef.child(key).setValue(obj);
        return key;
    }


    public void testMsg() {
        DatabaseReference dbRef = db.getReference("/userdata/" + getUid());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String str = "Test from -- " + sdf.format(new Date());
        dbRef.setValue(str);
    }

    public String getUid() {
        return user.getUid();
    }

}
