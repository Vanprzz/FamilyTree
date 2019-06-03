package com.estructurasdiscretas.familytree.ui.modules.main


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estructurasdiscretas.familytree.R
import com.estructurasdiscretas.familytree.model.database.Mirama
import com.estructurasdiscretas.familytree.model.database.MiramaManageDB
import com.estructurasdiscretas.familytree.model.schema.Couple
import com.estructurasdiscretas.familytree.model.schema.Person
import com.estructurasdiscretas.familytree.ui.custom.CoupleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    private var couple : Couple? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        val id = getSharedPreferences("Mirama", Context.MODE_PRIVATE).getString("idCoupleStart",null)
        viewModel.getParents(id)
    }

    /**
     *  Observers
     */
    private fun observeViewModel(){
        viewModel.couple.observe(this, Observer<Couple?> { couple = it})
        viewModel.personStart.observe(this, Observer<Pair<Person?,Person?>> { coupleCenter(it)})
        viewModel.personAB.observe(this, Observer<Pair<Person?,Person?>> { coupleAB(it)})
        viewModel.personCD.observe(this, Observer<Pair<Person?,Person?>> { coupleCD(it)})
        viewModel.listSon.observe(this, Observer<List<Pair<Person?,Person?>>> { coupleListSon(it)})
    }

    private fun coupleCenter(duo: Pair<Person?,Person?>){
        //RESET VIEW
        contentCenterA.visibility = View.VISIBLE
        contentCenterB.visibility = View.VISIBLE

        if(duo.first!=null){
            //ImageResource.loadImageUser(duo[0]?.img,ivCenterA)
            tvCenterA.text = duo.first?.name
        }else{
            contentCenterA.visibility = View.GONE
        }

        if(duo.second!=null){
            //ImageResource.loadImageUser(duo[1]?.img,ivCenterB)
            tvCenterB.text = duo.second?.name
        }else{
            contentCenterB.visibility = View.GONE
        }
    }


    private fun coupleAB(duo: Pair<Person?,Person?>){
        //RESET VIEW
        contentA.visibility = View.VISIBLE
        contentB.visibility = View.VISIBLE
        cardviewFA.visibility = View.VISIBLE

        if(duo.first != null || duo.second !=null ){
            if(duo.first!=null){
                tvContentA.text = duo.first?.name
            }else{
                contentA.visibility = View.GONE
            }

            if(duo.second!=null){
                tvContentB.text = duo.second?.name
            }else{
                contentB.visibility = View.GONE
            }

            cardviewFA.setOnClickListener{
                viewModel.getParents(couple!!.idCoupleParentMan)
            }

        }else{
            cardviewFA.visibility = View.GONE
        }

    }

    private fun coupleCD(duo: Pair<Person?,Person?>){
        //Reset View
        contentC.visibility = View.VISIBLE
        contentD.visibility = View.VISIBLE
        cardviewFB.visibility = View.VISIBLE

        if(duo.first != null || duo.second !=null ){
            if(duo.first!=null){
                //ImageResource.loadImageUser(duo[0]?.img,ivContentC)
                tvContentC.text = duo.first?.name
            }else{
                contentC.visibility = View.GONE
            }

            if(duo.second!=null){
                //ImageResource.loadImageUser(duo[1]?.img,ivContentD)
                tvContentD.text = duo.second?.name
            }else{
                contentD.visibility = View.GONE
            }

            cardviewFB.setOnClickListener{
                viewModel.getParents(couple!!.idCoupleParentWoman)
            }
        }else{
            cardviewFB.visibility = View.GONE
        }
    }

    fun coupleListSon(list : List< Pair< Person?,Person?>>){
        if (list.isNotEmpty()) {
            rvSon?.visibility = View.VISIBLE
        }else{
            rvSon?.visibility = View.GONE
        }

        val cou = MiramaManageDB(this).getSons(Mirama.SonHeaders.ID_COUPLE+"=?",arrayOf( couple!!.id))

        val adapter = CoupleAdapter(
            this,
            list.toMutableList(),
            object : CoupleAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    viewModel.getParents(cou[position].idCoupleSon)
                }
            })

        rvSon.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvSon.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}
