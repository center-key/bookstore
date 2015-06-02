// Grails Security ~ MIT License (Open Source) ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

class SecurityInterceptor {

   SecurityInterceptor() {
      matchAll()
         .excludes(uri: "/")
         .excludes(uri: "/assets/**")
         .excludes(controller: "security")
      }

   boolean before() {
      session.securityRedirect = request.forwardURI
      if (!session.securityUserId)
         response.setHeader("Refresh", "0; url=/signin")  //TEMPORARY
         //redirect(controller: "security", action: "signin")
         //see: https://github.com/grails/grails-core/issues/600
         //see: https://github.com/grails/grails-core/issues/625
      return !!session.securityUserId
      }

}
