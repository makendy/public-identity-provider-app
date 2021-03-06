package com.example.publicidentityprovider.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicidentityprovider.R
import com.example.publicidentityprovider.adapter.AppListAdapter
import com.example.publicidentityprovider.controller.RestApiService
import com.example.publicidentityprovider.details.AppInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_waiting_list.*

class AllowedListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allowed_list)

        // retrieve the intent that caused the activity to open
        val originIntent = intent
        // extract data from the intent
        val userToken = originIntent.getStringExtra("USER_TOKEN")!!
        Log.d("USER TOKEN", userToken)
        getAllowedAppList(userToken)
    }

    private fun displayAppListInfo(listOfApp : List<AppInfo>) {
        // display performance optimization when list widget size does not change
        RecyclerListApp.setHasFixedSize(true)
        // here we specify this is a standard vertical list
        RecyclerListApp.layoutManager = LinearLayoutManager(
            this@AllowedListActivity,
            LinearLayoutManager.VERTICAL,
            false)
        //decoration
        RecyclerListApp.addItemDecoration(DividerItemDecoration(this@AllowedListActivity, DividerItemDecoration.VERTICAL))
        // attach an adapter and provide some data

        val myAppClickListener = View.OnClickListener {
            // we retrieve the row position from its tag
            val position = it.tag as Int
            val clickedItem = listOfApp[position]
            // do stuff
            Toast.makeText(
                this@AllowedListActivity,
                "Clicked " + clickedItem.appName,
                Toast.LENGTH_SHORT)
                .show()

            // Create an explicit intent
            //val explicitIntent = Intent(this@MainActivity,
            //  GameDetailsActivity::class.java)
            // Insert extra data in the intent
            //explicitIntent.putExtra("GAME_ID", clickedItem.id.toString())
            // Start the other activity by sending the intent
            //startActivity(explicitIntent)
        }
        // attach an adapter and provide some data
        val recyclerAdapter = AppListAdapter(listOfApp,this@AllowedListActivity,
            myAppClickListener)
        RecyclerListApp.adapter = recyclerAdapter
    }

    private fun getAllowedAppList(userToken : String) {
        val apiService = RestApiService()

        apiService.getListAllowedAppInfo(userToken) {
            if (it != null) {
                val itJsonString = Gson().toJson(it)
                Log.d("GET APP ALLWD SUCCEED", itJsonString.toString())
                displayAppListInfo(it)
            } else {
                Log.d("GET APP ALLWD FAILURE", "An error has occurred !")
            }
        }
    }
}