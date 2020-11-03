package com.puj.admincenter.service

import com.puj.admincenter.domain.users.User
import com.puj.admincenter.dto.users.UserDto
import com.puj.admincenter.dto.users.CreateUserDto
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.dto.login.UpdatePassDto
import com.puj.admincenter.repository.users.UserRepository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {
    companion object {
        val LOG = LoggerFactory.getLogger(UserService::class.java)!!
    }

    fun count(): Long {
        return userRepository.count()
    }

    fun getById(userId: Int,
                authorization: String): ResponseEntity<*> {

        val user = userRepository.findById(userId)  // Hace solo el query
        return if (user.isPresent()) {
            ResponseEntity.ok(UserDto.convert(user.get()))
        } else {
            ResponseEntity<Any>(HttpStatus.NOT_FOUND)
        }
    }

    fun create(createUserDto: CreateUserDto): ResponseEntity<*> {
        if (userRepository.existsByEmail(createUserDto.email)) {
            val messageError = "User with email: ${createUserDto.email} already exists."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                    HttpStatus.CONFLICT)
        }

        val user = User(
                email = createUserDto.email,
                name = createUserDto.name,
                password = BCrypt.hashpw(createUserDto.password, BCrypt.gensalt()),
                username = createUserDto.username)
        val userSaved = userRepository.save(user)
        LOG.info("User ${createUserDto.email} created with id ${userSaved.id}")

        val responseDto = IdResponseDto(userSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                HttpStatus.CREATED)
    }

    fun updatePassword(updatePassDto: UpdatePassDto): ResponseEntity<*> {
        val user = userRepository.findUserByUserAndPassword(updatePassDto.username)
        if (user != null) {
            if (BCrypt.checkpw(updatePassDto.password, user.password)) {
                val hashedNewPassword = BCrypt.hashpw(updatePassDto.newpassword, BCrypt.gensalt())
                userRepository.updatePassword(updatePassDto.username, hashedNewPassword)
                val correctMessage = "User ${updatePassDto.username} was updated."
                LOG.info(correctMessage)
                return ResponseEntity<Any>(correctMessage, HttpStatus.OK)
            } else {
                val messageError = "Your current password is not valid"
                LOG.error(messageError)
                return ResponseEntity<Any>(messageError, HttpStatus.CONFLICT)
            }
        } else {
            val messageError = "User with username: ${updatePassDto.username} does not exist."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError, HttpStatus.NOT_FOUND)
        }
    }
}

