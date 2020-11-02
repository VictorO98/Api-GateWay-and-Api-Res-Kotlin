package com.puj.admincenter.repository.concept

import com.puj.admincenter.domain.concepts.ConceptsGroup
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
interface ConceptRepository : JpaRepository<ConceptsGroup, Long>,
                                JpaSpecificationExecutor<ConceptsGroup>{
    @Query("""
        SELECT COUNT(conceptsgroup) > 0
        FROM ConceptsGroup  conceptsgroup
        WHERE conceptsgroup.CONCEPT_ID = :CONCEPT_ID
    """)
    fun existsByConceptId(@Param("CONCEPT_ID") CONCEPT_ID: String): Boolean
}

class ConceptRepositorySpecification(private val VOCABULARY_ID: String?,
                                     private val CONCEPT_ID: String?,
                                     private val DOMAIN_ID: String?,
                                     private val SHORT_DESC: String?) : Specification<ConceptsGroup> {

    override fun toPredicate(root: Root<ConceptsGroup>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
        val p = mutableListOf<Predicate>()

        VOCABULARY_ID?.let { p.add(cb.equal(root.get<String>("VOCABULARY_ID"), VOCABULARY_ID)) }
        CONCEPT_ID?.let { p.add(cb.equal(root.get<String>("CONCEPT_ID"), CONCEPT_ID)) }
        DOMAIN_ID?.let { p.add(cb.equal(root.get<String>("DOMAIN_ID"), DOMAIN_ID)) }
        SHORT_DESC?.let { p.add(cb.like(root.get<String>("SHORT_DESC"), "%$SHORT_DESC%")) }

        return cb.and(*p.toTypedArray())
    }

}