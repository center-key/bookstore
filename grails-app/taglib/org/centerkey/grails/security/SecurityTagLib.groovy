// Grails Security ~ MIT License (Open Source) ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

class SecurityTagLib {

   static namespace = "security"

   def username = {
      // Usage:
      //    <security:username />
      out << User.get(session.securityUserId)?.email
      }

   def isAnonymous = { attrs, body ->
      // Usage:
      //    <security:isAnonymous>You are not signed in.</security:isAnonymous>
      if (!session.securityUserId)
         out << body()
      }

   def isSignedIn = { attrs, body ->
      // Usage:
      //    <security:isSignedIn>You are signed in.</security:isSignedIn>
      if (session.securityUserId)
         out << body()
      }

   def signInLink = { attrs, body ->
      // Usage:
      //    <security:signInLink />
      // or
      //    <security:signInLink>Login</security:signInLink>
      out << g.link([controller: "security", action: "signin"], { body() ?: "Sign in" })
      }

   def signOutLink = { attrs, body ->
      // Usage:
      //    <security:signOutLink />
      // or
      //    <security:signOutLink>Logout</security:signOutLink>
      out << g.link([controller: "security", action: "signout"], { body() ?: "Sign out" })
      }

}
