package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import android.widget.Toast.makeText as makeText

private const val TAG = "CrimeListFragment"

class CrimeListFragment: Fragment() {

    //private val listItemCrime = 0
    //private val listItemCrimePolice = 1
    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val crimeListViewModel : CrimeListViewModel by lazy {
        ViewModelProvider (this) [CrimeListViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(viewLifecycleOwner, Observer {crimes ->
            crimes?.let {
                Log.i(TAG, "Got crimes ${crimes.size}")
                updateUI(crimes)
            }
        })
    }

    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var crime: Crime

        val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(this.crime.date)
            dateTextView.text = dateFormat.toString()
            solvedImageView.visibility = if (crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun onClick(v: View) {
            makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

   // private  inner class CrimeHolderPolice(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
       // private lateinit var crime: Crime

       // val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        //val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        //val policeButton: Button = itemView.findViewById(R.id.crime_button_police)
       // val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

       // init {
         //   itemView.setOnClickListener(this)
           // policeButton.setOnClickListener {
            //    makeText(context,"???????? ?????????? ??????????????", Toast.LENGTH_SHORT).show()
            //}
        //}

    //    fun bind (crime: Crime) {
      //      this.crime = crime
        //    titleTextView.text = this.crime.title
          //  val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(this.crime.date)
            //dateTextView.text = dateFormat.toString()
            //solvedImageView.visibility = if (crime.isSolved) {
            //    View.VISIBLE
           // } else {
            //    View.GONE
           // }
       // }

   //     override fun onClick(v: View) {
     //       makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
       // }
   // }

    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {  //RecyclerView.Adapter<RecyclerView.ViewHolder>
       // override fun getItemViewType(position: Int): Int {
          //  return if (crimes[position].requiresPolice) {
           //     listItemCrimePolice
           // } else {
            //    listItemCrime
            //0}
         //   return  listItemCrime
        //}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
          //  return if (viewType == listItemCrime) {
            //    val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
             //   CrimeHolder(view)
           // } else {
             //   val view = layoutInflater.inflate(R.layout.list_item_crime_police, parent, false)
               // CrimeHolderPolice(view)
            //}
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false) //??????
            return CrimeHolder(view)  //??????
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
          // when (holder) {
            //   is CrimeHolder -> {
              //     holder.bind(crime)
             //  }
             //  is CrimeHolderPolice -> {
               //    holder.bind(crime)
             //  }
          // }

        }

        override fun getItemCount() = crimes.size

    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

}