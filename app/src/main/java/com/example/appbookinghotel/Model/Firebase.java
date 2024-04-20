package com.example.appbookinghotel.Model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Firebase {
    FirebaseFirestore mfirestore;
    FirebaseAuth mfirebaseAuth;
    FirebaseUser mfirebaseUser;
    FirebaseStorage mfirebaseStorage;
    StorageReference mstorageRef;
    Context mcontext;

    public Firebase(Context context){
        mfirestore = FirebaseFirestore.getInstance();
        mfirebaseAuth = FirebaseAuth.getInstance();
        mfirebaseStorage = FirebaseStorage.getInstance();
        mstorageRef = mfirebaseStorage.getReference();
        mfirebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        this.mcontext = context;
    }

    public interface FirebaseCallback<T> {
        void onCallback(ArrayList<T> list);
    }
    public void getAllDuThuyen(FirebaseCallback<DuThuyen> callback) {
        ArrayList<DuThuyen> duThuyenArrayList = new ArrayList<>();
        mfirestore.collection("DuThuyen")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            DuThuyen duThuyen = new DuThuyen(document.getId(),
                                    document.getString("TenDuThuyen"),
                                    document.getString("DiaDiemDuThuyen"),
                                    document.getString("MoTaDuThuyen"),
                                    document.getString("GiaDuThuyen"),
                                    document.getString("HinhAnhDuThuyen"));
                            duThuyenArrayList.add(duThuyen);
                        }
                        callback.onCallback(duThuyenArrayList);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });

    }
}
