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
         println("\n*** Initial setup user created ***")
         println("      User: ${email}")
         println("   Pasword: ${password}")
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
      println("\nSecurity Summary")
      println("   Roles:")
      Role.list().each { println("      ${it.id} - ${it.name}") }
      println("   Counts:")
      println("      roles (${Role.count()}), users (${User.count()}), capabilities (${Capability.count()})\n")
      }

   def bootstrap() {
      setupRoles()
      setupInitialUser()
      printSummary()
      ////////////////////////////
      println("---\nObjects:")
      Role.list().each { println("   Role ${it.id} - ${it.name}") }
      User.list().each { println("   User ${it.id} - ${it.email}") }
      Capability.list().each { println("   ${User.get(it.userId).email} - ${Role.get(it.roleId).name}") }
      println("---")
      ////////////////////////////
      }

}
