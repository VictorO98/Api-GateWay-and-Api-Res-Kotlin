package com.puj.admincenter.service

import com.puj.admincenter.domain.concepts.ConceptsGroup
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.dto.concepts.ConceptDto
import com.puj.admincenter.dto.concepts.CreateConceptDto
import com.puj.admincenter.repository.concept.ConceptRepository
import com.puj.admincenter.repository.concept.ConceptRepositorySpecification

import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus

@Service
class ConceptService(private val conceptRepository: ConceptRepository){
    companion object {
        val LOG = LoggerFactory.getLogger(ConceptService::class.java)!!
    }

    fun getAllConceptsGroups(VOCABULARY_ID: String?,
                             CONCEPT_ID: String?,
                             DOMAIN_ID: String?,
                             SHORT_DESC: String?): ResponseEntity<*> {
            LOG.debug("Found Client")
            val conceptsGroups = conceptRepository.findAll(ConceptRepositorySpecification(VOCABULARY_ID,
                        CONCEPT_ID,
                        DOMAIN_ID,
                        SHORT_DESC))
            val sizeConceptsGroup = conceptsGroups.size
            if(!conceptsGroups.isEmpty()){
                LOG.info("${sizeConceptsGroup} concepts found with Vocabulary ID: ${VOCABULARY_ID}, Concept Id: ${CONCEPT_ID}, Domain Id: ${DOMAIN_ID} and Short Desc: ${SHORT_DESC}")
                return ResponseEntity.ok(conceptsGroups)
            } else {
                LOG.info("No concepts were found with VocabularyId: ${VOCABULARY_ID}, ConceptId: ${CONCEPT_ID}, DomainId: ${DOMAIN_ID} and ShortDesc: ${SHORT_DESC}")
                return ResponseEntity<Any>("No concepts were found", HttpStatus.NOT_FOUND)
            }
    }

    fun createConcept(CreateConceptDto: CreateConceptDto): ResponseEntity<*> {
        if (conceptRepository.existsByConceptId(CreateConceptDto.CONCEPT_ID)) {
            val messageError = "The concept id: ${CreateConceptDto.CONCEPT_ID} already exists."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                    HttpStatus.CONFLICT)
        }

        val concept = ConceptsGroup(

                PXORDX=CreateConceptDto.PXORDX,
                OLDPXORDX=CreateConceptDto.OLDPXORDX,
                CODETYPE=CreateConceptDto.CODETYPE,
                CONCEPT_CLASS_ID=CreateConceptDto.CONCEPT_CLASS_ID,
                CONCEPT_ID=CreateConceptDto.CONCEPT_ID,
                VOCABULARY_ID=CreateConceptDto.VOCABULARY_ID,
                DOMAIN_ID=CreateConceptDto.DOMAIN_ID,
                TRACK=CreateConceptDto.TRACK,
                STANDARD_CONCEPT=CreateConceptDto.STANDARD_CONCEPT,
                CODE=CreateConceptDto.CODE,
                CODEWITHPERIODS=CreateConceptDto.CODEWITHPERIODS,
                CODESCHEME=CreateConceptDto.CODESCHEME,
                LONG_DESC=CreateConceptDto.LONG_DESC,
                SHORT_DESC=CreateConceptDto.SHORT_DESC,
                CODE_STATUS=CreateConceptDto.CODE_STATUS,
                CODE_CHANGE=CreateConceptDto.CODE_CHANGE,
                CODE_CHANGE_YEAR=CreateConceptDto.CODE_CHANGE_YEAR,
                CODE_PLANNED_TYPE=CreateConceptDto.CODE_PLANNED_TYPE,
                CODE_BILLING_STATUS=CreateConceptDto.CODE_BILLING_STATUS,
                CODE_CMS_CLAIM_STATUS=CreateConceptDto.CODE_CMS_CLAIM_STATUS,
                SEX_CD=CreateConceptDto.SEX_CD,
                ANAT_OR_COND=CreateConceptDto.ANAT_OR_COND,
                POA_CODE_STATUS=CreateConceptDto.POA_CODE_STATUS,
                POA_CODE_CHANGE=CreateConceptDto.POA_CODE_CHANGE,
                POA_CODE_CHANGE_YEAR=CreateConceptDto.POA_CODE_CHANGE_YEAR,
                VALID_START_DATE=CreateConceptDto.VALID_START_DATE,
                VALID_END_DATE=CreateConceptDto.VALID_END_DATE,
                INVALID_REASON=CreateConceptDto.INVALID_REASON,
                CREATE_DT=CreateConceptDto.CREATE_DT,
                DROP_ENABLE=CreateConceptDto.DROP_ENABLE)

        val conceptSaved = conceptRepository.save(concept)
        LOG.info("The concept ${CreateConceptDto.CONCEPT_ID} created with id ${conceptSaved.id}")

        val responseDto = IdResponseDto(conceptSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                HttpStatus.CREATED)
    }
}
