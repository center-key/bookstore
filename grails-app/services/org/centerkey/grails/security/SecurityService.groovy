// Grails Security ~ MIT License (Open Source) ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SecurityService {

   def grailsApplication
   def encoder = new BCryptPasswordEncoder()

   def setupRoles() {
      def upsertRole = { code, name ->
         def role = Role.findByCode(code) ?: new Role(code: code)
         role.name = name
         role.save(flush: true)
         }
      grailsApplication.config.security.roles.each { upsertRole(it.code, it.name) }
      }

   def setupInitialUser() {
      def email = grailsApplication.config.security.initialSetupUser
      def user = User.findByEmail(email)
      def createUser = {
         def password = (java.util.UUID.randomUUID() as String).take(8)
         def hash = encoder.encode(password)
         user = new User(email: email, hash: hash).save(flush: true)
         def empower = { new Capability(userId: user.id as String, roleId: it).save(flush: true) }
         Role.list().each { empower(it.id as String) }
         log.println("\n*** Initial setup user created ***")
         log.println("      User: ${email}")
         log.println("   Pasword: ${password}")
         }
      def deleteUser = {
         Capability.findAllByUserId(user.id as String).each { it.delete(flush: true) }
         user.delete(flush: true)
         }
      if (user)
         deleteUser()
      if (User.count() == 0)
         createUser()
      }

   def printSummary() {
      log.println("\nSecurity Summary")
      log.println("   Roles:")
      Role.list().each { log.println("      ${it.id} - ${it.name}") }
      log.println("   Counts:")
      log.println("      roles (${Role.count()}), users (${User.count()}), capabilities (${Capability.count()})\n")
      }

   def bootstrap() {
      setupRoles()
      setupInitialUser()
      printSummary()
      ////////////////////////////
      log.println("---\nObjects:")
      Role.list().each { log.println("   Role ${it.id} - ${it.name}") }
      User.list().each { log.println("   User ${it.id} - ${it.email}") }
      Capability.list().each { log.println("   ${User.get(it.userId).email} - ${Role.get(it.roleId).name}") }
      log.println("---")
      ////////////////////////////
      }

   def cleanupEmail(String email) {
      return email.trim().toLowerCase()
      }

   def register(String email, String password) {
      def user = new User(email: email, hash: encoder.encode(password))
      user.save(flush: true)
      def role = Role.findByCode(grailsApplication.config.security.newUserDefaultRole)
      new Capability(userId: user.id as String, roleId: role.id as String).save(flush: true)
      return user
      }

   def authenticate(String email, String password) {
      def user = User.findByEmail(cleanupEmail(email))
      return user && encoder.matches(password.trim(), user.hash) ? user : null
      }

   def setPassword(User user, String password) {
      user.hash = encoder.encode(password.trim())
      return user.save(flush: true)
      }

}
