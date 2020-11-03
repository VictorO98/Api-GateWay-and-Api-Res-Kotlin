package com.puj.admincenter.repository.concept

import com.puj.admincenter.domain.concepts.ConceptsGroup
import com.puj.admincenter.dto.concepts.ConceptDto
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
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

    @Transactional
    @Modifying
    @Query("""
            UPDATE ConceptsGroup conceptsGroup
            SET conceptsGroup.PXORDX = :#{#conceptDto.PXORDX},
                conceptsGroup.OLDPXORDX = :#{#conceptDto.OLDPXORDX},
                conceptsGroup.CODETYPE = :#{#conceptDto.CODETYPE},
                conceptsGroup.CONCEPT_CLASS_ID = :#{#conceptDto.CONCEPT_CLASS_ID},
                conceptsGroup.CONCEPT_ID = :CONCEPT_ID,
                conceptsGroup.VOCABULARY_ID = :#{#conceptDto.VOCABULARY_ID},
                conceptsGroup.DOMAIN_ID = :#{#conceptDto.DOMAIN_ID},
                conceptsGroup.TRACK = :#{#conceptDto.TRACK},
                conceptsGroup.STANDARD_CONCEPT = :#{#conceptDto.STANDARD_CONCEPT},
                conceptsGroup.CODE = :#{#conceptDto.CODE},
                conceptsGroup.CODEWITHPERIODS = :#{#conceptDto.CODEWITHPERIODS},
                conceptsGroup.CODESCHEME = :#{#conceptDto.CODESCHEME},
                conceptsGroup.LONG_DESC = :#{#conceptDto.LONG_DESC},
                conceptsGroup.SHORT_DESC = :#{#conceptDto.SHORT_DESC},
                conceptsGroup.CODE_STATUS = :#{#conceptDto.CODE_STATUS},
                conceptsGroup.CODE_CHANGE = :#{#conceptDto.CODE_CHANGE},
                conceptsGroup.CODE_CHANGE_YEAR = :#{#conceptDto.CODE_CHANGE_YEAR},
                conceptsGroup.CODE_PLANNED_TYPE = :#{#conceptDto.CODE_PLANNED_TYPE},
                conceptsGroup.CODE_BILLING_STATUS = :#{#conceptDto.CODE_BILLING_STATUS},
                conceptsGroup.CODE_CMS_CLAIM_STATUS = :#{#conceptDto.CODE_CMS_CLAIM_STATUS},
                conceptsGroup.SEX_CD = :#{#conceptDto.SEX_CD},
                conceptsGroup.ANAT_OR_COND = :#{#conceptDto.ANAT_OR_COND},
                conceptsGroup.POA_CODE_STATUS = :#{#conceptDto.POA_CODE_STATUS},
                conceptsGroup.POA_CODE_CHANGE = :#{#conceptDto.POA_CODE_CHANGE},
                conceptsGroup.POA_CODE_CHANGE_YEAR = :#{#conceptDto.POA_CODE_CHANGE_YEAR},
                conceptsGroup.VALID_START_DATE = :#{#conceptDto.VALID_START_DATE},
                conceptsGroup.VALID_END_DATE = :#{#conceptDto.VALID_END_DATE},
                conceptsGroup.INVALID_REASON = :#{#conceptDto.INVALID_REASON}
            WHERE conceptsGroup.CONCEPT_ID = :#{#conceptDto.CONCEPT_ID}
        """)
    fun updateByConceptId(conceptDto: ConceptDto)


    @Transactional
    @Modifying
    @Query("""
        UPDATE ConceptsGroup conceptsGroup
        SET conceptsGroup.DROP_ENABLE = 1
        WHERE conceptsGroup.CONCEPT_ID = :CONCEPT_ID
    """)
    fun deleteByConceptId(@Param("CONCEPT_ID") CONCEPT_ID: String): Boolean

}

class ConceptRepositorySpecification(private val VOCABULARY_ID: String?,
                                     private val CONCEPT_ID: String?,
                                     private val DOMAIN_ID: String?,
                                     private val SHORT_DESC: String?,
                                     private val DROP_ENABLE: Int?) : Specification<ConceptsGroup> {

    override fun toPredicate(root: Root<ConceptsGroup>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
        val p = mutableListOf<Predicate>()

        VOCABULARY_ID?.let { p.add(cb.equal(root.get<String>("VOCABULARY_ID"), VOCABULARY_ID)) }
        CONCEPT_ID?.let { p.add(cb.equal(root.get<String>("CONCEPT_ID"), CONCEPT_ID)) }
        DOMAIN_ID?.let { p.add(cb.equal(root.get<String>("DOMAIN_ID"), DOMAIN_ID)) }
        SHORT_DESC?.let { p.add(cb.like(root.get<String>("SHORT_DESC"), "%$SHORT_DESC%")) }
        DROP_ENABLE?.let { p.add(cb.equal(root.get<Int>("DROP_ENABLE"), DROP_ENABLE)) }
        return cb.and(*p.toTypedArray())
    }

}


