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

    @Column(nullable = false)
    val OLDPXORDX: String = "",

    @Column(nullable = false)
    val CODETYPE: String = "",

    @Column(nullable = false)
    val CONCEPT_CLASS_ID: String = "",

    @Column(nullable = false)
    val CONCEPT_ID: String = "",

    @Column(nullable = false)
    val VOCABULARY_ID: String = "",

    @Column(nullable = false)
    val DOMAIN_ID: String = "",

    @Column(nullable = false)
    val TRACK: String = "",

    @Column(nullable = false)
    val STANDARD_CONCEPT: String = "",

    @Column(nullable = false)
    val CODE: String = "",

    @Column(nullable = false)
    val CODEWITHPERIODS: String = "",

    @Column(nullable = false)
    val CODESCHEME: String = "",

    @Column(nullable = false)
    val LONG_DESC: String = "",

    @Column(nullable = false)
    val SHORT_DESC: String = "",

    @Column(nullable = false)
    val CODE_STATUS: String = "",

    @Column(nullable = false)
    val CODE_CHANGE: String = "",

    @Column(nullable = false)
    val CODE_CHANGE_YEAR: String = "",

    @Column(nullable = false)
    val CODE_PLANNED_TYPE: String = "",

    @Column(nullable = false)
    val CODE_BILLING_STATUS: String = "",

    @Column(nullable = false)
    val CODE_CMS_CLAIM_STATUS: String = "",

    @Column(nullable = false)
    val SEX_CD: String = "",

    @Column(nullable = false)
    val ANAT_OR_COND: String = "",

    @Column(nullable = false)
    val POA_CODE_STATUS: String = "",

    @Column(nullable = false)
    val POA_CODE_CHANGE: String = "",

    @Column(nullable = false)
    val POA_CODE_CHANGE_YEAR: String = "",

    @Column(nullable = false)
    val VALID_START_DATE: String = "",

    @Column(nullable = false)
    val VALID_END_DATE: String = "",

    @Column(nullable = false)
    val INVALID_REASON: String = "",

    @Column(nullable = false)
    val CREATE_DT: String = "",

    @Column(nullable = false)
    val DROP_ENABLE: Int = 0
)