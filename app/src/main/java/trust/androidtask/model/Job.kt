package trust.androidtask.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "job_table")
class Job :Serializable{
    @PrimaryKey
    var id:String=""
    var type:String=""
    var url:String=""
    var created_at:String=""
    var company:String=""
    @Nullable
    var company_url:String?=""
    var location:String=""
    var title:String=""
    var description:String=""
    var how_to_apply:String=""
    @Nullable
    var company_logo:String?=""
    var isFav=false
}