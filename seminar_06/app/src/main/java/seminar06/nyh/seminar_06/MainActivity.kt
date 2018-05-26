package seminar06.nyh.seminar_06

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var pokemonVO: PokemonVO
    lateinit var pokemonCollections : ArrayList<PokemonVO>
    lateinit var pokemonRealm: Realm
    lateinit var collectionAdapter: CollectionAdapter
    lateinit var id : String
    override fun onClick(v: View?) {
        val idx : Int = main_collection_list_rv.getChildAdapterPosition(v)
        val name : String = pokemonCollections[idx].name
        deletePokemonList(name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        main_input_btn.setOnClickListener {
            insertPokemonList()
            pokemonCollections = getPokemonVOList().toMutableList() as ArrayList<PokemonVO>
            collectionAdapter = CollectionAdapter(this, pokemonCollections)
            collectionAdapter.setOnItemClickListener(this)
            main_collection_list_rv.layoutManager = LinearLayoutManager(this)
            main_collection_list_rv.adapter = collectionAdapter
        }

        main_logout_btn.setOnClickListener {
            SharedPreferenceController.clearSPC(this)
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    fun init(){
        Realm.init(this)
        pokemonRealm = Realm.getDefaultInstance()
        id = intent.getStringExtra("id")

        pokemonCollections = ArrayList()
        //arraylist로 변환
        pokemonCollections = getPokemonVOList().toMutableList() as ArrayList<PokemonVO>
        collectionAdapter = CollectionAdapter(this, pokemonCollections)
        collectionAdapter.setOnItemClickListener(this)
        main_collection_list_rv.layoutManager = LinearLayoutManager(this)
        main_collection_list_rv.adapter = collectionAdapter
    }

    fun getPokemonVOList() : RealmResults<PokemonVO> {
        return pokemonRealm.where(PokemonVO::class.java).equalTo("user", id).findAll()
    }
    fun insertPokemonList(){
        pokemonVO = PokemonVO()
        pokemonVO.num = main_num_et.text.toString().toInt()
        pokemonVO.name = main_name_et.text.toString()
        pokemonVO.type = main_type_et.text.toString()
        pokemonVO.user = id

        pokemonRealm.beginTransaction()
        pokemonRealm.copyToRealm(pokemonVO)
        pokemonRealm.commitTransaction()

        Toast.makeText(this, "등록 성공", Toast.LENGTH_SHORT).show()

    }
    fun deletePokemonList(name: String){
        val result = pokemonRealm.where(PokemonVO::class.java).equalTo("name", name).findAll()

        if(result.isEmpty()){
            return
        }
        pokemonRealm.beginTransaction()
        result.deleteAllFromRealm()
        pokemonRealm.commitTransaction()
        Toast.makeText(this, "삭제 성공", Toast.LENGTH_SHORT).show()

        collectionAdapter = CollectionAdapter(this, pokemonCollections)
        collectionAdapter.setOnItemClickListener(this)
        main_collection_list_rv.layoutManager = LinearLayoutManager(this)
        main_collection_list_rv.adapter = collectionAdapter
    }
}
