package com.puj.admincenter.controller

import com.puj.admincenter.dto.concepts.ConceptDto
import com.puj.admincenter.service.ConceptService
import io.swagger.annotations.ApiOperation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.data.web.PageableDefault
import javax.validation.Valid


@CrossOrigin
@RequestMapping("/concepts")
@RestController
class ConceptController(private val conceptService: ConceptService){
    companion object{
        val logger = LoggerFactory.getLogger(ConceptController::class.java)!!
    }

    @GetMapping(
            value=["/getAllConceptsGroup"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    @ApiOperation(
        value = "Get all conceptsGroup",
        httpMethod = "GET"
    )
    fun getAllConceptsGroups(@RequestParam(value = "VOCABULARY_ID", required=false) VOCABULARY_ID: String?,
                             @RequestParam(value = "CONCEPT_ID", required=false) CONCEPT_ID: String?,
                             @RequestParam(value = "DOMAIN_ID", required=false) DOMAIN_ID: String?,
                             @RequestParam(value = "SHORT_DESC", required=false) SHORT_DESC: String?,
                             @RequestHeader(value="authorization", required = true) authorization: String): ResponseEntity<*> {
        return conceptService.getAllConceptsGroups(VOCABULARY_ID, CONCEPT_ID, DOMAIN_ID, SHORT_DESC)
    }

    @PostMapping(
            value=["/createConcept"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    fun createConcept(@RequestBody @Valid conceptDto: ConceptDto,
                      @RequestHeader(value="authorization", required = true) authorization: String): ResponseEntity<*>
            = conceptService.createConcept(conceptDto)

    @PutMapping(
            value = ["/updateConcept"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )

    fun updateConcept(@RequestBody @Valid conceptDto: ConceptDto,
                      @RequestHeader(value="authorization", required = true) authorization: String): ResponseEntity<*>
            = conceptService.updateConcept(conceptDto)

    @DeleteMapping(
            value = ["/deleteConcept"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )

    fun deleteConcept(@RequestParam(value = "CONCEPT_ID", required=true) CONCEPT_ID: String,
                      @RequestHeader(value="authorization", required = true) authorization: String): ResponseEntity<*>
            = conceptService.deleteConcept(CONCEPT_ID)

}