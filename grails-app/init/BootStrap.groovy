// Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors

class BootStrap {

   def grailsApplication
   def securityService

   def init = { servletContext ->
      def cfg = grailsApplication.config
      log.println("Grails v${grailsApplication.metadata.getGrailsVersion()}")
      log.println("${cfg.info.app.name} v${cfg.info.app.version}")
      securityService.bootstrap()
      }

   def destroy = {
      }

}
