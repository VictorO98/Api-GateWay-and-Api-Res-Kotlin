package com.puj.admincenter.dto.concepts

import com.puj.admincenter.domain.concepts.ConceptsGroup

data class GetConceptDto (
        val	id: Int?,
        val	PXORDX: String,
        val	OLDPXORDX: String?,
        val	CODETYPE:String,
        val	CONCEPT_CLASS_ID:String?,
        val	CONCEPT_ID:String,
        val	VOCABULARY_ID:String,
        val	DOMAIN_ID:String,
        val	TRACK:String?,
        val	STANDARD_CONCEPT:String?,
        val	CODE:String?,
        val	CODEWITHPERIODS:String?,
        val	CODESCHEME:String?,
        val	LONG_DESC:String?,
        val	SHORT_DESC:String?,
        val	CODE_STATUS:String?,
        val	CODE_CHANGE:String?,
        val	CODE_CHANGE_YEAR:String?,
        val	CODE_PLANNED_TYPE:String?,
        val	CODE_BILLING_STATUS:String?,
        val	CODE_CMS_CLAIM_STATUS:String?,
        val	SEX_CD:String?,
        val	ANAT_OR_COND:String?,
        val	POA_CODE_STATUS:String?,
        val	POA_CODE_CHANGE:String?,
        val	POA_CODE_CHANGE_YEAR:String?,
        val	VALID_START_DATE:String?,
        val	VALID_END_DATE:String?,
        val	INVALID_REASON:String?,
        val	CREATE_DT:String?
){
    companion object {
        fun convert(conceptsGroup: ConceptsGroup): GetConceptDto {
            val dto = GetConceptDto(
                    id = conceptsGroup.id,
                    PXORDX = conceptsGroup.PXORDX,
                    OLDPXORDX = conceptsGroup.OLDPXORDX,
                    CODETYPE = conceptsGroup.CODETYPE,
                    CONCEPT_CLASS_ID = conceptsGroup.CONCEPT_CLASS_ID,
                    CONCEPT_ID = conceptsGroup.CONCEPT_ID,
                    VOCABULARY_ID = conceptsGroup.VOCABULARY_ID,
                    DOMAIN_ID = conceptsGroup.DOMAIN_ID,
                    TRACK = conceptsGroup.TRACK,
                    STANDARD_CONCEPT = conceptsGroup.STANDARD_CONCEPT,
                    CODE = conceptsGroup.CODE,
                    CODEWITHPERIODS = conceptsGroup.CODEWITHPERIODS,
                    CODESCHEME = conceptsGroup.CODESCHEME,
                    LONG_DESC = conceptsGroup.LONG_DESC,
                    SHORT_DESC = conceptsGroup.SHORT_DESC,
                    CODE_STATUS = conceptsGroup.CODE_STATUS,
                    CODE_CHANGE = conceptsGroup.CODE_CHANGE,
                    CODE_CHANGE_YEAR = conceptsGroup.CODE_CHANGE_YEAR,
                    CODE_PLANNED_TYPE = conceptsGroup.CODE_PLANNED_TYPE,
                    CODE_BILLING_STATUS = conceptsGroup.CODE_CMS_CLAIM_STATUS,
                    CODE_CMS_CLAIM_STATUS = conceptsGroup.CODE_CMS_CLAIM_STATUS,
                    SEX_CD = conceptsGroup.SEX_CD,
                    ANAT_OR_COND = conceptsGroup.ANAT_OR_COND,
                    POA_CODE_STATUS = conceptsGroup.POA_CODE_STATUS,
                    POA_CODE_CHANGE = conceptsGroup.POA_CODE_CHANGE,
                    POA_CODE_CHANGE_YEAR = conceptsGroup.POA_CODE_CHANGE_YEAR,
                    VALID_START_DATE = conceptsGroup.VALID_START_DATE,
                    VALID_END_DATE = conceptsGroup.VALID_END_DATE,
                    INVALID_REASON = conceptsGroup.INVALID_REASON,
                    CREATE_DT = conceptsGroup.CREATE_DT
            )
            return dto
        }
    }
}
