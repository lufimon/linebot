package th.co.cdgs.eservicelinebot.repository

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
abstract class CoreQueryRepository {

    protected abstract var entityManager: EntityManager

    fun <T, ID> find(clazz: Class<T>, primaryKey: ID): T {
        return this.entityManager.find(clazz, primaryKey)
    }

}
