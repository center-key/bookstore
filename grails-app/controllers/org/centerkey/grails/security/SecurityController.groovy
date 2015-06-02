package org.centerkey.grails.security

import grails.converters.JSON

class SecurityController {

   def securityService

   static allowedMethods = [authenticate: "POST", register: "POST"]

   def authenticate() {
      def user = securityService.authenticate(params.email, params.password)
      session.securityUserId = user?.id as String
      def info = user ?
         [auth: true,  redirect: session.securityRedirect ?: "/"] :
         [auth: false, message: "Email or password is incorrect."]
      render info as JSON
      }

   def register() {
      def min = grailsApplication.config.security.minPasswordLength
      def invalidEmail = { !(it ==~ /.+[@].+[.].+/) }
      def email = securityService.cleanupEmail(params.email)
      def password = params.password.trim()
      def msg
      if (invalidEmail(email) || User.findByEmail(email))
         msg = "Email invalid or already taken."
      else if (password.size() < min)
         msg = "Password must be at least ${min} characters."
      def user = msg ? null : securityService.register(params.email, params.password)
      session.securityUserId = user?.id as String
      def info = user ?
         [auth: true,  redirect: session.securityRedirect ?: "/"] :
         [auth: false, message: msg]
      render info as JSON
      }

}
