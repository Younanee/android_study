package seminar06.nyh.seminar_06

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PokemonVO  : RealmObject() {
    @PrimaryKey
    var name : String = ""
    var num : Int = 0
    var user : String = ""
    var type : String = ""
}