package com.puj.admincenter.domain.concepts

import javax.persistence.*

@Entity
@Table(name = "concepts")
data class ConceptsGroup(
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(nullable = false)
    val PXORDX: String = "",

    @Column(nullable = true)
    val OLDPXORDX: String? = "",

    @Column(nullable = false)
    val CODETYPE: String = "",

    @Column(nullable = true)
    val CONCEPT_CLASS_ID: String? = "",

    @Column(nullable = false)
    val CONCEPT_ID: String = "",

    @Column(nullable = false)
    val VOCABULARY_ID: String = "",

    @Column(nullable = false)
    val DOMAIN_ID: String = "",

    @Column(nullable = true)
    val TRACK: String? = "",

    @Column(nullable = true)
    val STANDARD_CONCEPT: String? = "",

    @Column(nullable = true)
    val CODE: String? = "",

    @Column(nullable = true)
    val CODEWITHPERIODS: String? = "",

    @Column(nullable = true)
    val CODESCHEME: String? = "",

    @Column(nullable = true)
    val LONG_DESC: String? = "",

    @Column(nullable = true)
    val SHORT_DESC: String? = "",

    @Column(nullable = true)
    val CODE_STATUS: String? = "",

    @Column(nullable = true)
    val CODE_CHANGE: String? = "",

    @Column(nullable = true)
    val CODE_CHANGE_YEAR: String? = "",

    @Column(nullable = true)
    val CODE_PLANNED_TYPE: String? = "",

    @Column(nullable = true)
    val CODE_BILLING_STATUS: String? = "",

    @Column(nullable = true)
    val CODE_CMS_CLAIM_STATUS: String? = "",

    @Column(nullable = true)
    val SEX_CD: String? = "",

    @Column(nullable = true)
    val ANAT_OR_COND: String? = "",

    @Column(nullable = true)
    val POA_CODE_STATUS: String? = "",

    @Column(nullable = true)
    val POA_CODE_CHANGE: String? = "",

    @Column(nullable = true)
    val POA_CODE_CHANGE_YEAR: String? = "",

    @Column(nullable = true)
    val VALID_START_DATE: String? = "",

    @Column(nullable = true)
    val VALID_END_DATE: String? = "",

    @Column(nullable = true)
    val INVALID_REASON: String? = "",

    @Column(nullable = true)
    val CREATE_DT: String? = "",

    @Column(nullable = false)
    val DROP_ENABLE: Int? = 0
)