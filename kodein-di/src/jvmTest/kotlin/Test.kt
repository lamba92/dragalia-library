import com.github.lamba92.dragalialost.di.dragaliaLostModule
import com.github.lamba92.dragalialost.di.dragaliaMongoDBCacheModule
import com.github.lamba92.dragalialost.domain.usecases.SearchAllByNameUseCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import kotlin.test.Test

class Testotron : KodeinAware {

    override val kodein by Kodein.lazy {
        import(dragaliaLostModule(true))
        import(dragaliaMongoDBCacheModule("192.168.1.158"))
    }

    val uv by instance<SearchAllByNameUseCase>()

    @Test
    fun testtttttttttttt() = runBlocking {
        val t = uv.buildAction("")
    }

}
