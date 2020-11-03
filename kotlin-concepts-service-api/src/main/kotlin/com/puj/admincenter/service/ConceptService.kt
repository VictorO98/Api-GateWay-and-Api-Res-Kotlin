package com.puj.admincenter.service

import com.puj.admincenter.domain.concepts.ConceptsGroup
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.dto.concepts.ConceptDto
import com.puj.admincenter.dto.concepts.GetConceptDto
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
                        SHORT_DESC,0)).map { GetConceptDto.convert(it) }
            val sizeConceptsGroup = conceptsGroups.size
            if(!conceptsGroups.isEmpty()){
                LOG.info("${sizeConceptsGroup} concepts found with Vocabulary ID: ${VOCABULARY_ID}, Concept Id: ${CONCEPT_ID}, Domain Id: ${DOMAIN_ID} and Short Desc: ${SHORT_DESC}")
                return ResponseEntity.ok(conceptsGroups)
            } else {
                LOG.info("No concepts were found with VocabularyId: ${VOCABULARY_ID}, ConceptId: ${CONCEPT_ID}, DomainId: ${DOMAIN_ID} and ShortDesc: ${SHORT_DESC}")
                return ResponseEntity<Any>("No concepts were found", HttpStatus.NOT_FOUND)
            }
    }

    fun createConcept(conceptDto: ConceptDto): ResponseEntity<*> {
        if (conceptRepository.existsByConceptId(conceptDto.CONCEPT_ID)) {
            val messageError = "The concept id: ${conceptDto.CONCEPT_ID} already exists."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                    HttpStatus.CONFLICT)
        }

        val concept = ConceptsGroup(

                PXORDX=conceptDto.PXORDX,
                OLDPXORDX=conceptDto.OLDPXORDX,
                CODETYPE=conceptDto.CODETYPE,
                CONCEPT_CLASS_ID=conceptDto.CONCEPT_CLASS_ID,
                CONCEPT_ID=conceptDto.CONCEPT_ID,
                VOCABULARY_ID=conceptDto.VOCABULARY_ID,
                DOMAIN_ID=conceptDto.DOMAIN_ID,
                TRACK=conceptDto.TRACK,
                STANDARD_CONCEPT=conceptDto.STANDARD_CONCEPT,
                CODE=conceptDto.CODE,
                CODEWITHPERIODS=conceptDto.CODEWITHPERIODS,
                CODESCHEME=conceptDto.CODESCHEME,
                LONG_DESC=conceptDto.LONG_DESC,
                SHORT_DESC=conceptDto.SHORT_DESC,
                CODE_STATUS=conceptDto.CODE_STATUS,
                CODE_CHANGE=conceptDto.CODE_CHANGE,
                CODE_CHANGE_YEAR=conceptDto.CODE_CHANGE_YEAR,
                CODE_PLANNED_TYPE=conceptDto.CODE_PLANNED_TYPE,
                CODE_BILLING_STATUS=conceptDto.CODE_BILLING_STATUS,
                CODE_CMS_CLAIM_STATUS=conceptDto.CODE_CMS_CLAIM_STATUS,
                SEX_CD=conceptDto.SEX_CD,
                ANAT_OR_COND=conceptDto.ANAT_OR_COND,
                POA_CODE_STATUS=conceptDto.POA_CODE_STATUS,
                POA_CODE_CHANGE=conceptDto.POA_CODE_CHANGE,
                POA_CODE_CHANGE_YEAR=conceptDto.POA_CODE_CHANGE_YEAR,
                VALID_START_DATE=conceptDto.VALID_START_DATE,
                VALID_END_DATE=conceptDto.VALID_END_DATE,
                INVALID_REASON=conceptDto.INVALID_REASON,
                CREATE_DT=conceptDto.CREATE_DT,
                DROP_ENABLE=conceptDto.DROP_ENABLE)

        val conceptSaved = conceptRepository.save(concept)
        LOG.info("The concept ${conceptDto.CONCEPT_ID} created with id ${conceptSaved.id}")

        val responseDto = IdResponseDto(conceptSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                HttpStatus.CREATED)
    }

    fun updateConcept(conceptDto: ConceptDto): ResponseEntity<*>{
        if (conceptRepository.existsByConceptId(conceptDto.CONCEPT_ID)) {
            conceptRepository.updateByConceptId(conceptDto, conceptDto.CONCEPT_ID)
            LOG.info("Concept ${conceptDto.CONCEPT_ID} was updated.")
            return ResponseEntity<Any>(HttpStatus.OK)

        }
        val messageError = "Concept with concept id: ${conceptDto.CONCEPT_ID} does not exist."
        LOG.error(messageError)
        return ResponseEntity<Any>(messageError,
                HttpStatus.NOT_FOUND)
    }

    fun deleteConcept(CONCEPT_ID:String): ResponseEntity<*>{
        if (conceptRepository.existsByConceptId(CONCEPT_ID)) {
            conceptRepository.deleteByConceptId(CONCEPT_ID)
            val message = "Concept ${CONCEPT_ID} was deleted."
            LOG.info(message)
            return ResponseEntity<Any>(message, HttpStatus.OK)


        }
        val messageError = "The conceptId: ${CONCEPT_ID} does not exists."
        LOG.error(messageError)
        return ResponseEntity<Any>(messageError,
                HttpStatus.NOT_FOUND)


    }
}
